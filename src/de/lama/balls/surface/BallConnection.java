package de.lama.balls.surface;

import de.lama.balls.math.Vec2f;
import de.lama.balls.surface.ball.Ball;

public record BallConnection(Ball ball1, Ball ball2, float density) implements Connection {

    @Override
    public Vec2f getPoint1() {
        return this.ball1.getLocation();
    }

    @Override
    public Vec2f getPoint2() {
        return this.ball2.getLocation();
    }

    @Override
    public float getDensity() {
        return this.density;
    }
}