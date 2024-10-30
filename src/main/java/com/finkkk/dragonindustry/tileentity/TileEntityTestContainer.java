package com.finkkk.dragonindustry.tileentity;

import com.finkkk.dragonindustry.Item.ModItems;
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

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private ItemStackHandler itemStackHandler = new ItemStackHandler(2) {
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
            ItemStack inputStack = itemStackHandler.getStackInSlot(INPUT_SLOT); // 输入物品
            ItemStack outputStack = itemStackHandler.getStackInSlot(OUTPUT_SLOT); // 输出物品

            // 如果输入槽有物品
            if (!inputStack.isEmpty()) {
                // 如果输入槽为苹果
                if (inputStack.getItem() == ModItems.TEST3) {
                    // 如果输出槽为空
                    if (outputStack.isEmpty()) {
                        itemStackHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(Blocks.STONE)); // 输出石头
                        inputStack.shrink(1); // 消耗苹果
                    }
                    // 如果输入槽已有石头且未满64个
                    else if (outputStack.getItem() == Item.getItemFromBlock(Blocks.STONE) && outputStack.getCount() < outputStack.getMaxStackSize()) {
                        outputStack.grow(1); // 增加输出槽的石头数量
                        inputStack.shrink(1); // 消耗苹果
                    }


                }
            }
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
        if (side == EnumFacing.DOWN) {
            return new int[]{OUTPUT_SLOT};
        } else {
            return new int[]{INPUT_SLOT};
        }
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return index == INPUT_SLOT;
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

    net.minecraftforge.items.IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == EnumFacing.DOWN) {
                return (T) handlerBottom;
            } else if (facing == EnumFacing.UP) {
                return (T) handlerTop;
            } else {
                return (T) handlerSide;
            }
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