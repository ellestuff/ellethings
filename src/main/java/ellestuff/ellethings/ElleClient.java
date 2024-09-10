package ellestuff.ellethings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;

import static ellestuff.ellethings.ElleThings.*;
//import static ellestuff.ellethings.blocks.CanvasBlock.*;
import static ellestuff.ellethings.blocks.ElleBlocks.*;
import static ellestuff.ellethings.items.ElleItems.*;

public class ElleClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(SLIME_BALL_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(MAGMA_CREAM_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(FIREWORK_STAR_PROJECTILE, FlyingItemEntityRenderer::new);

        ModelPredicateProviderRegistry.register(
                REDSTONE_GLOVE, new Identifier("powering"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );
    }
}
