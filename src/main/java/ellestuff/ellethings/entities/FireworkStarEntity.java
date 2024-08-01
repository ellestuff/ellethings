package ellestuff.ellethings.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
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

public class FireworkStarEntity extends ThrownItemEntity {
    private NbtCompound rocket;

    public FireworkStarEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public FireworkStarEntity(LivingEntity livingEntity, ItemStack item, World world) {
        super(FIREWORK_STAR_PROJECTILE, livingEntity, world);

        NbtCompound explosion = item.getNbt();
        NbtList explosionList = new NbtList();

        explosionList.add(explosion);

        rocket.put("explosions", explosionList);
        rocket.putByte("flight_duration", (byte) 0);
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
        if (this.getWorld().isClient) {
            this.getWorld().addFireworkParticle(this.getX(), this.getY(), this.getZ(), vec3d.x, vec3d.y, vec3d.z, rocket);
        }
        else {
            // literally just copypasted this off the firework code, with a few tweaks
            List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, this.getBoundingBox().expand(5.0));
            Iterator var9 = list.iterator();

            while(true) {
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
            }
        }
    }
}
