package com.finkkk.dragonindustry.Item;

import com.finkkk.dragonindustry.Item.ModItems;
import com.finkkk.dragonindustry.RegisterUtil;
import net.minecraft.item.Item;

import java.rmi.registry.Registry;

public class ItemBase extends Item {
    public ItemBase(String name){
        super();
        RegisterUtil.InitItem(this,name);
    }
}
