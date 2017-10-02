package me.jacky1356400.luckybeans.block;

import me.jacky1356400.luckybeans.LuckyBeans;
import me.jacky1356400.luckybeans.util.IHasModel;
import me.jacky1356400.luckybeans.worldgen.BeanTreeGen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class BlockBeanSapling extends BlockBush implements IGrowable, IHasModel {

    private static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    public BlockBeanSapling() {
		setSoundType(SoundType.PLANT);
		setHardness(0.0f);
		setRegistryName("bean_sapling");
		setUnlocalizedName(LuckyBeans.MODID + ".bean_sapling");
		setCreativeTab(LuckyBeans.TAB);
		LuckyBeans.BLOCKS.add(this);
		LuckyBeans.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
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

    @Override @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SAPLING_AABB;
    }

	private boolean isSuitableSoilBlock(Block soilBlock) {
		return soilBlock == Blocks.GRASS || soilBlock == Blocks.DIRT || soilBlock == Blocks.FARMLAND;
	}

	@Override @ParametersAreNonnullByDefault
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			super.updateTick(worldIn, pos, state, rand);

			if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
				this.grow(worldIn, rand, pos, state);
			}
		}
	}

    public static void worldGenTrees(World world, BlockPos pos) {
        int posX = pos.getX() + world.rand.nextInt(8) - world.rand.nextInt(8);
        int posY = pos.getY() + world.rand.nextInt(4) - world.rand.nextInt(4);
        int posZ = pos.getZ() + world.rand.nextInt(8) - world.rand.nextInt(8);
        final BlockPos newPos = new BlockPos(posX, posY, posZ);

        new BeanTreeGen(5).generate(world, world.rand, newPos);
    }

	@Override @ParametersAreNonnullByDefault
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override @ParametersAreNonnullByDefault
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return rand.nextFloat() < 0.45D;
	}

	@Override @ParametersAreNonnullByDefault
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
        if (!TerrainGen.saplingGrowTree(world, rand, pos)) {
            return;
        }
        world.setBlockToAir(pos);

        if (!new BeanTreeGen(5).generate(world, rand, pos)) {
            world.setBlockState(pos, state);
        }
	}

	@Override @Nonnull
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override @Nonnull @ParametersAreNonnullByDefault
	public IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos) {
		final IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != this)
			return getDefaultState();
		return state;
	}

}
