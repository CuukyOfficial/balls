package de.lama.balls.surface;

import de.lama.balls.Configuration;
import de.lama.balls.math.Vec2f;
import de.lama.balls.surface.ball.Ball;
import de.lama.balls.surface.ball.BallFactory;
import de.lama.balls.surface.ball.RangedBallFactory;
import de.lama.balls.ui.AspectRatioProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class RectangularSurface implements Surface {

    private final List<Ball> balls;
    private final List<Connection> connections;
    private final Configuration configuration;

    public RectangularSurface(Configuration configuration) {
        this.balls = new CopyOnWriteArrayList<>();
        this.connections = new CopyOnWriteArrayList<>();
        this.configuration = configuration;
    }

    private void move() {
        this.checkBallCollision();
        this.balls.forEach(this::checkBoundaries);
        this.balls.forEach(Ball::move);
    }

    private void connect() {
        List<Connection> connections = new ArrayList<>(this.connections);
        float minDistance = this.configuration.getConnectionDistance();
        for (Ball ball1 : this.balls) {
            for (Ball ball2 : this.balls) {
                if (ball1 == ball2) continue;

                float distance = ball1.getLocation().distance(ball2.getLocation());
                if (distance <= minDistance) {
                    this.connections.add(new BallConnection(ball1, ball2, 1f - distance / minDistance));
                }
            }
        }

        this.connections.removeAll(connections);
    }

    private void checkBoundaries(Ball ball) {
        Arrays.stream(Boundary.values()).filter(b -> b.exceeds(ball))
            .forEach(b -> ball.bounce(b.getScaleOnExceed()));
    }

    private void checkBallCollision() {
        // TODO: optimize
        Map<Ball, Vec2f> velocitiesOfCollisions = new HashMap<>();
        for (Ball ball1 : this.balls) {
            Vec2f bounce = null;
            for (Ball ball2 : this.balls) {
                if (!ball1.equals(ball2) && ball1.intersects(ball2)) {
                    Vec2f v1 = ball1.asOval().bounce(ball2.asOval());
                    bounce = bounce != null ? bounce.add(v1) : v1;
                }
            }
            if (bounce != null) velocitiesOfCollisions.put(ball1, bounce);
        }

        this.balls.stream().filter(b -> !velocitiesOfCollisions.containsKey(b))
            .forEach(b -> velocitiesOfCollisions.put(b, b.getVelocity()));
        float max = this.configuration.getBallAmount() * this.configuration.getBallSpeed();
        float allSpeeds = velocitiesOfCollisions.values().stream().map(Vec2f::norm).reduce(Float::sum).orElse(max);
        velocitiesOfCollisions.keySet().forEach(b -> b.setVelocity(velocitiesOfCollisions.get(b).scale(max / allSpeeds)));
    }

    @Override
    public void start(AspectRatioProvider aspectRatioProvider, ScheduledExecutorService pool) {
        BallFactory ballFactory = new RangedBallFactory(1, 1, this.configuration, aspectRatioProvider);
        IntStream.range(0, this.configuration.getBallAmount())
            .mapToObj(i -> ballFactory.create()).forEach(this.balls::add);
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