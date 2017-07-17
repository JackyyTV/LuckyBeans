package me.jacky1356400.luckybeans.proxy;

import java.io.File;

import me.jacky1356400.luckybeans.Config;
import me.jacky1356400.luckybeans.LuckyBeans;
import me.jacky1356400.luckybeans.init.ModRegistry;
import me.jacky1356400.luckybeans.worldgen.BeanTreeWorldGen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public static Configuration config;

	public void preInit(FMLPreInitializationEvent e) {
		File configDir = e.getModConfigurationDirectory();
		config = new Configuration(new File(configDir.getPath(), "luckybeans.cfg"));
		Config.readConfig();
		MinecraftForge.EVENT_BUS.register(new ModRegistry());
	}

	public void init(FMLInitializationEvent e) {
        GameRegistry.registerWorldGenerator(new BeanTreeWorldGen(), 0);
	}

	public void postInit(FMLPostInitializationEvent e) {
	}

}
