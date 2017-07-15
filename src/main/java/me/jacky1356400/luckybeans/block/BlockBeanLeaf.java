package me.jacky1356400.luckybeans.block;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBeanLeaf extends BlockLeaves implements IHasModel {

	public BlockBeanLeaf() {
		setRegistryName("bean_leaf");
		setUnlocalizedName(Data.MODID + ".bean_leaf");
		setCreativeTab(Data.TAB);
		setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, false).withProperty(DECAYABLE, false));
		Data.BLOCKS.add(this);
		Data.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return Arrays.asList(new ItemStack(this));
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if (world.isAirBlock(pos.down()))
			world.setBlockState(pos.down(), ModRegistry.MYSTBEANBLOCK.getDefaultState());
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(this);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModRegistry.BEANSAP);
	}

	@Override
	public EnumType getWoodType(int meta) {
		return EnumType.OAK;
	}

	@Override
	public int getMetaFromState(IBlockState state) {//false, false = 0; true, false = 1; false, true = 2; true, true = 3;
		int meta = 0;
		if (state.getValue(CHECK_DECAY))
			meta++;
		if (state.getValue(DECAYABLE))
			meta += 2;
		return meta;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(CHECK_DECAY, meta % 2 == 1).withProperty(DECAYABLE, meta >= 2);

	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
	}

}
