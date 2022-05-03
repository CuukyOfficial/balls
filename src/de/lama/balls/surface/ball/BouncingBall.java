package de.lama.balls.surface.ball;

import de.lama.balls.Configuration;
import de.lama.balls.math.Oval2f;
import de.lama.balls.math.Vec2f;
import de.lama.balls.ui.AspectRatioProvider;

public class BouncingBall implements Ball {

    private final Configuration configuration;
    private final AspectRatioProvider aspectRatioProvider;
    private Vec2f location;
    private Vec2f velocity;

    public BouncingBall(Vec2f location, Vec2f velocity, Configuration configuration, AspectRatioProvider aspectRatioProvider) {
        this.location = location;
        this.velocity = velocity;
        this.configuration = configuration;
        this.aspectRatioProvider = aspectRatioProvider;
    }

    @Override
    public Vec2f getLocation() {
        return this.location;
    }

    @Override
    public float getWidth() {
        return this.configuration.getBallRadius() / this.aspectRatioProvider.getAspectRatio();
    }

    @Override
    public float getHeight() {
        return this.configuration.getBallRadius();
    }

    @Override
    public void bounce(Vec2f bounce) {
        this.velocity = this.velocity.scale(bounce);
    }

    @Override
    public void setVelocity(Vec2f velocity) {
        this.velocity = velocity;
    }

    @Override
    public Vec2f getVelocity() {
        return this.velocity;
    }

    @Override
    public boolean intersects(Ball ball) {
        return this.asOval().intersects(ball.asOval());
    }

    @Override
    public void move() {
        this.location = this.location.add(this.velocity);
    }

    @Override
    public Oval2f asOval() {
        return new Oval2f(this.location, this.velocity, this.getWidth(), this.getHeight());
    }
}