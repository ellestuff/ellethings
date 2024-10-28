package ellestuff.ellethings.dimension;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import ellestuff.ellethings.ElleThings;
import ellestuff.ellethings.dimension.MallChunkGenerator;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class ElleDimensions {
    public static final Codec<MallChunkGenerator> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter(MallChunkGenerator::getBiomeSource)
            ).apply(instance, MallChunkGenerator::new));
}
