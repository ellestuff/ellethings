package ellestuff.ellethings;

import ellestuff.ellethings.items.RedstoneTransformerItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;

import static ellestuff.ellethings.blocks.ElleBlocks.TRANSFORMER;
import static ellestuff.ellethings.blocks.ElleBlocks.TRANSFORMER_ITEM;
import static ellestuff.ellethings.entities.ElleEntities.*;

public class ElleClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        // Texture Stuff
        BlockRenderLayerMap.INSTANCE.putBlock(TRANSFORMER, RenderLayer.getCutout());

        ModelPredicateProviderRegistry.register(TRANSFORMER_ITEM, new Identifier("power"),
                (stack, world, entity, seed) -> (float) RedstoneTransformerItem.getPower(stack)/20
        );

        // Entity Renderers
        EntityRendererRegistry.register(SLIME_BALL_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(MAGMA_CREAM_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(FIREWORK_STAR_PROJECTILE, FlyingItemEntityRenderer::new);
    }
}
