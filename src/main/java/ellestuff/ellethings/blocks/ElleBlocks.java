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
import net.minecraft.block.TransparentBlock;
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
            new TransparentBlock(FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK)
                    .mapColor(MapColor.PINK)));

    public static final Block CHECKERED_TILES = registerBlock("checkered_tiles", new Block(FabricBlockSettings.copyOf(Blocks.CALCITE)));
    public static final Block DARK_CHECKERED_TILES = registerBlock("dark_checkered_tiles", new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE)));
    public static final Block KITCHEN_TILES = registerBlock("kitchen_tiles", new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));


    // Coloured Redstone Lamp
    public static final Block COLOURED_LAMP = registerBlock("coloured_lamp",
            new ColouredLampBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP).luminance(ColouredLampBlock.STATE_TO_LUMINANCE)));

    // Redstone Transformer (+ BlockItem)
    public static final Block TRANSFORMER = Registry.register(Registries.BLOCK, new Identifier(ElleThings.MODID, "transformer"),
            new RedstoneTransformerBlock(FabricBlockSettings.create().nonOpaque().notSolid()));
    public static final Item TRANSFORMER_ITEM = Registry.register(Registries.ITEM, new Identifier(ElleThings.MODID, "transformer"),
            new RedstoneTransformerItem(TRANSFORMER, new FabricItemSettings()));

    // Wooden Boards
    public static final Block OAK_BOARDS = registerBlock("oak_boards", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block BIRCH_BOARDS = registerBlock("birch_boards", new Block(FabricBlockSettings.copyOf(Blocks.BIRCH_PLANKS)));
    public static final Block SPRUCE_BOARDS = registerBlock("spruce_boards", new Block(FabricBlockSettings.copyOf(Blocks.SPRUCE_PLANKS)));
    public static final Block DARK_OAK_BOARDS = registerBlock("dark_oak_boards", new Block(FabricBlockSettings.copyOf(Blocks.DARK_OAK_PLANKS)));
    public static final Block ACACIA_BOARDS = registerBlock("acacia_boards", new Block(FabricBlockSettings.copyOf(Blocks.ACACIA_PLANKS)));
    public static final Block MANGROVE_BOARDS = registerBlock("mangrove_boards", new Block(FabricBlockSettings.copyOf(Blocks.MANGROVE_PLANKS)));
    public static final Block JUNGLE_BOARDS = registerBlock("jungle_boards", new Block(FabricBlockSettings.copyOf(Blocks.JUNGLE_PLANKS)));
    public static final Block CHERRY_BOARDS = registerBlock("cherry_boards", new Block(FabricBlockSettings.copyOf(Blocks.CHERRY_PLANKS)));
    public static final Block CRIMSON_BOARDS = registerBlock("crimson_boards", new Block(FabricBlockSettings.copyOf(Blocks.CRIMSON_PLANKS)));
    public static final Block WARPED_BOARDS = registerBlock("warped_boards", new Block(FabricBlockSettings.copyOf(Blocks.WARPED_PLANKS)));
    public static final Block BAMBOO_BOARDS = registerBlock("bamboo_boards", new Block(FabricBlockSettings.copyOf(Blocks.BAMBOO_PLANKS)));


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
        //BlockRenderLayerMap.INSTANCE.putBlock(TRANSFORMER, RenderLayer.getCutout());

        /*ModelPredicateProviderRegistry.register(TRANSFORMER_ITEM, new Identifier("power"),
                (stack, world, entity, seed) -> (float)RedstoneTransformerItem.getPower(stack)/20
        );*/

        // Building Blocks
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Items.EMERALD_BLOCK, GELATITE_BLOCK);

            // Wooden Boards
            content.addAfter(Items.OAK_PLANKS, OAK_BOARDS);
            content.addAfter(Items.BIRCH_PLANKS, BIRCH_BOARDS);
            content.addAfter(Items.SPRUCE_PLANKS, SPRUCE_BOARDS);
            content.addAfter(Items.DARK_OAK_PLANKS, DARK_OAK_BOARDS);
            content.addAfter(Items.ACACIA_PLANKS, ACACIA_BOARDS);
            content.addAfter(Items.MANGROVE_PLANKS, MANGROVE_BOARDS);
            content.addAfter(Items.JUNGLE_PLANKS, JUNGLE_BOARDS);
            content.addAfter(Items.CHERRY_PLANKS, CHERRY_BOARDS);
            content.addAfter(Items.CRIMSON_PLANKS, CRIMSON_BOARDS);
            content.addAfter(Items.WARPED_PLANKS, WARPED_BOARDS);
            content.addAfter(Items.BAMBOO_PLANKS, BAMBOO_BOARDS);
        });

        // Redstone
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            content.addAfter(Items.REDSTONE_LAMP, COLOURED_LAMP);
            content.addAfter(Items.REDSTONE_TORCH, TRANSFORMER_ITEM);
        });
    }
}
