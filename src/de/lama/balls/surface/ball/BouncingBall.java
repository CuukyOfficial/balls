package de.lama.balls.surface.ball;

import de.lama.balls.math.Oval2f;
import de.lama.balls.math.Vec2f;
import de.lama.balls.ui.AspectRatioProvider;

public class BouncingBall implements Ball {

    private final float radius;
    private final AspectRatioProvider aspectRatioProvider;
    private Vec2f location;
    private Vec2f velocity;

    public BouncingBall(Vec2f location, Vec2f velocity, float radius, AspectRatioProvider aspectRatioProvider) {
        this.location = location;
        this.velocity = velocity;
        this.radius = radius;
        this.aspectRatioProvider = aspectRatioProvider;
    }

    @Override
    public Vec2f getLocation() {
        return this.location;
    }

    @Override
    public float getWidth() {
        return this.radius / this.aspectRatioProvider.getAspectRatio();
    }

    @Override
    public float getHeight() {
        return this.radius;
    }

    @Override
    public void bounce(Vec2f bounce) {
        this.velocity = this.velocity.scale(bounce);
    }

    @Override
    public boolean intersects(Ball ball) {
        return this.toOval().intersects(ball.toOval());
    }

    @Override
    public void move() {
        this.location = this.location.add(this.velocity);
    }

    @Override
    public Oval2f toOval() {
        return new Oval2f(this.location, this.getWidth(), this.getHeight());
    }
}