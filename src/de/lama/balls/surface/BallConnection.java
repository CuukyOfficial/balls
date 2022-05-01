package de.lama.balls.surface;

import de.lama.balls.math.Vec2f;
import de.lama.balls.surface.ball.Ball;

public final class BallConnection implements Connection {

    private final Ball ball1;
    private final Ball ball2;
    private final float density;

    public BallConnection(Ball ball1, Ball ball2, float density) {
        this.ball1 = ball1;
        this.ball2 = ball2;
        this.density = density;
    }

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