package ellestuff.ellethings.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.FireworkStarItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

import static ellestuff.ellethings.ElleThings.FIREWORK_STAR_PROJECTILE;
import static java.lang.Math.random;

public class FireworkStarEntity extends ThrownItemEntity {
    private ItemStack starItem;
    private NbtCompound rocket;

    public FireworkStarEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);

    }

    public FireworkStarEntity(LivingEntity livingEntity, World world, ItemStack starItem) {
        super(FIREWORK_STAR_PROJECTILE, livingEntity, world);
        this.setStarItem(starItem);
    }

    protected Item getDefaultItem() {
        return Items.FIREWORK_STAR;
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.FIREWORK : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        goBoom();
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        goBoom();
    }

    private void goBoom() {
        Vec3d vec3d = this.getPos();

        System.out.println(rocket);

        if (rocket == null) {
            System.err.println("Rocket is null in goBoom");
        } else {
            System.out.println("Rocket is not null in goBoom: " + rocket);
        }

        if (this.getWorld().isClient) {
            if (rocket != null) {
                this.getWorld().addFireworkParticle(vec3d.x, vec3d.y, vec3d.z, 0, 0, 0, rocket);
            } else {
                for (int i = 0; i < 2; i++) {
                    this.getWorld().addParticle(ParticleTypes.SMOKE, vec3d.x, vec3d.y, vec3d.z, ((random()-0.5)/10), ((random()-0.5)/10), ((random()-0.5)/10));
                }
                System.err.println("Rocket is null on client side");
            }
        }
        else {
            // literally just copypasted this off the firework code, with a few tweaks
            List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, this.getBoundingBox().expand(5.0));
            Iterator var9 = list.iterator();

            while(rocket != null) {
                LivingEntity livingEntity;
                do {
                    if (!var9.hasNext()) {
                        return;
                    }

                    livingEntity = (LivingEntity)var9.next();
                } while(this.squaredDistanceTo(livingEntity) > 25.0);

                boolean bl = false;

                for(int i = 0; i < 2; ++i) {
                    Vec3d vec3d2 = new Vec3d(livingEntity.getX(), livingEntity.getBodyY(0.5 * (double)i), livingEntity.getZ());
                    HitResult hitResult = this.getWorld().raycast(new RaycastContext(vec3d, vec3d2, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, this));
                    if (hitResult.getType() == HitResult.Type.MISS) {
                        bl = true;
                        break;
                    }
                }

                if (bl) {
                    float g = 5 * (float)Math.sqrt((5.0 - (double)this.distanceTo(livingEntity)) / 5.0);
                    livingEntity.damage(this.getDamageSources().thrown(this, this.getOwner()), g);

                }

                this.discard();
            }
        }
    }

    // i have no idea what i'm doing here so i asked chatgpt T~T

    public void setStarItem(ItemStack stack) {
        this.starItem = stack.copy();
        System.out.println("Star Item set: " + this.starItem);
        if (this.starItem.getItem() instanceof FireworkStarItem) {
            NbtCompound starItemNbt = this.starItem.getNbt();
            if (starItemNbt != null && starItemNbt.contains("Explosion")) {
                rocket = new NbtCompound();
                NbtList explosions = new NbtList();
                NbtCompound explosionData = starItemNbt.getCompound("Explosion");
                explosions.add(explosionData);
                rocket.put("Explosions", explosions);
            }
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        System.out.println("Writing NBT data");
        if (rocket != null) {
            nbt.put("Fireworks", rocket);
            System.out.println("NBT written: " + nbt);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        System.out.println("Reading NBT data");
        if (nbt.contains("Fireworks")) {
            rocket = nbt.getCompound("Fireworks");
            System.out.println("Rocket NBT read: " + rocket);
            NbtList explosions = rocket.getList("Explosions", 10);
            if (!explosions.isEmpty()) {
                // Assuming the first compound in the list is the correct explosion data
                NbtCompound explosionData = explosions.getCompound(0);
                ItemStack itemStack = new ItemStack(Items.FIREWORK_STAR);
                itemStack.setNbt(explosionData);  // Set the NBT data to the itemStack
                this.starItem = itemStack;
                System.out.println("Star Item read from NBT: " + this.starItem);
            }
        }
    }
}
