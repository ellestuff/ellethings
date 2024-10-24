package ellestuff.ellethings.util;

import ellestuff.ellethings.ElleThings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ElleTags {
    public static class Blocks {
        public static final TagKey<Block> BOARDS = createTag("boards");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(ElleThings.MODID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(ElleThings.MODID, name));
        }
    }
}
