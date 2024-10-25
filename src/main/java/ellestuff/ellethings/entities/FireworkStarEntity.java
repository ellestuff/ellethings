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

import static ellestuff.ellethings.entities.ElleEntities.FIREWORK_STAR_PROJECTILE;
import static java.lang.Math.random;

public class FireworkStarEntity extends ThrownItemEntity {
    private static final TrackedData<NbtCompound> FIREWORK_NBT = DataTracker.registerData(FireworkStarEntity.class, TrackedDataHandlerRegistry.NBT_COMPOUND);

    public FireworkStarEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public FireworkStarEntity(LivingEntity livingEntity, World world, ItemStack starItem) {
        super(FIREWORK_STAR_PROJECTILE, livingEntity, world);
        this.setRocketNbtFromItem(starItem);
    }

    protected Item getDefaultItem() {
        return Items.FIREWORK_STAR;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(FIREWORK_NBT, new NbtCompound()); // Initialize with an empty NBT compound
    }

    public void setRocketNbt(NbtCompound nbt) {
        this.dataTracker.set(FIREWORK_NBT, nbt); // Sync the NBT data with the DataTracker
    }

    public NbtCompound getRocketNbt() {
        return this.dataTracker.get(FIREWORK_NBT); // Get the NBT data from the DataTracker
    }

    public void setRocketNbtFromItem(ItemStack stack) {
        if (stack.getItem() instanceof FireworkStarItem) {
            NbtCompound starItemNbt = stack.getNbt();
            if (starItemNbt != null && starItemNbt.contains("Explosion")) {
                NbtCompound explosionNbt = starItemNbt.getCompound("Explosion");

                NbtCompound rocketNbt = new NbtCompound();
                NbtList explosions = new NbtList();
                explosions.add(explosionNbt);
                rocketNbt.put("Explosions", explosions);

                setRocketNbt(rocketNbt); // Store and sync the NBT data
            }
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("Fireworks", this.getRocketNbt()); // Save the NBT data
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Fireworks")) {
            this.setRocketNbt(nbt.getCompound("Fireworks")); // Load the NBT data
        }
    }

    private void goBoom() {
        NbtCompound explosionNbt = this.getRocketNbt();
        Vec3d vec3d = this.getPos();
        System.err.println(explosionNbt);
        if (!this.getWorld().isClient && !(explosionNbt == null || explosionNbt.isEmpty())) {
            // literally just copypasted this off the firework code, with a few tweaks
            List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, this.getBoundingBox().expand(5.0));
            Iterator var9 = list.iterator();

            while(getRocketNbt() != null) {
                LivingEntity livingEntity;
                do {
                    if (!var9.hasNext()) {
                        return;
                    }

                    livingEntity = (LivingEntity)var9.next();
                } while(this.squaredDistanceTo(livingEntity) > 16.0);

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
                    float g = 4 * (float)Math.sqrt((4.0 - (double)this.distanceTo(livingEntity)) / 4.0);
                    livingEntity.damage(this.getDamageSources().thrown(this, this.getOwner()), g);

                }
            }

        }
    }

    @Override
    public void handleStatus(byte status) {
        if (status == 3) { // Custom status ID
            NbtCompound explosionNbt = this.getRocketNbt();
            Vec3d vec3d = this.getPos();
            if (explosionNbt == null || explosionNbt.isEmpty()) {
                this.getWorld().addParticle(ParticleTypes.POOF, vec3d.x, vec3d.y, vec3d.z, 0,0,0);
            } else {
                this.getWorld().addFireworkParticle(vec3d.x, vec3d.y, vec3d.z, 0, 0, 0, explosionNbt); // Add firework particles using the NBT data
            }
        }
    }

    private void triggerExplosion() {
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, (byte) 3);
        }
        goBoom();
        this.discard();
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        triggerExplosion();
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        triggerExplosion();
    }
}
