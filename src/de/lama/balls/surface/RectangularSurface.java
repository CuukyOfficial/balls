package de.lama.balls.surface;

import de.lama.balls.surface.ball.Ball;
import de.lama.balls.surface.ball.BallFactory;
import de.lama.balls.surface.ball.RangedBallFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

public class RectangularSurface implements Surface {

    private final int MAX_BALLS = 1000;
    private final float CONNECTION_DISTANCE = 1f;

    private final List<Ball> balls;
    private final List<Connection> connections;

    public RectangularSurface() {
        this.balls = new CopyOnWriteArrayList<>();
        this.connections = new CopyOnWriteArrayList<>();
        BallFactory ballFactory = new RangedBallFactory(1,1);
        IntStream.range(0, MAX_BALLS).mapToObj(i -> ballFactory.create()).forEach(this.balls::add);
    }

    private void sleep(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void start(Runnable runnable, long timeout) {
        while (true) {
            runnable.run();
            this.sleep(timeout);
        }
    }

    private void move() {
        this.balls.forEach(this::checkNeighbours);
        this.balls.forEach(this::checkBoundaries);
        this.balls.forEach(Ball::move);
    }

    private void connect() {
        List<Connection> connections = new ArrayList<>(this.connections);
        for (Ball ball1 : this.balls) {
            for (Ball ball2 : this.balls) {
                if (ball1 == ball2) continue;

                float distance = ball1.getLocation().distance(ball2.getLocation());
                if (distance <= CONNECTION_DISTANCE) {
                    this.connections.add(new BallConnection(ball1, ball2,  1f - distance / CONNECTION_DISTANCE));
                }
            }
        }

        this.connections.removeAll(connections);
    }

    @Override
    public void start(ExecutorService pool) {
        pool.execute(() -> this.start(this::move, 15));
        pool.execute(() -> this.start(this::connect, 100));
    }

    private void checkBoundaries(Ball ball) {
        Arrays.stream(Boundary.values()).filter(b -> b.exceeds(ball))
            .forEach(b -> ball.bounce(b.getScaleOnExceed()));
    }

    private void checkNeighbours(Ball ball) {
        for (Ball neighbour: this.balls) {
//             if (neighbour == ball)
//
//            if ()
        }
    }

    @Override
    public List<Ball> getBalls() {
        return new ArrayList<>(this.balls);
    }

    @Override
    public List<Connection> getConnections() {
        return new ArrayList<>(this.connections);
    }
}