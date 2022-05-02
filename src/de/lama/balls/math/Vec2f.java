package de.lama.balls.math;

import java.util.function.BiFunction;

public record Vec2f(float x, float y) {

    private Vec2f modify(Vec2f mod, BiFunction<Float, Float, Float> modifier) {
        return new Vec2f(modifier.apply(this.x, mod.x), modifier.apply(this.y, mod.y));
    }

    public Vec2f scale(Vec2f scale) {
        return this.modify(scale, (x1, x2) -> x1 * x2);
    }

    public Vec2f add(Vec2f add) {
        return this.modify(add, Float::sum);
    }

    public float distance(Vec2f other) {
        return (float) Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2));
    }

    public Vec2f normalize() {
        float norm = (float) Math.sqrt(this.x * this.x + this.y * this.y);
        return new Vec2f(this.x / norm, this.y / norm);
    }

    public String toString() {
        return "Vector2f{" + "x=" + this.x + ", y=" + this.y + '}';
    }
}