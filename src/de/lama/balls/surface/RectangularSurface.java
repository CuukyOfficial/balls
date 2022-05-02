package de.lama.balls.surface;

import de.lama.balls.surface.ball.Ball;
import de.lama.balls.surface.ball.BallFactory;
import de.lama.balls.surface.ball.RangedBallFactory;
import de.lama.balls.ui.AspectRatioProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class RectangularSurface implements Surface {

    private static final int MAX_BALLS = 10;
    private static final float CONNECTION_DISTANCE = 1f;

    private final List<Ball> balls;
    private final List<Connection> connections;
    private boolean stopped;

    public RectangularSurface() {
        this.balls = new CopyOnWriteArrayList<>();
        this.connections = new CopyOnWriteArrayList<>();
    }

    private void move() {
        if (this.stopped) return;
        this.balls.forEach(this::checkNeighbours);
        if (this.stopped) return;
        this.balls.forEach(this::checkBoundaries);
        this.balls.forEach(Ball::move);
    }

    private void connect() {
        if (this.stopped) return;
        List<Connection> connections = new ArrayList<>(this.connections);
        for (Ball ball1 : this.balls) {
            for (Ball ball2 : this.balls) {
                if (ball1 == ball2) continue;

                float distance = ball1.getLocation().distance(ball2.getLocation());
                if (distance <= CONNECTION_DISTANCE) {
                    this.connections.add(new BallConnection(ball1, ball2, 1f - distance / CONNECTION_DISTANCE));
                }
            }
        }

        this.connections.removeAll(connections);
    }

    private void checkBoundaries(Ball ball) {
        Arrays.stream(Boundary.values()).filter(b -> b.exceeds(ball))
            .forEach(b -> ball.bounce(b.getScaleOnExceed()));
    }

    private void checkNeighbours(Ball ball) {
        for (Ball neighbour : this.balls) {
            if (neighbour.equals(ball)) continue;

            if (ball.intersects(neighbour)) {
                this.stopped = true;
                break;
            }
        }
    }

    @Override
    public void start(AspectRatioProvider aspectRatioProvider, ScheduledExecutorService pool) {
        BallFactory ballFactory = new RangedBallFactory(1, 1, aspectRatioProvider);
        IntStream.range(0, MAX_BALLS).mapToObj(i -> ballFactory.create()).forEach(this.balls::add);
        pool.scheduleAtFixedRate(this::move, 0, 15, TimeUnit.MILLISECONDS);
        pool.scheduleAtFixedRate(this::connect, 0, 100, TimeUnit.MILLISECONDS);
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