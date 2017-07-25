package me.jacky1356400.luckybeans.helper;

public class ColorHelper {

    public static int getR(int col) {
        return (col & 0xFF0000) >> 16;
    }

    public static int getG(int col) {
        return (col & 0xFF00) >> 8;
    }

    public static int getB(int col) {
        return col & 0xFF;
    }

    public static float getRF(int col) {
        return getR(col) / 255.0F;
    }

    public static float getGF(int col) {
        return getG(col) / 255.0F;
    }

    public static float getBF(int col) {
        return getB(col) / 255.0F;
    }

}
