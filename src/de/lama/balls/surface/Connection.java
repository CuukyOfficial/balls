package de.lama.balls.surface;

import de.lama.balls.math.Vec2f;

public interface Connection {

    Vec2f getPoint1();

    Vec2f getPoint2();

    float getDensity();

}