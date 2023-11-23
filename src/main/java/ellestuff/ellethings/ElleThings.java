package ellestuff.ellethings;

import ellestuff.ellethings.items.ThrowingGloveItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ElleThings implements ModInitializer {
    public static final String MODID = "ellethings";

    public static final Item THROWING_GLOVE = new ThrowingGloveItem(new FabricItemSettings());
    public static final Item NETHERITE_THROWING_GLOVE = new Item(new FabricItemSettings());

    @Override
    public void onInitialize() {
        { // Item Registering
            Registry.register(Registries.ITEM, new Identifier(MODID, "throwing_glove"), THROWING_GLOVE);
            Registry.register(Registries.ITEM, new Identifier(MODID, "netherite_throwing_glove"), NETHERITE_THROWING_GLOVE);
        } // Item Registering

        { // Creative menu listings
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
                content.addAfter(Items.BOW, THROWING_GLOVE);
                content.addAfter(THROWING_GLOVE, NETHERITE_THROWING_GLOVE);
            });
        }
    }
}
