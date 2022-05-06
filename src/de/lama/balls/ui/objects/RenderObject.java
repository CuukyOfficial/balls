package de.lama.balls.ui.objects;

import java.awt.*;

public interface RenderObject {

    void draw(Graphics2D g2d);

    void interpolate(Graphics2D g2d, float scale);

}