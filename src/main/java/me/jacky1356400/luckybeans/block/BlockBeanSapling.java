package me.jacky1356400.luckybeans.block;

import java.util.Random;

import me.jacky1356400.luckybeans.init.ModRegistry;
import me.jacky1356400.luckybeans.util.Data;
import me.jacky1356400.luckybeans.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class BlockBeanSapling extends BlockBush implements IGrowable, IHasModel {

	public static final WorldGenTrees BEAN_TREE = new WorldGenTrees(true, 5, Blocks.LOG.getDefaultState(), ModRegistry.BEANLEAF.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false), false);

	public BlockBeanSapling() {
		setSoundType(SoundType.PLANT);
		setHardness(0.0f);
		setRegistryName("bean_sapling");
		setUnlocalizedName(Data.MODID + ".bean_sapling");
		setCreativeTab(Data.TAB);
		Data.BLOCKS.add(this);
		Data.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		Block soilBlock = world.getBlockState(pos.down()).getBlock();

		return this.isSuitableSoilBlock(soilBlock);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	private boolean isSuitableSoilBlock(Block soilBlock) {
		return soilBlock == Blocks.GRASS || soilBlock == Blocks.DIRT || soilBlock == Blocks.FARMLAND;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			super.updateTick(worldIn, pos, state, rand);

			if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
				this.grow(worldIn, rand, pos, state);
			}
		}
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return rand.nextFloat() < 0.45D;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		world.setBlockToAir(pos);
		BEAN_TREE.generate(world, world.rand, pos);
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos) {
		final IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != this)
			return getDefaultState();
		return state;
	}

}
