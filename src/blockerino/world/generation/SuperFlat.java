package blockerino.world.generation;

public class SuperFlat implements Generator {

    private int groundLevel;

    public SuperFlat(int _groundLevel){
        groundLevel = _groundLevel;
    }

    public float getValue(float _x, float _y){
        if (_y > groundLevel) { // Either the block is above or below the ground level
            return 1;
        }
        else{
            return 0;
        }
    }

    public boolean getBooleanValue(float _x, float _y){
        if (_y > groundLevel) {
            return true;
        }
        else{
            return false;
        }
    }
}
