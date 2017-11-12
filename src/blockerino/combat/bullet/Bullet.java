package blockerino.combat.bullet;

import blockerino.resources.Texture;

public abstract class Bullet {

    public void render() {
        //TODO Render bullet (Take into account max bullet distance, type of bullet)
    }

    public abstract int weight();

    public abstract int speed();

    public abstract int resistance(); // Amount of resistance to traveling bullet resulting in deceleration.

    public abstract Texture trail();

    public abstract Texture collissionEffect();

    public Texture texture() {
        return null; //TODO temporary null to avoid compile error
    }

    //TODO Surface or entity has CALLBACK to bullet to determine damage or effect. Bullet keeps a list of damages / effects related to specific blocks and entities, not other way around.
}
