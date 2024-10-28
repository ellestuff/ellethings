package ellestuff.ellethings.datagen;

import ellestuff.ellethings.blocks.ElleBlocks;
import ellestuff.ellethings.items.ElleItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ElleModelProvider extends FabricModelProvider {
    public ElleModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.GELATITE_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.ACACIA_BOARDS);
        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.BAMBOO_BOARDS);
        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.BIRCH_BOARDS);
        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.CHERRY_BOARDS);
        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.CRIMSON_BOARDS);
        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.DARK_OAK_BOARDS);
        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.JUNGLE_BOARDS);
        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.MANGROVE_BOARDS);
        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.OAK_BOARDS);
        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.SPRUCE_BOARDS);
        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.WARPED_BOARDS);

        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.CHECKERED_TILES);
        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.DARK_CHECKERED_TILES);
        blockStateModelGenerator.registerSimpleCubeAll(ElleBlocks.KITCHEN_TILES);

        // Remaining blocks are too complex to datagen.
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ElleItems.GELATITE, Models.GENERATED);
        itemModelGenerator.register(ElleItems.GEL_BALL, Models.GENERATED);
        itemModelGenerator.register(ElleItems.GEL_BOTTLE, Models.GENERATED);

        itemModelGenerator.register(ElleItems.THROWING_GLOVE, Models.HANDHELD);
        itemModelGenerator.register(ElleItems.NETHERITE_THROWING_GLOVE, Models.HANDHELD);
        itemModelGenerator.register(ElleItems.SLIME_STAFF, Models.HANDHELD);

        // Remaining items are too complex to datagen.
    }
}
