package ellestuff.ellethings.datagen;

import ellestuff.ellethings.ElleThings;
import ellestuff.ellethings.items.ElleItems;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.item.trim.ArmorTrimMaterials;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class ElleTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> GELATITE = of("gelatite");

    private static RegistryKey<ArmorTrimMaterial> of(String id) {
        return RegistryKey.of(RegistryKeys.TRIM_MATERIAL, new Identifier(ElleThings.MODID, id));
    }
}
