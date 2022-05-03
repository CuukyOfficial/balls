package de.lama.balls;

import de.lama.balls.ui.RenderQuality;

import java.awt.*;

public record Configuration(int ballAmount, float connectionDistance, float ballSpeed, float ballRadius,
                            RenderQuality renderQuality, Color background, Color circleColor) {

}