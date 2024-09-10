package ellestuff.ellethings.items;

import ellestuff.ellethings.ElleThings;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Predicate;

public class ElleItems {
    // Throwing Gloves
    public static final Item THROWING_GLOVE = registerItem("throwing_glove", new ThrowingGloveItem(new FabricItemSettings()
            .maxCount(1)
            .maxDamage(200),
            stack ->
                    stack.isOf(Items.SLIME_BALL) ||
                    stack.isOf(Items.SNOWBALL)));

    public static final Item NETHERITE_THROWING_GLOVE = registerItem("netherite_throwing_glove", new ThrowingGloveItem(new FabricItemSettings()
            .maxCount(1)
            .maxDamage(500)
            .fireproof(),
            stack ->
                    stack.isOf(Items.SLIME_BALL) ||
                    stack.isOf(Items.SNOWBALL) ||
                    stack.isOf(Items.MAGMA_CREAM) ||
                    stack.isOf(Items.FIREWORK_STAR)));

    public static final Item REDSTONE_GLOVE = registerItem("redstone_glove", new RedstoneGloveItem(new FabricItemSettings()
            .maxCount(1)));

    // Slime Staff
    public static final Item SLIME_STAFF = registerItem("slime_staff", new SlimeStaffItem(new FabricItemSettings()
            .maxCount(1)
            .maxDamage(180)));

    // Regular ass items lmao
    public static final Item GELATITE = registerItem("gelatite", new Item(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ElleThings.MODID, name), item);
    }

    public static void registerElleItems() {
        // Combat Group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.addAfter(Items.BOW, THROWING_GLOVE);
            content.addAfter(THROWING_GLOVE, NETHERITE_THROWING_GLOVE);
        });

        // Ingredients Group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter(Items.EMERALD, GELATITE);
        });
    }
}
