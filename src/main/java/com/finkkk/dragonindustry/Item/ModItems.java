package com.finkkk.dragonindustry.Item;

import com.finkkk.dragonindustry.ModGroups;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class ModItems {
    public static final List<Item> ITEM_LIST = new ArrayList<>();
    public static final Item TEST3 = new ItemBase("test3").setCreativeTab(ModGroups.DRAGON_INDUSTRY);
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(ITEM_LIST.toArray(new Item[0]));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void modelRegister(ModelRegistryEvent event){
        for (Item item : ITEM_LIST){
            ModelLoader.setCustomModelResourceLocation(
                    item,
                    0,
                    new ModelResourceLocation(item.getRegistryName(),"inventory")
            );
        }
    }
}
