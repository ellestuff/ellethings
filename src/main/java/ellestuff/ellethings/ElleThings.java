package ellestuff.ellethings;

import ellestuff.ellethings.dimension.MallChunkGenerator;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        Registry.register(Registries.CHUNK_GENERATOR, new Identifier(ElleThings.MODID, "mall"), MallChunkGenerator.CODEC);
    }
}