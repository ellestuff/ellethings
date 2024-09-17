package ellestuff.ellethings.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class RedstoneTransformerItem extends BlockItem {
    public int power = 0;

    public RedstoneTransformerItem(Block block, Item.Settings settings) {
        super(block, settings);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }


}
