package de.lama.balls.math;

import java.util.function.BiFunction;

public final class Vector2f implements Vec2f {

    private final float x;
    private final float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private Vec2f modify(Vec2f mod, BiFunction<Float, Float, Float> modifier) {
        return new Vector2f(modifier.apply(this.x, mod.getX()), modifier.apply(this.y, mod.getY()));
    }

    @Override
    public Vec2f scale(Vec2f scale) {
        return this.modify(scale, (x1, x2) -> x1 * x2);
    }

    @Override
    public Vec2f add(Vec2f add) {
        return this.modify(add, Float::sum);
    }

    @Override
    public float distance(Vec2f other) {
        return (float) Math.sqrt(Math.pow((this.x - other.getX()), 2) + Math.pow((this.y - other.getY()), 2));
    }

    @Override
    public Vec2f normalize() {
        float norm = (float) Math.sqrt(x * x + y * y);
        return new Vector2f(x / norm, y / norm);
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Vector2f{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }


}