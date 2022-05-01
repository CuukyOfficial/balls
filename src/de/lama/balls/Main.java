package de.lama.balls;

import de.lama.balls.surface.RectangularSurface;
import de.lama.balls.surface.Surface;
import de.lama.balls.ui.RenderedWindow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Surface surface = new RectangularSurface();
        RenderedWindow window = new RenderedWindow("Deez Nuts", surface);

        ExecutorService pool = Executors.newCachedThreadPool();
        surface.start(pool);
        pool.execute(() -> window.setVisible(true));
    }
}