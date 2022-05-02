package de.lama.balls.ui;

import de.lama.balls.ConfigurationProvider;
import de.lama.balls.surface.Surface;

import javax.swing.*;

public class RenderedWindow extends JFrame implements AspectRatioProvider {

    private final RenderedLabel renderer;

    public RenderedWindow(String title, ConfigurationProvider configurationProvider) {
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 800);

        this.renderer = new RenderedLabel(configurationProvider);
        renderer.setFocusable(true);
        this.getContentPane().add(renderer);
    }

    @Override
    public float getAspectRatio() {
        return this.renderer.getAspectRatio();
    }

    public void start(Surface surface) {
        this.renderer.start(surface);
    }
}