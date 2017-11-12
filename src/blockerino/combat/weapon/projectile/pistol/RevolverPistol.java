package blockerino.combat.weapon.projectile.pistol;

import blockerino.combat.bullet.Bullet;
import blockerino.combat.weapon.Weapon;
import blockerino.combat.weapon.projectile.ProjectileFireType;
import blockerino.combat.weapon.projectile.IProjectileWeapon;

import java.util.ArrayList;

public class RevolverPistol extends IProjectileWeapon {

    private int clipSizeTotal = 8;
    private int clipSizeLeftover;
    private int reloadTime; // In milliseconds?
    private int bulletInterval; // In milliseconds?
    private ArrayList<ProjectileFireType> fireTypes = new ArrayList<>();
    private Bullet bullet;

    public RevolverPistol(int _clipSizeLeftover, Bullet _bullet) {


        /*  private void shootWeapon(){

    }*/

        java.util.ArrayList<ProjectileFireType> setFireTypes = new ArrayList<>() {{
            add(ProjectileFireType.SINGLE);
        }};

        setClipSizeTotal(8);
        setClipSizeLeftover(_clipSizeLeftover);
        setReloadTime(2000); // 2000 milliseconds
        setBulletInterval(1000); // 1000 milliseconds

        setFireTypes(setFireTypes);
        setBullet(_bullet);
    }

    public int getClipSizeTotal() {
        return clipSizeTotal;
    }

    private void setClipSizeTotal(int clipSizeTotal) {
        this.clipSizeTotal = clipSizeTotal;
    }

    public int getClipSizeLeftover() {
        return clipSizeLeftover;
    }

    private void setClipSizeLeftover(int clipSizeLeftover) {
        this.clipSizeLeftover = clipSizeLeftover;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    private void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    public int getBulletInterval() {
        return bulletInterval;
    }

    private void setBulletInterval(int bulletInterval) {
        this.bulletInterval = bulletInterval;
    }

    public ArrayList<ProjectileFireType> getFireTypes() {
        return fireTypes;
    }

    private void setFireTypes(ArrayList<ProjectileFireType> fireTypes) {
        this.fireTypes = fireTypes;
    }

    public Bullet getBullet() {
        return bullet;
    }

    private void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
}
