package ellestuff.ellethings.datagen;

import ellestuff.ellethings.blocks.ElleBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ElleLootTableProvider extends FabricBlockLootTableProvider {
    public ElleLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        // Wooden Boards
        addDrop(ElleBlocks.ACACIA_BOARDS);
        addDrop(ElleBlocks.BIRCH_BOARDS);
        addDrop(ElleBlocks.BAMBOO_BOARDS);
        addDrop(ElleBlocks.CHERRY_BOARDS);
        addDrop(ElleBlocks.CRIMSON_BOARDS);
        addDrop(ElleBlocks.DARK_OAK_BOARDS);
        addDrop(ElleBlocks.JUNGLE_BOARDS);
        addDrop(ElleBlocks.MANGROVE_BOARDS);
        addDrop(ElleBlocks.OAK_BOARDS);
        addDrop(ElleBlocks.SPRUCE_BOARDS);
        addDrop(ElleBlocks.WARPED_BOARDS);

        // Tiles
        addDrop(ElleBlocks.CHECKERED_TILES);
        addDrop(ElleBlocks.DARK_CHECKERED_TILES);
        addDrop(ElleBlocks.KITCHEN_TILES);

        // Other
        addDrop(ElleBlocks.GELATITE_BLOCK);
        addDrop(ElleBlocks.COLOURED_LAMP);
    }
}
