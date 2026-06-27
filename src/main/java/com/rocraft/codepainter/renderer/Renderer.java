package com.rocraft.codepainter;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface Renderer {
    String paint(String in);
    void paint(File file);
    void paint(InputStream in, OutputStream out);
}
