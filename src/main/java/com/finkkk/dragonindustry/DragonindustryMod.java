package com.finkkk.dragonindustry;

import com.finkkk.dragonindustry.block.ModBlocks;
import com.finkkk.dragonindustry.block.testcontainer.TestItemHandler;
import com.finkkk.dragonindustry.block.testcontainer.TestStorage;
import com.finkkk.dragonindustry.tileentity.TileEntityTestContainer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = DragonindustryMod.MODID, name = DragonindustryMod.NAME, version = DragonindustryMod.VERSION)
public class DragonindustryMod
{
    public static final int GUI_TEST_CONTAINER = 2; // 为你的GUI分配一个ID
    public static final String MODID = "dragonindustry";
    public static final String NAME = "Dragon Industry Mod";
    public static final String VERSION = "0.2.2";

    private static Logger logger;

    @Mod.Instance
    public static DragonindustryMod instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        logger = event.getModLog();
        // 注册能力
        CapabilityManager.INSTANCE.register(TestItemHandler.class, new TestStorage(), TestItemHandler::new);
        // 注册 GUI Handler
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        // 注册 TileEntity
        GameRegistry.registerTileEntity(TileEntityTestContainer.class, new ResourceLocation("dragonindustry:test_container"));

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.TEST_CONTAINER), 0, new ModelResourceLocation("dragonindustry:test_container", "inventory"));

    }
}



