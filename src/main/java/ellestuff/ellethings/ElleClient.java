package ellestuff.ellethings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;

import static ellestuff.ellethings.entities.ElleEntities.*;

public class ElleClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(SLIME_BALL_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(MAGMA_CREAM_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(FIREWORK_STAR_PROJECTILE, FlyingItemEntityRenderer::new);
    }
}
