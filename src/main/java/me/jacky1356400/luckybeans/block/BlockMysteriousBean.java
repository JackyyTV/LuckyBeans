package me.jacky1356400.luckybeans.block;

import me.jacky1356400.luckybeans.util.Data;
import me.jacky1356400.luckybeans.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockMysteriousBean extends Block implements IHasModel {

    public BlockMysteriousBean() {
        super(Material.WOOD);
        setSoundType(SoundType.WOOD);
        setHardness(0.1f);
        setRegistryName(Data.MODID + ":mysterious_bean_block");
        setUnlocalizedName(Data.MODID + ".mysterious_bean_block");
        Data.BLOCKS.add(this);
    }

}
