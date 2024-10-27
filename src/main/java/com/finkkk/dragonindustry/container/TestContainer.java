package com.finkkk.dragonindustry.container;

import com.finkkk.dragonindustry.block.testcontainer.TestSlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TestContainer extends Container
{
    private final IInventory tileTest;

    public TestContainer(InventoryPlayer playerInventory, IInventory testInventory)
    {
        this.tileTest = testInventory;
        this.addSlotToContainer(new Slot(testInventory, 0, 56, 17));
        this.addSlotToContainer(new TestSlotOutput(testInventory, 1, 116, 35));


        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

    }

    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileTest);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);


        }
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileTest.setField(id, data);
    }
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileTest.isUsableByPlayer(playerIn);
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        System.out.println("transferStackInSlot 转移槽位的索引 called with index: " + index);
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            System.out.println("Slot " + index + " before merge: " + itemstack1);

            if (index == 1)
            {
                if (!this.mergeItemStack(itemstack1, 2, this.inventorySlots.size(), true))
                {
                    System.out.println("Failed to merge itemstack1 from output slot.");
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if ( index == 0)
            {
                // 尝试合并输入物品
                if (!this.mergeItemStack(itemstack1, 2, this.inventorySlots.size(), false)) { // 假设玩家库存在 2 到 38 的索引
                    return ItemStack.EMPTY;
                }
            }
            else
            {
                // 只允许从玩家库存转移到输入槽
                if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}