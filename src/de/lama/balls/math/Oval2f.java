package de.lama.balls.math;

import java.awt.geom.Ellipse2D;

public record Oval2f(Vec2f location, Vec2f velocity, float width, float height) {

    private Vec2f getMiddle(Vec2f other) {
        float xMiddle = this.location.x() + 0.5f * (other.x() - this.location().x());
        float yMiddle = this.location.y() + 0.5f * (other.y() - this.location().y());
        return new Vec2f(xMiddle, yMiddle);
    }

    private Oval2f next() {
        return new Oval2f(this.location.add(this.velocity), this.velocity, this.width, this.height);
    }

    public boolean intersects(Oval2f oval) {
//        float a = this.width;
//        float b = this.height;
//        float p = oval.location.x();
//        float q = oval.location.y();
//        System.out.println(Math.pow(p / a, 2) + Math.pow(q / b, 2));
//        return Math.pow(p / a, 2) + Math.pow(q / b, 2) <= 4;
        Oval2f next = this.next();
        return next.asEllipse().contains(next.getMiddle(oval.next().location).asPoint());
    }

    public Vec2f bounce(Oval2f oval, float speed) {
        Vec2f n = this.location.sub(oval.location).normalize();
        return this.velocity.sub(n.scale(n.dotProduct(this.velocity.sub(oval.velocity))));
    }

    public Ellipse2D asEllipse() {
        return new Ellipse2D.Float(this.location.x() - this.width,
            this.location.y() - this.height, this.width * 2, this.height * 2);
    }
}