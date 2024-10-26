package com.finkkk.dragonindustry.block;

import com.finkkk.dragonindustry.RegisterUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {
    public BlockBase(String name,Material materialIn) {
        super(materialIn);
        RegisterUtil.InitBlock(this,name);
    }
}
