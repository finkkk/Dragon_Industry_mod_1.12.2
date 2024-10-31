package com.finkkk.dragonindustry.block.testcontainer;

import com.finkkk.dragonindustry.Item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

import static com.finkkk.dragonindustry.tileentity.TileEntityTestContainer.INPUT_SLOT;
import static com.finkkk.dragonindustry.tileentity.TileEntityTestContainer.OUTPUT_SLOT;

public class TestItemHandler extends ItemStackHandler {

    // 默认构造方法，设置默认的槽位数量（例如 2）
    public TestItemHandler() {
        this(1); // 默认设为 1 个槽位
    }
    // 构造方法，接收槽位数量
    public TestItemHandler(int slots) {
        super(slots); // 将槽位数量传给父类 ItemStackHandler
    }

    public void processInputOutput(){
        ItemStack inputStack = getStackInSlot(INPUT_SLOT); // 输入物品
        ItemStack outputStack = getStackInSlot(OUTPUT_SLOT); // 输出物品

        // 如果输入槽有物品
        if (!inputStack.isEmpty()) {
            // 如果输入槽为苹果
            if (inputStack.getItem() == ModItems.TEST3) {
                // 如果输出槽为空
                if (outputStack.isEmpty()) {
                    setStackInSlot(OUTPUT_SLOT, new ItemStack(Blocks.STONE)); // 输出石头
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
