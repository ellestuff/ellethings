package ellestuff.ellethings.blocks;

import ellestuff.ellethings.ElleThings;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ElleBlocks {
    public static final Block GELATITE_BLOCK = registerBlock("gelatite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK)
                    .mapColor(MapColor.PINK)));

    public static final Block COLOURED_LAMP = registerBlock("coloured_lamp",
            new ColouredLampBlock(FabricBlockSettings.create().luminance(ColouredLampBlock.STATE_TO_LUMINANCE).strength(0.3F).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::always)));

    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(ElleThings.MODID, name), block);
    }

    public static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(ElleThings.MODID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerElleBlocks() {
        // Building Blocks
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Items.EMERALD_BLOCK, GELATITE_BLOCK);
        });

        // Redstone
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            content.addAfter(Items.REDSTONE_LAMP, COLOURED_LAMP);
        });
    }
}
