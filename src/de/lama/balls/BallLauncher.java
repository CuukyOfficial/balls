package de.lama.balls;

import de.lama.balls.surface.RectangularSurface;
import de.lama.balls.surface.Surface;
import de.lama.balls.ui.RenderQuality;
import de.lama.balls.ui.RenderedWindow;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class BallLauncher implements ConfigurationProvider {

    private static final Configuration DEFAULT_CONFIG =
        new Configuration(10, 0.5f, 0.005f, 0.05f,
            RenderQuality.HIGH, Color.BLACK, Color.RED);

    private final Configuration configuration;

    BallLauncher() {
        System.setProperty("sun.java2d.opengl", "True");
        this.configuration = DEFAULT_CONFIG;

        Surface surface = new RectangularSurface(this);
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