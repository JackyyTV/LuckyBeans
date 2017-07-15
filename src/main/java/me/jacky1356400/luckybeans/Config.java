package me.jacky1356400.luckybeans;

import me.jacky1356400.luckybeans.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;

public class Config {

    private static final String CATEGORY_GENERAL = "general";

    public static boolean beanTreesGen;
    public static String[] blacklistedBiomes;
    public static int beanTreesGenMin;
    public static int beanTreesGenMax;

    public static void readConfig(){
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initConfig(cfg);
        } catch (Exception e) {
            LuckyBeans.logger.error("Error caught when loading config!", e);
        } finally {
            if(cfg.hasChanged()){
                cfg.save();
            }
        }
    }

    private static void initConfig(Configuration cfg){
        beanTreesGen = cfg.getBoolean("beanTreesGen", CATEGORY_GENERAL, true, "If true, enables Bean trees world gen");
        blacklistedBiomes = cfg.getStringList("blacklistedBiomes", CATEGORY_GENERAL, new String[0], "Blacklisted Biomes for Bean trees world gen");
        beanTreesGenMin = cfg.getInt("beanTreesGenMin", CATEGORY_GENERAL, 60, 0, 255, "Minimum height for Bean trees to spawn");
        beanTreesGenMax = cfg.getInt("beanTreesGenMax", CATEGORY_GENERAL, 100, 0, 255, "Maximum height for Bean trees to spawn");
    }

}
