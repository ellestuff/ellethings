package ellestuff.ellethings.datagen;

import ellestuff.ellethings.blocks.ElleBlocks;
import ellestuff.ellethings.items.ElleItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class ElleRecipeProvider extends FabricRecipeProvider {

    public ElleRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerReversibleCompactingRecipes(exporter,
                RecipeCategory.MISC, ElleItems.GELATITE,
                RecipeCategory.BUILDING_BLOCKS, ElleBlocks.GELATITE_BLOCK);

        offerNetheriteUpgradeRecipe(exporter, ElleItems.THROWING_GLOVE, RecipeCategory.COMBAT, ElleItems.NETHERITE_THROWING_GLOVE);
    }
}
