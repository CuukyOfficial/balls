package de.lama.balls.math;

import java.awt.geom.Ellipse2D;

public record Oval2f(Vec2f location, float width, float height) {

    private Vec2f getMiddle(Vec2f other) {
        float xMiddle = this.location.x() + 0.5f * (other.x() - this.location().x());
        float yMiddle = this.location.y() + 0.5f * (other.y() - this.location().y());
        return new Vec2f(xMiddle, yMiddle);
    }

    private boolean intersects(float middle, float cord, float dimension) {
        return Math.abs(middle - cord) <= dimension;
    }

    public boolean intersects(Oval2f oval) {
        Vec2f middle = this.getMiddle(oval.location);

        //= this.intersects(middle.x(), this.location.x(), this.width) &&
            //this.intersects(middle.y(), this.location.y(), this.height);
//        if (intersects) {
//            //System.out.println("x1: " + this.location.x());
//            System.out.println("first vector: " + this.location);
//            System.out.println("second vector: " + oval.location);
//            System.out.println("intersects on width: " + this.intersects(middle.x(), this.location.x(), this.width));
//            System.out.println("width: " + this.width);
//            System.out.println("distance from center to middle: " + (middle.x() - this.location.x()));
//            System.out.println(middle);
//        }
        return this.toEllipse().contains(middle.x(), middle.y());
    }

    public Ellipse2D toEllipse() {
        return new Ellipse2D.Float(this.location.x(), this.location.y(), this.width, this.height);
    }
}