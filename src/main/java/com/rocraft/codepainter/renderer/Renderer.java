package com.rocraft.codepainter.renderer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public interface Renderer {
    String paint(String in);

    default void paint(File file) throws IOException {
        paint(file, file);
    }

    default void paint(File file, File to) throws IOException {
        String string = Files.readString(file.toPath());
        String painted = paint(string);

        Files.writeString(to.toPath(), painted);
    }
}
