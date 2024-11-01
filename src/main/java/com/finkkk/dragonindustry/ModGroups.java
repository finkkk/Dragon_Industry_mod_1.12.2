package com.finkkk.dragonindustry;

import com.finkkk.dragonindustry.Item.ModItems;
import com.finkkk.dragonindustry.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ModGroups {
    public static final CreativeTabs DRAGON_INDUSTRY = new CreativeTabs(CreativeTabs.getNextID(),"dragon_industry") {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(ModBlocks.TEST_CONTAINER);
        }
    };
}
