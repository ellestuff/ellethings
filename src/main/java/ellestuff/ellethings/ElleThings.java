package ellestuff.ellethings;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ElleThings implements ModInitializer {
    public static final String MODID = "ellethings";

    public static final Item SLIME_THROWER = new Item(new FabricItemSettings());
    public static final Item MAGMA_THROWER = new Item(new FabricItemSettings());

    @Override
    public void onInitialize() {
        { // Item Registering
            Registry.register(Registries.ITEM, new Identifier(MODID, "slime_thrower"), SLIME_THROWER);
            Registry.register(Registries.ITEM, new Identifier(MODID, "magma_thrower"), MAGMA_THROWER);
        } // Item Registering
    }
}
