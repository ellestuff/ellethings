package ellestuff.ellethings.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import net.minecraft.world.World;

import static ellestuff.ellethings.ElleThings.SLIME_BALL_PROJECTILE;

public class SlimeBallEntity extends ThrownItemEntity {

    private int maxBounces = 3;
    private int bounces = 0;

    public SlimeBallEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public SlimeBallEntity(LivingEntity livingEntity, World world) {
        super(SLIME_BALL_PROJECTILE, livingEntity, world);
    }

    protected Item getDefaultItem() {
        return Items.SLIME_BALL;
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.ITEM_SLIME : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
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
        Entity entity = entityHitResult.getEntity();
        int i = 2;
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), (float)i + bounces);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            if (hitResult.getType() == HitResult.Type.BLOCK && bounces < maxBounces) { // I asked ChatGPT to generate this code T^T
                BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                Direction hitFace = blockHitResult.getSide();
                Vec3d hitNormal = new Vec3d(hitFace.getVector().getX(), hitFace.getVector().getY(), hitFace.getVector().getZ());
                Vec3d reflectedMotion = this.getVelocity().subtract(hitNormal.multiply(this.getVelocity().dotProduct(hitNormal) * 2.0));

                this.setVelocity(reflectedMotion.multiply(0.7f));
                this.getWorld().playSound(null, BlockPos.ofFloored(this.getPos()), SoundEvents.ENTITY_SLIME_SQUISH_SMALL, SoundCategory.PLAYERS, 1.0F, 1.0F / (getWorld().getRandom().nextFloat() * 0.4F + 1.2F));
                this.getWorld().sendEntityStatus(this, (byte) 3);

                bounces++;
            } else {
                this.getWorld().sendEntityStatus(this, (byte)3);
                this.discard();
            }
        }

    }
}
