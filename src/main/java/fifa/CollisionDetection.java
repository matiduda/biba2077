package fifa;

import javafx.animation.AnimationTimer;

// TODO:
// Reorganize the class to detect collisions
// between all players and the ball easily
// (could be done using an array of objects)

public class CollisionDetection {
    private final static int collisionBoundary = 20;
    static int i = 0;

    CollisionDetection(Ball ego, Ball wall) {        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if(checkCollision(ego, wall)) {
                    System.out.printf("Collision %d\n", i++); // TODO: Delete this
                    resolveCollision(ego, wall);
                }
            }
        };


        timer.start();
    }

    public static boolean checkCollision(Ball b1, Ball b2) {        
        double distance = getDistance(b1.pos, b2.pos);

        if (distance <= (b1.size + b2.size + collisionBoundary))
            return true;

        return false;
    }

    private static double getDistance(IntVec pos1, IntVec pos2) {
        double xDistance = pos2.x - pos1.x;
        double yDistance = pos2.y - pos1.y;

        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    // Code resource from
    // https://gist.github.com/christopher4lis/f9ccb589ee8ecf751481f05a8e59b1dc

    private Vector rotate(Vector vel, double angle) {

        Vector rotatedVelocities = new Vector(0, 0);

        rotatedVelocities.x = vel.x * Math.cos(angle) - vel.y * Math.sin(angle);
        rotatedVelocities.y = vel.x * Math.sin(angle) + vel.y * Math.cos(angle);

        return rotatedVelocities;
    }

    private void resolveCollision(Ball particle, Ball otherParticle) {
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
            Vector u1 = rotate(particle.getVelocity(), angle);
            Vector u2 = rotate(otherParticle.getVelocity(), angle);

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
}
