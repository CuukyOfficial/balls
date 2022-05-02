package de.lama.balls.surface;

import de.lama.balls.math.Vec2f;
import de.lama.balls.surface.ball.Ball;
import de.lama.balls.ui.AspectRatioProvider;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public enum Boundary {

    LEFT((b) -> b.getLocation().x() - b.getWidth() < 0, new Vec2f(-1, 1)),
    RIGHT((b) -> b.getLocation().x() + b.getWidth() > 1, new Vec2f(-1, 1)),
    UPPER((b) -> b.getLocation().y() + b.getHeight() > 1, new Vec2f(1, -1)),
    LOWER((b) -> b.getLocation().y() - b.getHeight() < 0, new Vec2f(1, -1));

    private final Predicate<Ball> exceeds;
    private final Vec2f scaleOnExceed;

    Boundary(Predicate<Ball> exceeds, Vec2f scaleOnExceed) {
        this.exceeds = exceeds;
        this.scaleOnExceed = scaleOnExceed;
    }

    public boolean exceeds(Ball ball) {
        return this.exceeds.test(ball);
    }

    public Vec2f getScaleOnExceed() {
        return this.scaleOnExceed;
    }
}