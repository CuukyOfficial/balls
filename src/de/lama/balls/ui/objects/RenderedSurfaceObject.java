package de.lama.balls.ui.objects;

import de.lama.balls.Configuration;
import de.lama.balls.surface.Surface;

import java.awt.*;

abstract class RenderedSurfaceObject implements RenderObject {

    protected final Surface surface;
    protected final Configuration configuration;

    protected RenderedSurfaceObject(Surface surface, Configuration configuration) {
        this.surface = surface;
        this.configuration = configuration;
    }

    protected int transformX(Graphics2D g2d, float x) {
        return (int) (g2d.getClipBounds().getWidth() * x);
    }

    protected int transformY(Graphics2D g2d, float y) {
        return (int) (g2d.getClipBounds().getHeight() * y);
    }

    public Surface getSurface() {
        return this.surface;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }
}