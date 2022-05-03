package de.lama.balls;

import de.lama.balls.ui.RenderQuality;

import java.awt.*;

public class Configuration {

    private int ballAmount;
    private float connectionDistance;
    private float ballSpeed;
    private float ballRadius;
    private RenderQuality renderQuality;
    private Color background;
    private Color circleColor;

    public Configuration() {
        this.ballAmount = 10;
        this.connectionDistance = 0.5f;
        this.ballSpeed = 0.005f;
        this.ballRadius = 0.05f;
        this.renderQuality = RenderQuality.HIGH;
        this.background = Color.BLACK;
        this.circleColor = Color.RED;
    }

    public int getBallAmount() {
        return ballAmount;
    }

    public void setBallAmount(int ballAmount) {
        this.ballAmount = ballAmount;
    }

    public float getConnectionDistance() {
        return connectionDistance;
    }

    public void setConnectionDistance(float connectionDistance) {
        this.connectionDistance = connectionDistance;
    }

    public float getBallSpeed() {
        return ballSpeed;
    }

    public void setBallSpeed(float ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    public float getBallRadius() {
        return ballRadius;
    }

    public void setBallRadius(float ballRadius) {
        this.ballRadius = ballRadius;
    }

    public RenderQuality getRenderQuality() {
        return renderQuality;
    }

    public void setRenderQuality(RenderQuality renderQuality) {
        this.renderQuality = renderQuality;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(Color circleColor) {
        this.circleColor = circleColor;
    }
}