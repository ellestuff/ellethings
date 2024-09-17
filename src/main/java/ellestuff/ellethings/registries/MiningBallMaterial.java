package ellestuff.ellethings.registries;

import ellestuff.ellethings.ElleThings;
import net.minecraft.item.Item;

public class MiningBallMaterial {
    private final String name;
    private final Item item;
    private final int colour;

    public MiningBallMaterial(String name, Item material, int colour) {
        this.name = name;
        this.item = material;
        this.colour = colour;
    }

    public String getName() { return name; }
    public Item getItem() { return item; }
    public int getColour() { return colour; }
    public String getTranslationKey() { return "miningballmaterial." + ElleThings.MODID + "." + name; }
}
