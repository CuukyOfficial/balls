package de.lama.balls.surface.ball;

import de.lama.balls.math.Oval2f;
import de.lama.balls.math.Vec2f;

public interface Ball {

    void bounce(Vec2f bounce);

    void move();

    Vec2f getLocation();

    boolean intersects(Ball ball);

    float getWidth();

    float getHeight();

    Oval2f toOval();

}