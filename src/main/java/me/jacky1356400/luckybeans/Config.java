package me.jacky1356400.luckybeans;

import me.jacky1356400.luckybeans.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;

public class Config {

	private static final String CATEGORY_GENERAL = "general";

	public static boolean beanTreesGen;
	public static int beanTreesGenChance;
    public static boolean giveBeanCommand;

	public static void readConfig() {
		Configuration cfg = CommonProxy.config;
		try {
			cfg.load();
			initConfig(cfg);
		} catch (Exception e) {
			LuckyBeans.logger.error("Error caught when loading config!", e);
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}

	private static void initConfig(Configuration cfg) {
		beanTreesGen = cfg.getBoolean("beanTreesGen", CATEGORY_GENERAL, true, "If true, enables Bean trees world gen");
		beanTreesGenChance = cfg.getInt("beanTreesGenChance", CATEGORY_GENERAL, 25, 1, 10000, "What's the chance (1 in whatever number is set) to spawn a Bean tree per chunk?");
        giveBeanCommand = cfg.getBoolean("giveBeanCommand", CATEGORY_GENERAL, true, "If true, enables command for giving Mysterious Lucky Beans (This is made for streamers)");
	}

}
