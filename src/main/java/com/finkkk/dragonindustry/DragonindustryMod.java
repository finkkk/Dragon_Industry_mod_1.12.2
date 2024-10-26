package com.finkkk.dragonindustry;

import net.minecraft.client.gui.Gui;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = DragonindustryMod.MODID, name = DragonindustryMod.NAME, version = DragonindustryMod.VERSION)
public class DragonindustryMod
{
    public static final String MODID = "dragonindustry";
    public static final String NAME = "Dragon Industry Mod";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @Mod.Instance
    public static DragonindustryMod instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
