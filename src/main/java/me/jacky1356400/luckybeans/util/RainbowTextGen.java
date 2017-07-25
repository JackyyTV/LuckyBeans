package me.jacky1356400.luckybeans.util;

import me.jacky1356400.luckybeans.helper.ColorHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;

public class RainbowTextGen extends FontRenderer {

    public static RainbowTextGen INSTANCE = new RainbowTextGen();
    private float hue;
    private float r;
    private float g;
    private float b;
    private float a;
    private boolean firstLine;
    private boolean firstLineOnly;

    private RainbowTextGen() {
        super(Minecraft.getMinecraft().gameSettings,
                new ResourceLocation("textures/font/ascii.png"),
                Minecraft.getMinecraft().getTextureManager(),
                Minecraft.getMinecraft().isUnicode());
        ((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(this);
    }

    public RainbowTextGen init(boolean firstLineOnly) {
        this.hue = ((int)(Minecraft.getSystemTime() / 80L) / 16.0F);
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        setUnicodeFlag(fontRenderer.getUnicodeFlag());
        setBidiFlag(fontRenderer.getBidiFlag());
        this.firstLine = true;
        this.firstLineOnly = firstLineOnly;
        return this;
    }

    @ParametersAreNonnullByDefault
    public int drawStringWithShadow(String text, float x, float y, int color) {
        int length = super.drawStringWithShadow(text, x, y, color);
        this.firstLine = false;
        return length;
    }

    @ParametersAreNonnullByDefault
    public void drawSplitString(String str, int x, int y, int wrapWidth, int textColor) {
        super.drawSplitString(str, x, y, wrapWidth, textColor);
        this.firstLine = false;
    }

    protected void setColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        setHueColor();
    }

    protected float renderDefaultChar(int ch, boolean italic) {
        advanceHue();
        setHueColor();
        return super.renderDefaultChar(ch, italic);
    }

    private void advanceHue() {
        this.hue += 0.0625F;
    }

    private void setHueColor() {
        int rgb = Color.HSBtoRGB(this.hue, 1.0F, 1.0F);
        if ((!this.firstLine) && (this.firstLineOnly)) {
            super.setColor(this.r, this.g, this.b, this.a);
            return;
        }
        if ((this.r == this.g) && (this.g == this.b)) {
            super.setColor(this.r *
                    ColorHelper.getRF(rgb), this.g *
                    ColorHelper.getGF(rgb), this.b *
                    ColorHelper.getBF(rgb), this.a);
        } else {
            super.setColor(this.r * 0.5F * (1.0F +
                    ColorHelper.getRF(rgb)), this.g * 0.5F * (1.0F +
                    ColorHelper.getGF(rgb)), this.b * 0.5F * (1.0F +
                    ColorHelper.getBF(rgb)), this.a);
        }
    }

    protected float renderUnicodeChar(char ch, boolean italic) {
        advanceHue();
        setHueColor();
        return super.renderUnicodeChar(ch, italic);
    }

}