package me.jacky1356400.luckybeans.item;

import me.jacky1356400.luckybeans.util.Data;
import me.jacky1356400.luckybeans.util.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Random;

public class ItemBean extends ItemFood implements IHasModel {

    private PotionEffect[] effects = new PotionEffect[2];

	public ItemBean() {
        super(4, 4, false);
		setRegistryName(Data.MODID + ":bean");
		setUnlocalizedName(Data.MODID + ".bean");
		setCreativeTab(Data.TAB);
		setHasSubtypes(true);
		setAlwaysEdible();
		Data.ITEMS.add(this);

        effects[0] = new PotionEffect(MobEffects.ABSORPTION, 20, 1);
        effects[1] = new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1);
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
    public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if(!world.isRemote) {
            int r = new Random().nextInt(effects.length);
            PotionEffect effect = effects[r];
            player.addPotionEffect(new PotionEffect(effect.getPotion(), effect.getDuration(), effect.getAmplifier()));
        }
    }

}
