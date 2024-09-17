package ellestuff.ellethings.items;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MiningBallItem extends Item implements Vanishable {
    public MiningBallItem(Item.Settings settings) {
        super(settings);
    }

    public static float getPullProgress(int useTicks) {
        float f = (float)useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) { return false; }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    public int getMaxUseTime(ItemStack stack) { return 72000; }

    public int getEnchantability() {
        return 1;
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            float f = getPullProgress(i);
            if (i >= 10) {
                if (!world.isClient) {
                    SnowballEntity snowballEntity = new SnowballEntity(world, user);
                    snowballEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, f * 0.8f, 1.0F);
                    world.spawnEntity(snowballEntity);
                }

                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                if (!playerEntity.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }
        }
    }
}
