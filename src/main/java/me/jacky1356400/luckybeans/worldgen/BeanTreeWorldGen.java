package me.jacky1356400.luckybeans.worldgen;

import me.jacky1356400.luckybeans.Config;
import me.jacky1356400.luckybeans.block.BlockBeanSapling;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class BeanTreeWorldGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        final int xChunk = chunkX * 16 + 8, zChunk = chunkZ * 16 + 8;
        final int xCh = chunkX * 16 + random.nextInt(16);
        final int yCh = random.nextInt(128) + 64;
        final int zCh = chunkZ * 16 + random.nextInt(16);

        final Biome biome = world.getBiomeForCoordsBody(new BlockPos(xChunk + 16, 0, zChunk + 16));
        final BlockPos blockPos = new BlockPos(xCh, yCh, zCh);

        if ((BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS))
                || (BiomeDictionary.hasType(biome, BiomeDictionary.Type.WET))
                || (BiomeDictionary.hasType(biome, BiomeDictionary.Type.HILLS))
                || (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MOUNTAIN))
                || (BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST))) {
            if (Config.beanTreesGen) {
                generateBeanTrees(world, blockPos);
            }
        }

    }

    private void generateBeanTrees(World world, BlockPos pos) {
        BlockBeanSapling.worldGenTrees(world, pos);
    }
}