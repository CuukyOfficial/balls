package de.lama.balls.surface;

import de.lama.balls.surface.ball.Ball;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public interface Surface {

    void start(ScheduledExecutorService pool);

    List<Ball> getBalls();

    List<Connection> getConnections();

}