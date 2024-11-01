package ellestuff.ellethings.effects;

import ellestuff.ellethings.ElleThings;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ElleStatusEffects {

    public static final StatusEffect LUCIDITY = register("lucidity", (new LucidityStatusEffect()));

    private static StatusEffect register(String id, StatusEffect entry) {
        return (StatusEffect) Registry.register(Registries.STATUS_EFFECT, new Identifier(ElleThings.MODID, id), entry);
    }
}
