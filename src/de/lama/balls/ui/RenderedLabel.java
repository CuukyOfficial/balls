package de.lama.balls.ui;

import de.lama.balls.ConfigurationProvider;
import de.lama.balls.math.Vec2f;
import de.lama.balls.surface.Connection;
import de.lama.balls.surface.Surface;
import de.lama.balls.surface.ball.Ball;

import javax.swing.*;
import java.awt.*;

public class RenderedLabel extends JLabel implements AspectRatioProvider {

    private static final RenderQuality RENDER_QUALITY = RenderQuality.HIGH;
    private static final Color BACKGROUND = Color.BLACK;
    private static final Color CIRCLE_COLOR = Color.RED;

    private final ConfigurationProvider configurationProvider;
    private Surface surface;

    RenderedLabel(ConfigurationProvider configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

    private int transformX(float x) {
        return (int) (this.getWidth() * x);
    }

    private int transformY(float y) {
        return (int) (this.getHeight() * y);
    }

    public void start(Surface surface) {
        this.surface = surface;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(RENDER_QUALITY.getHints());
        g2d.setColor(this.configurationProvider.get().background());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        Color circleColor = this.configurationProvider.get().circleColor();
        float r = circleColor.getRed() / 255f;
        float gr = circleColor.getGreen() / 255f;
        float b = circleColor.getBlue() / 255f;
        for (Connection connection : this.surface.getConnections()) {
            g2d.setColor(new Color(r, gr, b, connection.getDensity()));
            int x1 = this.transformX(connection.getPoint1().x());
            int x2 = this.transformX(connection.getPoint2().x());
            int y1 = this.transformY(connection.getPoint1().y());
            int y2 = this.transformY(connection.getPoint2().y());
            g2d.drawLine(x1, y1, x2, y2);
        }

        g2d.setColor(this.configurationProvider.get().circleColor());
        for (Ball ball : this.surface.getBalls()) {
            Vec2f loc = ball.getLocation();
            int x = this.transformX(loc.x() - ball.getWidth());
            int y = this.transformY(loc.y() - ball.getHeight());
            int width = this.transformX(ball.getWidth() * 2);
            int height = this.transformY(ball.getHeight() * 2);
            g2d.fillOval(x, y, width, height);
            g2d.drawOval(x, y, width, height);
        }

        this.repaint();
    }

    @Override
    public float getAspectRatio() {
        if (this.getWidth() == 0 || this.getHeight() == 0) return 1f;
        return (float) this.getWidth() / (float) this.getHeight();
    }
}