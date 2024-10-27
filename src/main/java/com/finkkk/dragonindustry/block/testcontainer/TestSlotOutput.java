package com.finkkk.dragonindustry.block.testcontainer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class TestSlotOutput extends Slot {
    public TestSlotOutput(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition){
        super(inventoryIn,slotIndex,xPosition,yPosition);
    }
    /**
     * 禁止将物品放入该槽位
     */
    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    /**
     * 允许玩家从槽位中取出物品
     */
    @Override
    public ItemStack onTake(EntityPlayer player, ItemStack stack) {
        super.onTake(player, stack);
        return stack;
    }
}
