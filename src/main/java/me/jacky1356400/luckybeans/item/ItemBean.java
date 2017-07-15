package me.jacky1356400.luckybeans.item;

import me.jacky1356400.luckybeans.util.Data;
import me.jacky1356400.luckybeans.util.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;

public class ItemBean extends Item implements IHasModel {

	public ItemBean() {
		setRegistryName(Data.MODID + ":bean");
		setUnlocalizedName(Data.MODID + ".bean");
		setCreativeTab(Data.TAB);
		setHasSubtypes(true);
		Data.ITEMS.add(this);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			for (int i = 0; i < 16; i++) {
				items.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Override
	public void initModel(ModelRegistryEvent e) {
		for (int i = 0; i < 16; i++) {
			ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(getRegistryName(), "meta=" + i));
		}
	}

}
