package me.jacky1356400.luckybeans.item;

import me.jacky1356400.luckybeans.util.Data;
import me.jacky1356400.luckybeans.util.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;

import java.util.List;

public class ItemBean extends ItemFood implements IHasModel {

	public ItemBean() {
        super(4, 4, false);
		setRegistryName(Data.MODID + ":bean");
		setUnlocalizedName(Data.MODID + ".bean");
		setCreativeTab(Data.TAB);
		setHasSubtypes(true);
		setAlwaysEdible();
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

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        for (int i = 0; i < 16; i++) {
            if (i == getMetadata(stack)) {
                tooltip.add(I18n.format(Data.MODID + ".tooltip.color" + i));
            }
        }
    }

    @Override
    public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if(!world.isRemote) {
            /*
            TODO: Add rewards for different kind of beans
            For the reward I've designed so far, go to:
            https://docs.google.com/spreadsheets/d/14mYCAsveFrjtDw8t_DId8u16kPytwj3GkX1Llkakg98/edit?usp=sharing
            */
        }
    }

}
