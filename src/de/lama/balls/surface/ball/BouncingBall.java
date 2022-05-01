package de.lama.balls.surface.ball;

import de.lama.balls.math.Vec2f;

public class BouncingBall implements Ball {

    private Vec2f location;
    private Vec2f velocity;
    private final float radius;

    public BouncingBall(Vec2f location, Vec2f velocity, float radius) {
        this.location = location;
        this.velocity = velocity;
        this.radius = radius;
    }

    @Override
    public Vec2f getLocation() {
        return this.location;
    }

    @Override
    public float getRadius() {
        return this.radius;
    }

    @Override
    public void bounce(Vec2f bounce) {
        this.velocity = this.velocity.scale(bounce);
    }

    @Override
    public boolean intersects(Ball ball) {
        return this.location.distance(ball.getLocation()) <= this.radius + ball.getRadius();
    }

    @Override
    public void move() {
        this.location = this.location.add(this.velocity);
    }
}