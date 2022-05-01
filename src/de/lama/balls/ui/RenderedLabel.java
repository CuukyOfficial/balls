package de.lama.balls.ui;

import de.lama.balls.math.Vec2f;
import de.lama.balls.surface.Connection;
import de.lama.balls.surface.Surface;
import de.lama.balls.surface.ball.Ball;

import javax.swing.*;
import java.awt.*;

public class RenderedLabel extends JLabel {

    private final Color BACKGROUND = Color.BLACK;
    private final Color CIRCLE_COLOR = Color.RED;

    private final Surface surface;

    public RenderedLabel(Surface surface) {
        this.surface = surface;
        this.repaint();
    }

    private int transformX(float x) {
        return (int) (this.getWidth() * x);
    }

    private int transformY(float y) {
        return (int) (this.getHeight() * y);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(BACKGROUND);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        float r = CIRCLE_COLOR.getRed() / 255f;
        float gr = CIRCLE_COLOR.getGreen() / 255f;
        float b = CIRCLE_COLOR.getBlue() / 255f;
        for (Connection connection : this.surface.getConnections()) {
            g2d.setColor(new Color(r, gr, b, connection.getDensity()));
            int x1 = this.transformX(connection.getPoint1().getX());
            int x2 = this.transformX(connection.getPoint2().getX());
            int y1 = this.transformY(connection.getPoint1().getY());
            int y2 = this.transformY(connection.getPoint2().getY());
            g2d.drawLine(x1, y1, x2, y2);
        }

        g2d.setColor(CIRCLE_COLOR);
        for (Ball ball : this.surface.getBalls()) {
            Vec2f loc = ball.getLocation();
            int x = this.transformX(loc.getX() - ball.getRadius());
            int y = this.transformY(loc.getY() - ball.getRadius());
            g2d.fillOval(x, y,
                this.transformX(ball.getRadius() * 2), this.transformY(ball.getRadius() * 2));
        }

        this.repaint();
    }
}