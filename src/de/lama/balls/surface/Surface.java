package de.lama.balls.surface;

import de.lama.balls.surface.ball.Ball;
import de.lama.balls.ui.AspectRatioProvider;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

public interface Surface {

    void start(AspectRatioProvider provider, ScheduledExecutorService pool);

    void addLocationUpdateListener(Runnable runnable);

    List<Ball> getBalls();

    Map<Ball, List<BallConnection>> getConnections();

}