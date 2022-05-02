package de.lama.balls;

import de.lama.balls.surface.RectangularSurface;
import de.lama.balls.surface.Surface;
import de.lama.balls.ui.RenderedWindow;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class BallLauncher implements ConfigurationProvider {

    private Configuration configuration;

    BallLauncher() {
        Surface surface = new RectangularSurface();
        System.setProperty("sun.java2d.opengl", "True");
        RenderedWindow window = new RenderedWindow("Deez Nuts", this);

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        surface.start(window, pool);
        pool.execute(() -> {
            window.start(surface);
            window.setVisible(true);
        });
    }

    @Override
    public Configuration get() {
        return this.configuration;
    }
}