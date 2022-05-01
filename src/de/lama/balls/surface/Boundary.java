package de.lama.balls.surface;

import de.lama.balls.math.Vec2f;
import de.lama.balls.math.Vector2f;
import de.lama.balls.surface.ball.Ball;

import java.util.function.Predicate;

public enum Boundary {

    LEFT((b) -> b.getLocation().getX() - b.getRadius() < 0, new Vector2f(-1, 1)),
    RIGHT((b) -> b.getLocation().getX() + b.getRadius() > 1, new Vector2f(-1, 1)),
    UPPER((b) -> b.getLocation().getY() + b.getRadius() > 1, new Vector2f(1, -1)),
    LOWER((b) -> b.getLocation().getY() - b.getRadius() < 0, new Vector2f(1, -1));

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