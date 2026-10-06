package com.rocraft.codepainter;

import com.rocraft.codepainter.renderer.Renderer;
import com.rocraft.codepainter.renderer.java.JavaHtmlRenderer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    static void main() throws IOException {
        long start = System.currentTimeMillis();
        Renderer renderer = new JavaHtmlRenderer();

        File in = Paths.get("src/main/resources/java_in.java").toFile();
        File out = Paths.get("src/main/resources/out.html").toFile();

        renderer.paint(in, out);
        IO.println("Finished in: " + (System.currentTimeMillis() - start) + "ms");
    }
}
