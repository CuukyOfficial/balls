package de.lama.balls.math;

import java.util.function.BiFunction;

public record Vector2f(float x, float y) implements Vec2f {

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
        float norm = (float) Math.sqrt(this.x * this.x + this.y * this.y);
        return new Vector2f(this.x / norm, this.y / norm);
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
        return "Vector2f{" + "x=" + this.x + ", y=" + this.y + '}';
    }
}