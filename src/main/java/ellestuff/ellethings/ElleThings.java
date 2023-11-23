package ellestuff.ellethings;

import ellestuff.ellethings.entities.SlimeBallEntity;
import ellestuff.ellethings.items.ThrowingGloveItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import javax.swing.text.html.parser.Entity;

public class ElleThings implements ModInitializer {
    public static final String MODID = "ellethings";

    public static final Item THROWING_GLOVE = new ThrowingGloveItem(new FabricItemSettings());
    public static final Item NETHERITE_THROWING_GLOVE = new Item(new FabricItemSettings());

    public static final EntityType<SlimeBallEntity> SLIME_BALL_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MODID, "slime_ball"),
            FabricEntityTypeBuilder.<SlimeBallEntity>create(SpawnGroup.MISC, SlimeBallEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build()
    );

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
        } // Creative menu listings
    }
}
