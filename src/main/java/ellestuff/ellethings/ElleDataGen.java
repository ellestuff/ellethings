package ellestuff.ellethings;
import ellestuff.ellethings.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ElleDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(ElleBlockTagProvider::new);
        pack.addProvider(ElleItemTagProvider::new);
        pack.addProvider(ElleLootTableProvider::new);
        pack.addProvider(ElleModelProvider::new);
        pack.addProvider(ElleRecipeProvider::new);
    }
}
