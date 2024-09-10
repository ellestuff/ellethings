package ellestuff.ellethings.enchantments;

import ellestuff.ellethings.ElleThings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ElleEnchantments {
    public static final Enchantment REACHING = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier(ElleThings.MODID, "reaching"),
            new ReachingEnchantment()
    );

    public static void registerEnchantments() {
        // This can be left empty, the static block will take care of registration.
    }
}
