package blockerino.entity.character.equipment.combat.bullet;

public abstract class Bullet {

    public void render() {
        //TODO Render bullet (Take into account max bullet distance, type of bullet)
    }

    public void move(){
        //TODO Update position of bullet. Projecticle position in world, energy its ending position and following trail.
    }

    //TODO Surface or Server.game.entity has CALLBACK to bullet to determine damage or effect. Bullet keeps a list of damages / effects related to specific blocks and entities, not other way around.
}
