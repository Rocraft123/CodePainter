package com.rocraft.codepainter.renderer.java;

import com.rocraft.codepainter.color.configs.DarkColorConfig;
import com.rocraft.codepainter.color.painters.SimpleStylePainter;
import com.rocraft.codepainter.color.StylePainter;
import com.rocraft.codepainter.renderer.Renderer;

import java.io.*;
import java.nio.file.Files;

public abstract class JavaRenderer implements Renderer {

    public static final SimpleStylePainter DEFAULT_STYLE_PAINTER = new SimpleStylePainter(
            new DarkColorConfig());

    protected final StylePainter stylePainter;

    public JavaRenderer() {
        this.stylePainter = DEFAULT_STYLE_PAINTER;
    }

    public JavaRenderer(StylePainter stylePainter) {
        this.stylePainter = stylePainter;
    }

    @Override
    public void paint(File file) throws IOException {
        String string = Files.readString(file.toPath());
        String painted = paint(string);

        Files.writeString(file.toPath(), painted);
    }

    @Override
    public void paint(File file, File to) throws IOException {
        String string = Files.readString(file.toPath());
        String painted = paint(string);

        Files.writeString(to.toPath(), painted);
    }
}
