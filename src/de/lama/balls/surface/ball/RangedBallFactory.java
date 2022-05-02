package de.lama.balls.surface.ball;

import de.lama.balls.math.Vec2f;
import de.lama.balls.ui.AspectRatioProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RangedBallFactory implements BallFactory {

    private final Random random;
    private final float maxX;
    private final float maxY;
    private final float speed;
    private final float radius;
    private final List<Ball> spawned;
    private final AspectRatioProvider aspectRatioProvider;

    public RangedBallFactory(float maxX, float maxY, float speed, float radius, AspectRatioProvider aspectRatioProvider) {
        this.random = new Random();
        this.maxX = maxX;
        this.maxY = maxY;
        this.speed = speed;
        this.radius = radius;
        this.spawned = new ArrayList<>();
        this.aspectRatioProvider = aspectRatioProvider;
    }

    private float minmax(float max, float t) {
        return Math.max(Math.min(max - this.radius, t), this.radius);
    }

    private Vec2f randomLocation() {
        float x = this.minmax(this.maxX, this.random.nextFloat() * this.maxX);
        float y = this.minmax(this.maxY, this.random.nextFloat() * this.maxY);
        return new Vec2f(x, y);
    }

    private Vec2f randomVelocity() {
        float x = this.random.nextFloat() * 2 - 1;
        float y = this.random.nextFloat() * 2 - 1;
        Vec2f normalizedVector = new Vec2f(x, y).normalize();
        return normalizedVector.scale(this.speed);
    }

    @Override
    public Ball create() {
        Vec2f velocity = this.randomVelocity();
        Vec2f location = this.randomLocation();
        Ball spawn = new BouncingBall(location, velocity, this.radius, this.aspectRatioProvider);
        if (this.spawned.stream().anyMatch(spawned -> spawned.intersects(spawn)))
            return this.create();
        this.spawned.add(spawn);
        return spawn;
    }
}