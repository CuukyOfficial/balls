package de.lama.balls.surface.ball;

import de.lama.balls.math.Oval2f;
import de.lama.balls.math.Vec2f;

public interface Ball {

    void bounce(Vec2f off);

    void move();

    boolean intersects(Ball ball);

    void setVelocity(Vec2f velocity);

    Vec2f getVelocity();

    Vec2f getLocation();

    float getWidth();

    float getHeight();

    Oval2f asOval();

}