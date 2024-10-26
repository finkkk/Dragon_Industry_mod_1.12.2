package com.finkkk.dragonindustry;

import com.finkkk.dragonindustry.Item.ModItems;
import com.finkkk.dragonindustry.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class RegisterUtil {
    public static void InitItem(Item item,String name){
        item.setUnlocalizedName(name);
        item.setRegistryName(name);
        ModItems.ITEM_LIST.add(item);
    }

    public static void InitBlock(Block block, String name){
        block.setUnlocalizedName(name);
        block.setRegistryName(name);
        ModBlocks.BLOCK_LIST.add(block);

        ItemBlock itemBlock = new ItemBlock(block);
        RegisterUtil.InitItem(itemBlock,name);
    }
}
