package ellestuff.ellethings.registries;

import ellestuff.ellethings.ElleThings;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ElleBallMaterials {
    public static MiningBallMaterial getById(String id) {
        Identifier identifier = new Identifier(id);
        MiningBallMaterial pattern = ElleRegistries.BALL_MATERIAL.get(identifier);

        return pattern != null ? pattern : ElleRegistries.BALL_MATERIAL.get(new Identifier(ElleThings.MODID, "none"));
    }

    public static MiningBallMaterial getById(String namespace, String id) {
        return getById(namespace + ":" + id);
    }

    public static final MiningBallMaterial IRON = ElleRegistries.register("iron", Items.IRON_INGOT, 0xffffff);
    public static final MiningBallMaterial DIAMOND = ElleRegistries.register("diamond", Items.DIAMOND, 0x4aedd9);
}
