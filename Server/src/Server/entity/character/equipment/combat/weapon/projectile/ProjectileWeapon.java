package Server.entity.character.equipment.combat.weapon.projectile;

import Server.entity.character.equipment.combat.bullet.Bullet;
import Server.entity.character.equipment.combat.weapon.Weapon;

import java.util.ArrayList;

public abstract class ProjectileWeapon extends Weapon {

    //TODO Bullet has a callback from entity or surface it hit, determining it's damage, hit, remaining traveling speed etc.

    protected void shoot() {
        //TODO Check if weapon can shoot (energy = if has energy levels & heat, projectile = if has ammo)
        if (canShoot()) {
            // Decrease ammo as weapon shot
            decreaseAmmo();
            //TODO caclulate bullet trajectory (weight / gravity, velocity) use getPointingDirection()
        }
    }

    public boolean canShoot() {
        return false; //TODO temporary to avoid compile error
    }

    public void decreaseAmmo() {
        //TODO decrease projectile ammo by -1
        //TODO setClipSizeLeftover(getClipSizeLeftover() - burstSetting (aka single is minus 1, burst_3 is minus 3, burst_5 is minus 5 (google as enum can also have value))
    }


    public abstract int getClipSizeTotal();

    public abstract int getClipSizeLeftover();

    public abstract int getReloadTime();

    public abstract int getBulletInterval();

    public abstract ArrayList<ProjectileFireType> getFireTypes();

    public abstract Bullet getBullet();

}
