package ellestuff.ellethings.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RedstoneGloveItem extends Item {
    public int power = 15;
    private BlockPos lastTarget;

    public RedstoneGloveItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return ActionResult.FAIL;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);



        if (selected & world.isClient) {
            System.out.println(lastTarget);

            PlayerEntity user = (PlayerEntity) entity;
            BlockHitResult ray = raycast(world, user, RaycastContext.FluidHandling.ANY);
            BlockPos pos = ray.getBlockPos();

            if (world.getBlockState(pos).contains(Properties.POWER)) {
                world.setBlockState(pos, world.getBlockState(pos).with(Properties.POWER, this.getPower()), 3);
            }
            if (world.getBlockState(pos).contains(Properties.LIT)) {
                world.setBlockState(pos, world.getBlockState(pos).with(Properties.LIT, true), 3);
            }
        }
    }

    public int getPower() {
        return this.power;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("WIP Item!!!").formatted(Formatting.RED));
        tooltip.add(Text.literal("This item is unfinished and is very buggy!!").formatted(Formatting.DARK_RED).formatted(Formatting.ITALIC));
    }
}
