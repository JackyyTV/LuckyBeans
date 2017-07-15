package me.jacky1356400.luckybeans.item;

import me.jacky1356400.luckybeans.util.Data;
import me.jacky1356400.luckybeans.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemBean extends Item implements IHasModel {

    public ItemBean(){
        setRegistryName(Data.MODID + ":bean");
        setUnlocalizedName(Data.MODID + ".bean");
        setCreativeTab(Data.TAB);
        setHasSubtypes(true);
        Data.ITEMS.add(this);
    }

    public boolean isFull3D(){
        return true;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (int i = 0; i < 16; ++i)
            {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

}
