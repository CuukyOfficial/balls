package de.lama.balls;

import de.lama.balls.surface.RectangularSurface;
import de.lama.balls.surface.Surface;
import de.lama.balls.ui.RenderedWindow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {

    public static void main(String[] args) {
        Surface surface = new RectangularSurface();
        System.setProperty("sun.java2d.opengl", "True");
        RenderedWindow window = new RenderedWindow("Deez Nuts", surface);

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        surface.start(pool);
        pool.execute(() -> window.setVisible(true));
    }
}