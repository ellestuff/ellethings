package ellestuff.ellethings.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.function.Predicate;

public class NetheriteGloveItem extends ThrowingGloveItem{


    public static final Predicate<ItemStack> THROWER_PROJECTILES = (stack) ->
        stack.isOf(Items.SLIME_BALL) ||
        stack.isOf(Items.SNOWBALL) ||
        stack.isOf(Items.MAGMA_CREAM) ||
        stack.isOf(Items.FIREWORK_STAR);

    public NetheriteGloveItem(Item.Settings settings) {
        super(settings);
    }

    public Predicate<ItemStack> getProjectiles() {
        return THROWER_PROJECTILES;
    }
}
