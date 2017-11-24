package blockerino.world.generation;

public interface Generator {
    double getValue(double _x, double _y, double _z);
    boolean getBooleanValue(double _x, double _y, double _z);
}
