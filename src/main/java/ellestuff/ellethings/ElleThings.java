package ellestuff.ellethings;

import ellestuff.ellethings.entities.FireworkStarEntity;
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

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import static ellestuff.ellethings.blocks.ElleBlocks.registerElleBlocks;
import static ellestuff.ellethings.items.ElleItems.registerElleItems;
import static ellestuff.ellethings.networking.ElleNetworking.registerEllePackets;

public class ElleThings implements ModInitializer {
    public static final String MODID = "ellethings";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        registerElleItems();
        registerElleBlocks();
        registerEllePackets();
    }
}
