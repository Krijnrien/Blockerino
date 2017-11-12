package blockerino.entity;

import blockerino.combat.bullet.Bullet;
import blockerino.combat.bullet.projectile.StandardBullet;
import blockerino.combat.weapon.Weapon;
import blockerino.combat.weapon.projectile.IProjectileWeapon;
import blockerino.combat.weapon.projectile.pistol.RevolverPistol;
import blockerino.graphics.Sprite;
import blockerino.util.KeyHandler;
import blockerino.util.MouseHandler;
import blockerino.util.Vector2f;

import java.awt.*;

public class Player extends Entity {

	public Player(Sprite _sprite, Vector2f _origin, int _size) {
		super(_sprite, _origin, _size);
	}

	private void move() {

		if(up) {
			dy -= acc;
			if(dy < -maxSpeed) {
				dy = -maxSpeed;
			}
		} else {
			if(dy < 0) {
				dy += deacc;
				if(dy > 0) {
					dy = 0;
				}
			}
		}


		if(down) {
			dy += acc;
			if(dy > maxSpeed) {
				dy = maxSpeed;
			}
		} else {
			if(dy > 0) {
				dy -= deacc;
				if(dy < 0) {
					dy = 0;
				}
			}
		}

		if(left) {
			dx -= acc;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else {
			if(dx < 0) {
				dx += deacc;
				if(dx > 0) {
					dx = 0;
				}
			}
		}

		if(right) {
			dx += acc;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if(dx > 0) {
				dx -= deacc;
				if(dx < 0) {
					dx = 0;
				}
			}
		}

		//TODO Update Y position cause gravity
		if(airborne){

		}

	}

	public void update() {
		super.update();
		move();
		position.x += dx; // get player X position
		position.y += dy; // get player Y position
	}

	@Override
	public void render(Graphics2D _graphics2D) {
		_graphics2D.drawImage(animation.getImage(), (int) position.x, (int) position.y, size, size, null);
	}

	public void input(MouseHandler _mouse, KeyHandler _key) {
		up = _key.up.down;
		down = _key.down.down;

		left = _key.left.down;
		right = _key.right.down;

		attack = _key.attack.down;

		Bullet bullet = new StandardBullet();
		Weapon revolver = new RevolverPistol(8,bullet);
		revolver.input();
	}
}
