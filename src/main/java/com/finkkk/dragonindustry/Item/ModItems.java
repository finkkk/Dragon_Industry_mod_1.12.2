package com.finkkk.dragonindustry.Item;

import com.finkkk.dragonindustry.ModGroups;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.rmi.registry.Registry;

@Mod.EventBusSubscriber
public class ModItems {
    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event){
        event.getRegistry().register(new Item().setRegistryName("test3").setCreativeTab(ModGroups.DRAGON_INDUSTRY).setUnlocalizedName("test3"));
    }
}
