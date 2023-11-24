package ellestuff.ellethings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

import static ellestuff.ellethings.ElleThings.MAGMA_CREAM_PROJECTILE;
import static ellestuff.ellethings.ElleThings.SLIME_BALL_PROJECTILE;

public class ElleClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(SLIME_BALL_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(MAGMA_CREAM_PROJECTILE, FlyingItemEntityRenderer::new);
    }
}
