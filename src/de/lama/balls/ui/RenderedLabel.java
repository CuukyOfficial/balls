package de.lama.balls.ui;

import de.lama.balls.Configuration;
import de.lama.balls.surface.Surface;
import de.lama.balls.ui.objects.RenderObject;
import de.lama.balls.ui.objects.RenderedCircle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RenderedLabel extends JLabel implements AspectRatioProvider {

    private final Configuration configuration;
    private final List<RenderObject> renderObjects;
    private Surface surface;

    private boolean updateQueued;
    private long lastUpdate;
    private long approximateNextUpdate;

    RenderedLabel(Configuration configuration) {
        this.configuration = configuration;
        this.renderObjects = new ArrayList<>();
        this.lastUpdate = System.currentTimeMillis();
        this.approximateNextUpdate = 10;
    }

    private void onLocationUpdate() {
        long current = System.currentTimeMillis();
        this.approximateNextUpdate = current + (current - this.lastUpdate);
        this.lastUpdate = current;
        this.updateQueued = true;
    }

    public void start(Surface surface) {
        this.surface = surface;
        surface.addLocationUpdateListener(this::onLocationUpdate);
        this.renderObjects.add(new RenderedCircle(this.surface, this.configuration));
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.configuration.getBackground());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setRenderingHints(this.configuration.getRenderQuality().getHints());
//        if (this.updateQueued) {
            this.renderObjects.forEach(render -> render.draw(g2d));
//            this.updateQueued = false;
//        } else {
//            long timeUntilNextUpdate = this.approximateNextUpdate - this.lastUpdate;
//            long timePassed = System.currentTimeMillis() - this.lastUpdate;
//            float scale = (float) timePassed / timeUntilNextUpdate;
//            System.out.println("INTERPOLATE: " + scale);
//            this.renderObjects.forEach(render -> render.interpolate(g2d, scale));
//        }

        this.repaint();
    }

    @Override
    public float getAspectRatio() {
        if (this.getWidth() == 0 || this.getHeight() == 0) return 1f;
        return (float) this.getWidth() / (float) this.getHeight();
    }
}