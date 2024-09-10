package ellestuff.ellethings.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import static java.lang.Math.random;
import static java.lang.Math.round;

public class SlimeStaffItem extends Item {

    public SlimeStaffItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        // Your code here
        super.inventoryTick(stack, world, entity, slot, selected);

        if (selected & world.isClient) {
            PlayerEntity user = (PlayerEntity) entity;
            BlockHitResult ray = raycast(world, user, RaycastContext.FluidHandling.ANY);
            Vec3d pos = ray.getPos();

            if (ray.getSide() == Direction.UP && !user.getItemCooldownManager().isCoolingDown(this)) {
                world.addParticle(ParticleTypes.ITEM_SLIME, pos.x + ((random()-0.5)/2) , pos.y, pos.z + ((random()-0.5)/2), ((random()-0.5)/10), 0.2, ((random()-0.5)/10));
            }
        }
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        BlockHitResult ray = raycast(world, user, RaycastContext.FluidHandling.ANY);
        Vec3d pos = ray.getPos();
        Float offsetStrength = 1f;

        if (ray.getSide() == Direction.UP) {
            if (!world.isClient) {
                world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SLIME_SQUISH , SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

                SlimeEntity slime = EntityType.SLIME.create(world);

                slime.refreshPositionAndAngles(pos.x, pos.y, pos.z, 0, 0);
                slime.setSize(0, true);
                EntityDimensions slimeSize = slime.getDimensions(slime.getPose());
                double slimeRadius = slimeSize.width * 0.5;
                
                for (int cz = -1; cz <= 1; cz += 2) {
                    for (int cx = -1; cx <= 1; cx += 2) {
                        for (int cy = 0; cy <= 1; cy += 1) {
                            if (solidCheck(world, pos.x + (cx * slimeRadius), pos.y + (cy * slimeSize.height), pos.z + (cz * slimeRadius))) {
                                //too close to a solid, so stop here
                                return TypedActionResult.success(itemStack, world.isClient());
                            }
                        }
                    }
                }

                world.spawnEntity(slime);

                user.getItemCooldownManager().set(this, 100);

                itemStack.damage(1, user, (p) -> p.sendToolBreakStatus(hand));
            }
            return TypedActionResult.success(itemStack, world.isClient());
        }

        return TypedActionResult.pass(itemStack);
    }
    
    public boolean solidCheck(World world, double x, double y, double z) {
        BlockPos blockPos = new BlockPos(new Vec3i((int)Math.floor(x), (int)Math.floor(y), (int)Math.floor(z)));
        return world.getBlockState(blockPos).isSolidBlock(world, blockPos);
    }
}
