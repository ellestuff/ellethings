package ellestuff.ellethings.registries;

import ellestuff.ellethings.ElleThings;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.util.Identifier;

public class ElleRegistries {
    public static final RegistryKey<Registry<MiningBallMaterial>> BALL_MATERIAL_REGISTRY_KEY = RegistryKey.ofRegistry(new Identifier(ElleThings.MODID, "mining_ball"));
    public static final SimpleRegistry<MiningBallMaterial> BALL_MATERIAL = FabricRegistryBuilder.createDefaulted(BALL_MATERIAL_REGISTRY_KEY, new Identifier(ElleThings.MODID, "none"))
            .buildAndRegister();

    public static MiningBallMaterial register(String name, Item item, int colour) {
        return Registry.register(BALL_MATERIAL, new Identifier(ElleThings.MODID, name), new MiningBallMaterial(name, item, colour));
    }
}