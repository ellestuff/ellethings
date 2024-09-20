package ellestuff.ellethings.blocks;

import ellestuff.ellethings.ElleThings;
import ellestuff.ellethings.items.RedstoneTransformerItem;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ElleBlocks {
    // Standard Blocks
    public static final Block GELATITE_BLOCK = registerBlock("gelatite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK)
                    .mapColor(MapColor.PINK)));

    // Coloured Redstone Lamp
    public static final Block COLOURED_LAMP = registerBlock("coloured_lamp",
            new ColouredLampBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP).luminance(ColouredLampBlock.STATE_TO_LUMINANCE)));

    // Redstone Transformer (+ BlockItem)
    public static final Block TRANSFORMER = Registry.register(Registries.BLOCK, new Identifier(ElleThings.MODID, "transformer"),
            new RedstoneTransformerBlock(FabricBlockSettings.create().nonOpaque().notSolid()));
    public static final Item TRANSFORMER_ITEM = Registry.register(Registries.ITEM, new Identifier(ElleThings.MODID, "transformer"),
            new RedstoneTransformerItem(TRANSFORMER, new FabricItemSettings()));



    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(ElleThings.MODID, name), block);
    }

    public static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(ElleThings.MODID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerElleBlocks() {
        // Texture Stuff
        BlockRenderLayerMap.INSTANCE.putBlock(TRANSFORMER, RenderLayer.getCutout());

        ModelPredicateProviderRegistry.register(TRANSFORMER_ITEM, new Identifier("power"),
                (stack, world, entity, seed) -> (float)RedstoneTransformerItem.getPower(stack)/20
        );

        // Building Blocks
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Items.EMERALD_BLOCK, GELATITE_BLOCK);
        });

        // Redstone
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            content.addAfter(Items.REDSTONE_LAMP, COLOURED_LAMP);
            content.addAfter(Items.REDSTONE_TORCH, TRANSFORMER_ITEM);
        });
    }
}
