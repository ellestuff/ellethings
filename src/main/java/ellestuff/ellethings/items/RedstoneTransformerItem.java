package ellestuff.ellethings.items;

import ellestuff.ellethings.ElleThings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static ellestuff.ellethings.blocks.RedstoneTransformerBlock.POWER;

public class RedstoneTransformerItem extends BlockItem {
    public RedstoneTransformerItem(Block block, Settings settings) {
        super(block, settings);
    }

    // Get the power from the item's NBT
    public static int getPower(ItemStack stack) {
        if (stack.hasNbt() && stack.getNbt().contains("elleStrength")) {
            return stack.getNbt().getInt("elleStrength");
        }
        return 15;
    }


    public static void setPower(ItemStack stack, int power) {
        power = Math.min(Math.max(power,0),15);

        if (power == 15) { stack.removeSubNbt("elleStrength"); }
        else {
            NbtCompound nbt = stack.getOrCreateNbt();
            nbt.putInt("elleStrength", power);
        }

    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal(String.format("Power: %s", this.getPower(stack))).formatted(Formatting.GRAY));
    }
}
