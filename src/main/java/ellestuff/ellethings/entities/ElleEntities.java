package ellestuff.ellethings.entities;

import ellestuff.ellethings.ElleThings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ElleEntities {
    public static final EntityType<SlimeBallEntity> SLIME_BALL_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(ElleThings.MODID, "slime_ball"),
            FabricEntityTypeBuilder.<SlimeBallEntity>create(SpawnGroup.MISC, SlimeBallEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build()
    );

    public static final EntityType<MagmaCreamEntity> MAGMA_CREAM_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(ElleThings.MODID, "magma_cream"),
            FabricEntityTypeBuilder.<MagmaCreamEntity>create(SpawnGroup.MISC, MagmaCreamEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build()
    );

    public static final EntityType<FireworkStarEntity> FIREWORK_STAR_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(ElleThings.MODID, "firework_star"),
            FabricEntityTypeBuilder.<FireworkStarEntity>create(SpawnGroup.MISC, FireworkStarEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build()
    );
}
