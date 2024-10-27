package com.finkkk.dragonindustry;

import com.finkkk.dragonindustry.container.TestContainer;
import com.finkkk.dragonindustry.gui.GuiTestContainer;
import com.finkkk.dragonindustry.tileentity.TileEntityTestContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof TileEntityTestContainer) {
            return new TestContainer(player.inventory, (IInventory) tileEntity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof TileEntityTestContainer) {
            return new GuiTestContainer(player.inventory, (IInventory) tileEntity);
        }
        return null;
    }
}
