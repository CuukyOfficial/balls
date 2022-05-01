package de.lama.balls.math;

public interface Vec2f {

    Vec2f scale(Vec2f scale);

    Vec2f add(Vec2f add);

    float distance(Vec2f other);

    Vec2f normalize();

    float getX();

    float getY();

}