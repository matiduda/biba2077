package fifa;

public class Vector {
    public double x;
    public double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector n) {
        x += n.x;
        y += n.y;
        return this;
    }

    public Vector extend(double a) {
        x *= a;
        y *= a;
        return this;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }
}
