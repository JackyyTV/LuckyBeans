package me.jacky1356400.luckybeans;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.jacky1356400.luckybeans.proxy.CommonProxy;
import me.jacky1356400.luckybeans.util.Data;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Data.MODID, version = Data.VERSION, name = Data.MODNAME, useMetadata = true, dependencies = Data.DEPENDS)
public class LuckyBeans {

	public static Logger logger = LogManager.getLogger("LuckyBeans");

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

}