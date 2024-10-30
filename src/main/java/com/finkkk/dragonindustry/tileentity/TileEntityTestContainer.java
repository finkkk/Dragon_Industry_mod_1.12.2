package com.finkkk.dragonindustry.tileentity;

import akka.util.Index;
import com.finkkk.dragonindustry.Item.ModItems;
import com.finkkk.dragonindustry.block.ModBlocks;
import com.finkkk.dragonindustry.container.TestContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TileEntityTestContainer extends TileEntityLockable implements ITickable, ISidedInventory
{

    private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {1};
    private NonNullList<ItemStack> testContainerItemStacks = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);

    // 获取物品槽数量
    public int getSizeInventory()
    {
        return this.testContainerItemStacks.size();
    }

    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.testContainerItemStacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }
    public ItemStack getStackInSlot(int index)
    {
        return this.testContainerItemStacks.get(index);
    }

    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.testContainerItemStacks, index, count);
    }
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.testContainerItemStacks, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.testContainerItemStacks.set(index, stack);
        this.markDirty();
    }

    public static void registerFixesFurnace(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityTestContainer.class, new String[] {"Items"}));
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.testContainerItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.testContainerItemStacks);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.testContainerItemStacks);

        return compound;
    }
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public void update()
    {

        if (!this.world.isRemote)
        {
            ItemStack inputStack = this.testContainerItemStacks.get(0); // 输入物品
            ItemStack outputStack = this.testContainerItemStacks.get(1); // 输出物品

            // 如果输入槽有物品
            if (!inputStack.isEmpty())
            {
                // 如果输入槽为苹果
                if (inputStack.getItem() == Items.APPLE)
                {
                    // 如果输出槽为空
                    if (outputStack.isEmpty())
                    {
                        this.testContainerItemStacks.set(1, new ItemStack(Blocks.STONE)); // 输出石头
                    }
                    // 如果输入槽已有石头且未满64个
                    else if (outputStack.getItem() == Item.getItemFromBlock(Blocks.STONE) && outputStack.getCount() < outputStack.getMaxStackSize())
                    {
                        outputStack.grow(1); // 增加输出槽的石头数量
                    }
                    inputStack.shrink(1); // 消耗泥土
                    this.markDirty();     // 标记已修改，保存数据
                }

            }

        }

    }

    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    public void openInventory(EntityPlayer player)
    {
    }

    public void closeInventory(EntityPlayer player)
    {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return index == 0;
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
            return SLOTS_BOTTOM;
        } else {
            return SLOTS_TOP;
        }
    }


    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return index == 0;
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        return index == 1;
    }

    public String getGuiID()
    {
        return "dragonindustry:test_container";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new TestContainer(playerInventory, this);
    }


    public void clear()
    {
        this.testContainerItemStacks.clear();
    }

    @Override
    public String getName() {
        return "Test Container";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, EnumFacing.WEST);

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
}