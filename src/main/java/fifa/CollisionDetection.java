package fifa;

import java.util.Collection;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class CollisionDetection {

    private final static int collisionBoundary = 5;

    protected static final double KICK_VOLUME = 0.0;

    HashMap<Integer, Ball> dynamicObj = new HashMap<Integer, Ball>();
    HashMap<Integer, Rectangle> staticObj = new HashMap<Integer, Rectangle>();

    private Rectangle bottomFieldBorder;

    private int dynamicSize = 0;
    private int staticSize = 0;

    private final double shootingStrength = 5.0;

    private final double bounceEffectBall = 1.05;
    private final double ballShootableOffset = 10.00;

    private final double goalGateAngles[] = { 0, -2 * Math.PI / 3, 2 * Math.PI / 3 };
    private final double borderAngles[] = { Math.PI / 3, Math.PI, 5 * Math.PI / 3 };

    AnimationTimer collisionTimer;

    public CollisionDetection(Rectangle border) {
        bottomFieldBorder = border;

        setCollisionChecks();
        collisionTimer.start();
    }

    public void addDynamic(Ball ball) {
        dynamicObj.put(dynamicSize++, ball);
    }

    public void addStatic(Collection<Node> walls) {
        for (Node w : walls) {
            staticObj.put(staticSize++, (Rectangle) w);
        }
    }

    static double getDistance(Vector pos1, Vector pos2) {
        double xDistance = pos2.x - pos1.x;
        double yDistance = pos2.y - pos1.y;

        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    // --------------- Ball - Rectangle collisions ---------------

    private boolean checkStaticCollision(Ball b, Rectangle wall, double angle) {
        double radius = b.ball.getRadius();

        Vector position = rotateCoordinates(b.pos, angle, new Vector(App.WIDTH / 2, App.HEIGHT / 2));

        double x = position.x;
        double y = position.y;

        double minX = wall.getX() - radius;
        double maxX = minX + wall.getWidth() + radius;

        double minY = wall.getY() - radius;
        double maxY = minY + wall.getHeight() + radius;

        if (y >= minY && y <= maxY && x >= minX && x <= maxX)
            return true;

        return false;
    }

    private void resolveStaticCollision(Ball b, double angle) {
        Vector newVelocity = rotate(b.vel, (Math.PI / 4) - 2 * angle);

        newVelocity.x *= bounceEffectBall;
        newVelocity.y *= bounceEffectBall;

        b.vel = newVelocity;
    }

    private void resolveSimpleStaticCollision(Ball b) {
        b.vel.x *= -bounceEffectBall;
        b.vel.y *= -bounceEffectBall;
    }

    Vector rotateCoordinates(Vector vec, double angle, Vector anchor) {
        double sinus = Math.sin(angle);
        double cosinus = Math.cos(angle);

        Vector temp = new Vector(0, 0);

        temp.x = (vec.x - anchor.x) * cosinus - (vec.y - anchor.y) * sinus + anchor.x;
        temp.y = (vec.y - anchor.y) * cosinus + (vec.x - anchor.x) * sinus + anchor.y;

        return temp;
    }

    // --------------- Ball - Ball collisions ---------------

    public static boolean checkBallCollision(Ball b1, Ball b2) {
        double distance = getDistance(b1.pos, b2.pos);

        if (distance <= (b1.size + b2.size + collisionBoundary))
            return true;

        return false;
    }

    // Code resource from
    // https://gist.github.com/christopher4lis/f9ccb589ee8ecf751481f05a8e59b1dc

    static Vector rotate(Vector vel, double angle) {

        Vector rotatedVelocities = new Vector(0, 0);

        rotatedVelocities.x = vel.x * Math.cos(angle) - vel.y * Math.sin(angle);
        rotatedVelocities.y = vel.x * Math.sin(angle) + vel.y * Math.cos(angle);

        return rotatedVelocities;
    }

    private void resolveBallCollision(Ball particle, Ball otherParticle) {
        double xVelocityDiff = particle.vel.x - otherParticle.vel.x;
        double yVelocityDiff = particle.vel.y - otherParticle.vel.y;
        double xDist = otherParticle.pos.x - particle.pos.x;
        double yDist = otherParticle.pos.y - particle.pos.y;

        // Prevent accidental overlap of particles
        if (xVelocityDiff * xDist + yVelocityDiff * yDist >= 0) {

            // Grab angle between the two colliding particles
            double angle = -Math.atan2(otherParticle.pos.y - particle.pos.y, otherParticle.pos.x - particle.pos.x);

            // Store mass in var for better readability in collision equation
            double m1 = particle.mass;
            double m2 = otherParticle.mass;

            // Velocity before equation
            Vector u1 = rotate(particle.vel, angle);
            Vector u2 = rotate(otherParticle.vel, angle);

            // Velocity after 1d collision equation
            Vector v1 = new Vector(0, 0);
            Vector v2 = new Vector(0, 0);

            v1.x = u1.x * (m1 - m2) / (m1 + m2) + u2.x * 2 * m2 / (m1 + m2);
            v1.y = u1.y;

            v2.x = u2.x * (m2 - m1) / (m1 + m2) + u1.x * 2 * m1 / (m1 + m2);
            v2.y = u2.y;

            // Final velocity after rotating axis back to original location
            Vector vFinal1 = rotate(v1, -angle);
            Vector vFinal2 = rotate(v2, -angle);

            // Swap particle velocities for realistic bounce effect
            particle.vel.x = vFinal1.x;
            particle.vel.y = vFinal1.y;

            otherParticle.vel.x = vFinal2.x;
            otherParticle.vel.y = vFinal2.y;
        }
    }

    // ---------- Timers and events ----------

    private void setCollisionChecks() {
        collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Resolve collisions between dynamic objects
                boolean playSound = false;

                for (int i = 0; i < dynamicSize; i++) {
                    for (int j = i + 1; j < dynamicSize; j++) {
                        Ball b1 = dynamicObj.get(i);
                        Ball b2 = dynamicObj.get(j);

                        if (checkBallCollision(b1, b2) == true) {
                            resolveBallCollision(b1, b2);
                            playSound = true;
                        }

                        // Check if player can shoot the ball
                        if (b1.IS_BALL && !b2.IS_BALL) {

                            double d = getDistance(b1.pos, b2.pos);

                            if (d < (b1.size + b2.size + 2 * ballShootableOffset)) {
                                b2.makeShootable(true);

                                if (b2.isShooting()) {
                                    b1.shoot(b2.pos, shootingStrength);
                                    b2.makeShootable(false);
                                }
                            } else {
                                b2.makeShootable(false);
                            }
                        }
                    }
                }

                // Resolve collisions between ball and static objects

                for (int i = 0; i < dynamicSize; i++) {

                    Ball b = dynamicObj.get(i);

                    if (b.IS_BALL) {

                        for (int s = 0; s < 3; s++) {
                            if (checkStaticCollision(b, bottomFieldBorder, goalGateAngles[s])) {
                                if (s == 0)
                                    resolveSimpleStaticCollision(b);
                                else
                                    resolveStaticCollision(b, goalGateAngles[s]);

                            }
                            if (checkStaticCollision(b, bottomFieldBorder, borderAngles[s])) {
                                if (s == 1)
                                    resolveSimpleStaticCollision(b);
                                else
                                    resolveStaticCollision(b, borderAngles[s]);
                            }
                        }

                        // Check for ball object and for all 3 angles
                        for (int j = 0; j < staticSize; j++) {

                            Rectangle wall = staticObj.get(j);

                            for (int s = 0; s < 3; s++) {

                                if (checkStaticCollision(b, wall, goalGateAngles[s])) {

                                    // Check for a score
                                    if (wall.getWidth() == Hitboxes.getGoalHitbox().getWidth()) {
                                        Logic.goalDetected(s);
                                    }

                                    resolveSimpleStaticCollision(b);
                                }
                            }
                        }
                    }
                }

                // Play the kick sound

                if (playSound) {
                    Sound.kick(KICK_VOLUME);
                }

                // Play music
                Sound.music();
            }
        };
    }
}
