package blockerino.graphics;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] frames;
    private int currentFrame;
    private int numFrames;

    private int count;
    private int delay;

    private int timesPlayed;

    public Animation(BufferedImage[] _frames) {
        timesPlayed = 0;
        setFrames(_frames);
    }

    public Animation() {
        timesPlayed = 0;
    }

    public void setFrames(BufferedImage[] _frames) {
        this.frames = _frames;
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        delay = 2;
        numFrames = _frames.length;
    }

    public void setDelay(int _delay) {
        this.delay = _delay;
    }

    public void setFrame(int _frame) {
        currentFrame = _frame;
    }

    public void setNumFrames(int _numFrames) {
        numFrames = _numFrames;
    }


    public void update() {
        if (delay == -1) return;
        count++;

        if (count == delay) {
            currentFrame++;
            count = 0;
        }
        if (currentFrame == numFrames) {
            currentFrame = 0;
            timesPlayed++;
        }

    }

    public int getDelay() {
        return delay;
    }

    public int getFrame() {
        return currentFrame;
    }

    public int getCount() {
        return count;
    }

    public BufferedImage getImage() {
        return frames[currentFrame];
    }

    public boolean hasPlayedOnce() {
        return timesPlayed > 0;
    }

    public boolean hasPlayed(int _timesPlayed) {
        return timesPlayed == _timesPlayed;
    }
}
