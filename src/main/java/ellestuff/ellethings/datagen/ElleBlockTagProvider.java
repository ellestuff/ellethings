package ellestuff.ellethings.datagen;

import ellestuff.ellethings.blocks.ElleBlocks;
import ellestuff.ellethings.util.ElleTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ElleBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ElleBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ElleTags.Blocks.BOARDS)
                .add(ElleBlocks.ACACIA_BOARDS)
                .add(ElleBlocks.BIRCH_BOARDS)
                .add(ElleBlocks.BAMBOO_BOARDS)
                .add(ElleBlocks.CHERRY_BOARDS)
                .add(ElleBlocks.CRIMSON_BOARDS)
                .add(ElleBlocks.DARK_OAK_BOARDS)
                .add(ElleBlocks.JUNGLE_BOARDS)
                .add(ElleBlocks.MANGROVE_BOARDS)
                .add(ElleBlocks.OAK_BOARDS)
                .add(ElleBlocks.SPRUCE_BOARDS)
                .add(ElleBlocks.WARPED_BOARDS);
        
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .forceAddTag(ElleTags.Blocks.BOARDS);
        
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ElleBlocks.CHECKERED_TILES)
                .add(ElleBlocks.KITCHEN_TILES)
                .add(ElleBlocks.DARK_CHECKERED_TILES)
                .add(ElleBlocks.GELATITE_BLOCK);
        
        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(ElleBlocks.GELATITE_BLOCK);
        
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ElleBlocks.GELATITE_BLOCK);
    }
}
