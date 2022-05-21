package fifa;

public class IntVec {
    public int x;
    public int y;

    public IntVec(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }
}
