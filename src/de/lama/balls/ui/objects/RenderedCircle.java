package de.lama.balls.ui.objects;

import de.lama.balls.Configuration;
import de.lama.balls.math.Vec2f;
import de.lama.balls.surface.BallConnection;
import de.lama.balls.surface.Surface;
import de.lama.balls.surface.ball.Ball;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class RenderedCircle extends RenderedSurfaceObject {

    public RenderedCircle(Surface surface, Configuration configuration) {
        super(surface, configuration);
    }

    private Vec2f interpolate(Ball ball, float scale) {
        return ball.getLocation().add(ball.getVelocity().scale(scale));
    }

    @Override
    public void draw(Graphics2D g2d) {
        this.interpolate(g2d, 0f);
    }

    @Override
    public void interpolate(Graphics2D g2d, float scale) {
        Color circleColor = this.configuration.getCircleColor();
        float r = circleColor.getRed() / 255f;
        float gr = circleColor.getGreen() / 255f;
        float b = circleColor.getBlue() / 255f;

        Map<Ball, List<BallConnection>> connections = this.surface.getConnections();
        g2d.setColor(this.configuration.getCircleColor());
        for (Ball ball : this.surface.getBalls()) {
            Vec2f loc = this.interpolate(ball, scale);

            for (BallConnection connection : connections.get(ball)) {
                g2d.setColor(new Color(r, gr, b, connection.density()));
                Vec2f to = this.interpolate(connection.to(), scale);
                int x1 = this.transformX(g2d, loc.x());
                int x2 = this.transformX(g2d, to.x());
                int y1 = this.transformY(g2d, loc.y());
                int y2 = this.transformY(g2d, to.y());
                g2d.drawLine(x1, y1, x2, y2);
            }

            int x = this.transformX(g2d,loc.x() - ball.getWidth());
            int y = this.transformY(g2d,loc.y() - ball.getHeight());
            int width = this.transformX(g2d,ball.getWidth() * 2);
            int height = this.transformY(g2d,ball.getHeight() * 2);
            g2d.setColor(circleColor);
            g2d.fillOval(x, y, width, height);
            g2d.drawOval(x, y, width, height);
        }
    }
}