package ellestuff.ellethings;

import ellestuff.ellethings.entities.MagmaCreamEntity;
import ellestuff.ellethings.entities.SlimeBallEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static ellestuff.ellethings.items.ElleItems.registerElleItems;

public class ElleThings implements ModInitializer {
    public static final String MODID = "ellethings";

    public static final EntityType<SlimeBallEntity> SLIME_BALL_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MODID, "slime_ball"),
            FabricEntityTypeBuilder.<SlimeBallEntity>create(SpawnGroup.MISC, SlimeBallEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build()
    );

    public static final EntityType<MagmaCreamEntity> MAGMA_CREAM_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MODID, "magma_cream"),
            FabricEntityTypeBuilder.<MagmaCreamEntity>create(SpawnGroup.MISC, MagmaCreamEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build()
    );

    @Override
    public void onInitialize() {
        registerElleItems();
    }
}
