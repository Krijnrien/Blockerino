package blockerino.world.generation;

/**
 * NoiseGenerator is a class which uses the OpenSimplexNoise library to return noise values.
 * Amplitude and Frequency are variables to manipulate the return value.
 * Increasing the amplitude will increase the peak level
 * Increasing the frequency narrows the "wavelength" of the generator
 */
public class NoiseGenerator implements Generator {

    private OpenSimplexNoise noise = null;
    private double amplitude = 0;
    private double frequency = 1; // as value gets divided by this number it can't be 0
    private double averageHeight = 0;


    public NoiseGenerator(long _seed){
        noise = new OpenSimplexNoise(_seed);
    }

    public void setAmplitude(double _value){
        amplitude = _value;
    }

    public double getAmplitude(){
        return amplitude;
    }

    public void setFrequency(double _value){
        frequency = _value;
    }

    public double getFrequency(){
        return frequency;
    }

    public void setAverageHeight(double _value){
        averageHeight = _value;
    }

    public double getAverageHeight(){
        return averageHeight;
    }

    /**
     * Get the value from the noise combined with the amplitude, frequency and average height
     * @param _x x position
     * @param _y y position
     * @param _z z position
     * @return value
     */
    @Override
    public double getValue(double _x, double _y, double _z){
        return (noise.eval(_x / frequency, _y / frequency, _z / frequency) + 0.5) * amplitude + averageHeight;
    }

    /**
     * Check if the given position falls above or under the generator value
     * @param _x x position
     * @param _y y position
     * @param _z z position
     * @return true or false
     */
    @Override
    public boolean getBooleanValue(double _x, double _y, double _z){
        double val = getValue(_x, 0, _z); // Change y between 0 to _y to toggle a 2d generation effect

        if (val > _y){
            return false;
        }
        else{
            return true;
        }
    }
}
