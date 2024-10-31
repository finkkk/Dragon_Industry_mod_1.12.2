package com.finkkk.dragonindustry.tileentity;

import com.finkkk.dragonindustry.Item.ModItems;
import com.finkkk.dragonindustry.block.testcontainer.TestItemHandler;
import com.finkkk.dragonindustry.container.TestContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;

public class TileEntityTestContainer extends TileEntityLockable implements ITickable, ISidedInventory
{

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    private TestItemHandler itemStackHandler = new TestItemHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            TileEntityTestContainer.this.markDirty();
        }
    };

    public int getInventoryStackLimit()
    {
        return 64;
    }
    @Override
    public void update() {
        if (!this.world.isRemote) {
            itemStackHandler.processInputOutput();
            this.markDirty();
        }

    }
    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }

    @Override
    public int getSizeInventory() {
        return itemStackHandler.getSlots();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return itemStackHandler.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return itemStackHandler.extractItem(index, count, false);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return itemStackHandler.extractItem(index, itemStackHandler.getStackInSlot(index).getCount(), false);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        itemStackHandler.setStackInSlot(index, stack);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        itemStackHandler.deserializeNBT(compound.getCompoundTag("Items"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("Items", itemStackHandler.serializeNBT());
        return compound;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index == INPUT_SLOT; // 只允许输入槽
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[]{INPUT_SLOT, OUTPUT_SLOT}; // 让所有面都可以访问输入和输出槽
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return index == INPUT_SLOT; // 允许所有面插入输入槽
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return index == OUTPUT_SLOT;
    }

    @Override
    public String getName() {
        return "Test Container";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    // 统一的 SidedInvWrapper 实例
    net.minecraftforge.items.IItemHandler handler = new SidedInvWrapper(this, null);

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) handler; // 所有面使用同一个 handler
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void clear() {
        itemStackHandler.setStackInSlot(INPUT_SLOT, ItemStack.EMPTY);
        itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return null;
    }

    @Override
    public String getGuiID() {
        return null;
    }
}