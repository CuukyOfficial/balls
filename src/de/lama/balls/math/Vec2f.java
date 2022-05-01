package de.lama.balls.math;

public interface Vec2f {

    Vec2f scale(Vec2f scale);

    Vec2f add(Vec2f add);

    Vec2f normalize();

    float distance(Vec2f other);

    float getX();

    float getY();

}