package fifa;

public class Utils {
    
    public Utils() { };
    
    public static Vector rotate(Vector vel, double angle) {

        Vector rotatedVelocities = new Vector(0, 0);

        rotatedVelocities.x = vel.x * Math.cos(angle) - vel.y * Math.sin(angle);
        rotatedVelocities.y = vel.x * Math.sin(angle) + vel.y * Math.cos(angle);

        return rotatedVelocities;
    }

    public static double getDistance(Vector pos1, Vector pos2) {
        double xDistance = pos2.x - pos1.x;
        double yDistance = pos2.y - pos1.y;

        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }
}
