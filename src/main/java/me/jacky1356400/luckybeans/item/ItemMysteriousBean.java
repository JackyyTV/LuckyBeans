package me.jacky1356400.luckybeans.item;

import me.jacky1356400.luckybeans.LuckyBeans;
import me.jacky1356400.luckybeans.init.ModRegistry;
import me.jacky1356400.luckybeans.util.IHasModel;
import me.jacky1356400.luckybeans.util.RainbowTextGen;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemMysteriousBean extends Item implements IHasModel {

	public ItemMysteriousBean() {
		setRegistryName(LuckyBeans.MODID + ":mysterious_bean");
		setUnlocalizedName(LuckyBeans.MODID + ".mysterious_bean");
		setCreativeTab(LuckyBeans.TAB);
		LuckyBeans.ITEMS.add(this);
	}

    @SideOnly(Side.CLIENT)
    public FontRenderer getFontRenderer(ItemStack stack) {
        return RainbowTextGen.INSTANCE.init(true);
    }

	@Override @Nonnull
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return giveBean(world, pos, player, player.getHeldItem(hand)) ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
	}

	@Override @Nonnull
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
		boolean result = giveBean(world, player.getPosition(), player, player.getHeldItem(hand));
		return ActionResult.newResult(result ? EnumActionResult.SUCCESS : EnumActionResult.FAIL, player.getHeldItem(hand));
	}

	private static boolean giveBean(World world, BlockPos pos, EntityPlayer player, ItemStack stack) {
		if (player.addItemStackToInventory(new ItemStack(ModRegistry.BEAN, 1, world.rand.nextInt(16)))) {
			stack.shrink(1);
			return true;
		}
		return false;
	}

}
