package de.lama.balls.surface;

import de.lama.balls.surface.ball.Ball;

import java.util.List;
import java.util.concurrent.ExecutorService;

public interface Surface {

    void start(ExecutorService pool);

    List<Ball> getBalls();

    List<Connection> getConnections();

}