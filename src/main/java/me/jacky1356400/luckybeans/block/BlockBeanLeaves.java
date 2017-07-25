package me.jacky1356400.luckybeans.block;

import me.jacky1356400.luckybeans.init.ModRegistry;
import me.jacky1356400.luckybeans.util.Data;
import me.jacky1356400.luckybeans.util.IHasModel;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BlockBeanLeaves extends BlockLeaves implements IHasModel {

	public BlockBeanLeaves() {
		setRegistryName("bean_leaves");
		setUnlocalizedName(Data.MODID + ".bean_leaves");
		setCreativeTab(Data.TAB);
		setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
		Data.BLOCKS.add(this);
		Data.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

    @Override @ParametersAreNonnullByDefault
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return true;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 5;
    }

    @Override @ParametersAreNonnullByDefault
    public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override @ParametersAreNonnullByDefault
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return Arrays.asList(new ItemStack(this));
	}

	@Override @Nonnull @ParametersAreNonnullByDefault
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(this);
	}

    @Override @Nonnull
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModRegistry.BEANSAP);
	}

	@Override @Nonnull
	public EnumType getWoodType(int meta) {
		return EnumType.OAK;
	}

	@Override
	public int getMetaFromState(IBlockState state) { //false, false = 0; true, false = 1; false, true = 2; true, true = 3;
		int meta = 0;
		if (state.getValue(CHECK_DECAY))
			meta++;
		if (state.getValue(DECAYABLE))
			meta += 2;
		return meta;
	}

	@Override @Nonnull @SuppressWarnings("deprecation")
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(CHECK_DECAY, meta % 2 == 1).withProperty(DECAYABLE, meta >= 2);

	}

	@Override @Nonnull
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
	}

}
