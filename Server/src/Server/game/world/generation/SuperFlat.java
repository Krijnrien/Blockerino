package Server.game.world.generation;

public class SuperFlat implements Generator {

    private int groundLevel;

    public SuperFlat(int _groundLevel){
        groundLevel = _groundLevel;
    }

    @Override
    public double getValue(double _x, double _y, double _z){
        if (_y > groundLevel) { // Either the block is above or below the ground level
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public boolean getBooleanValue(double _x, double _y, double _z){
        return _y > groundLevel;
    }
}
