package de.lama.balls.ui;

import de.lama.balls.surface.Surface;

import javax.swing.*;

public class RenderedWindow extends JFrame {

    public RenderedWindow(String title, Surface surface) {
        System.setProperty("sun.java2d.opengl", "True");

        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 800);

        JLabel renderer = new RenderedLabel(surface);
        renderer.setFocusable(true);
        this.getContentPane().add(renderer);
    }
}