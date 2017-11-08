package blockerino.world.generation;

public interface Generator {

    float threshold = 0.5f;

    float getValue(float x, float y);
    boolean getBooleanValue(float x, float y);
}
