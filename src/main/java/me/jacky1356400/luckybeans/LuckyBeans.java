package me.jacky1356400.luckybeans;

import me.jacky1356400.luckybeans.command.CommandGiveBean;
import me.jacky1356400.luckybeans.init.ModRegistry;
import me.jacky1356400.luckybeans.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = LuckyBeans.MODID, version = LuckyBeans.VERSION, name = LuckyBeans.MODNAME, dependencies = LuckyBeans.DEPENDS, useMetadata = true)
public class LuckyBeans {

    public static final List<Item> ITEMS = new ArrayList<>();
    public static final List<Block> BLOCKS = new ArrayList<>();
    public static final String VERSION = "1.1";
    public static final String MODID = "luckybeans";
    public static final String MODNAME = "Lucky Beans";
    public static final String DEPENDS = "required-after:forge@[14.21.0.2387,);after:jei@[4.7.0,);";
    public static final CreativeTabs TAB = new CreativeTabs(MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModRegistry.MYSTBEAN);
        }
    };

    public static Logger logger = LogManager.getLogger(MODNAME);

	@SidedProxy(serverSide = "me.jacky1356400.luckybeans.proxy.CommonProxy", clientSide = "me.jacky1356400.luckybeans.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent e) {
        if (Config.giveBeanCommand) {
            e.registerServerCommand(new CommandGiveBean());
        }
    }

}
