package com.finkkk.dragonindustry.block;

import com.finkkk.dragonindustry.DragonindustryMod;
import com.finkkk.dragonindustry.ModGroups;
import com.finkkk.dragonindustry.block.testcontainer.TestBlockFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = DragonindustryMod.MODID)
public class ModBlocks extends Blocks {

    public static final List<Block> BLOCK_LIST = new ArrayList<>();

    public static final Block TEST_CONTAINER = new TestBlockFurnace("test_container",Material.ROCK).setCreativeTab(ModGroups.DRAGON_INDUSTRY);
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(BLOCK_LIST.toArray(new Block[0]));
    }
}
