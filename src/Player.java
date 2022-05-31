import processing.core.PApplet;
import processing.core.PImage;

import java.util.*;

public class Player extends Entity {

	// used to determine sprite
	private int s = 0;

	// movement resources
	private float speed = 15;
	private float accel = 0.75f;
	private int speedCounter = 0;

	public void setSpeedCounter(int count) {
		speedCounter = count;
	}

	private float jumpSpeed = 25;
	private float friction = 0.8f;
	private float g = 1.5f;

	private boolean canJump = true;
	private boolean dead = false;

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean getDead() {
		return dead;
	}

	// resets resources if player dies
	private boolean reset = false;

	public void setReset(boolean reset) {
		this.reset = reset;
	}

	public boolean getReset() {
		return reset;
	}


	private int spawnX = 0;
	private int spawnY = 0;

	public void setSpawnX(int x) {
		spawnX = x;
	}

	public void setSpawnY(int y) {
		spawnY = y;
	}

	private int points = 0;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	private int facing = 0;
	private PImage[] runR;
	private PImage[] runL;

	public Player(PApplet parent, PImage[] runR, PImage runL[]) {
		super(0, 0, 32, 32, parent);
		this.runR = runR;
		this.runL = runL;
	}

	public void move(HashMap<Integer, Boolean> keys, ArrayList<Block> blocks, int rightBound) {

		speed = 15;
		accel = 0.75f;
		friction = 0.8f;

		if (speedCounter > 0) {
			speedCounter--;
			speed = 30;
			accel = 1.5f;
			friction = 0.6f;
		}

		// key input
		if (keys.get(37) || keys.get(65))
			velX -= accel;

		if (keys.get(39) || keys.get(68))
			velX += accel;

		if ((keys.get(38) || keys.get(87)) && canJump) {
			velY = -jumpSpeed;
			canJump = false;
		}

		// velocity updates
		velY += g;

		if (velX < -speed)
			velX = -speed;
		if (velX > speed)
			velX = speed;

		if (velY > 25)
			velY = 25;

		if (!keys.get(37) && !keys.get(39) && !keys.get(65) && !keys.get(68)) {
			velX *= friction;
		}

		// x movement, x collision detection
		x += velX;

		// optimize later - get blocks based on location to player. double hashmap?
		for (Block e : blocks) {
			if (collidesWith(e)) {
				// bump to left
				if (getCenterX() < e.getCenterX()) {
					x = e.getX() - width;

					// bump to right
				} else {
					x = e.getX() + e.getWidth();
				}

				velX = 0;
			}
		}

		// check bounds for x position
		if (x < 0) {
			x = 0;
			velX = 0;
		} else if (x + width > rightBound) {
			x = rightBound - width;
			velX = 0;
		}

		// y movement, canJump detection, y collision
		y += velY;
		canJump = false;

		// optimize later
		for (Block e : blocks) {
			if (collidesWith(e)) {
				// bump up, set jump true
				if (getCenterY() < e.getCenterY()) {
					y = e.getY() - height;
					canJump = true;

					// bump down
				} else {
					y = e.getY() + e.getHeight();
				}

				velY = 0;
			}
		}

		if (y > p.height)
			dead = true;

		if (dead) {
			x = spawnX;
			y = spawnY;
			speedCounter = 0;
			reset = true;
			points -= 10;
			dead = false;
		}

	}

	public void show() {

		int scale = 1;

		if (speedCounter > 0) {
			scale = 2;
		}

		if (velX > 0.1) {
			p.image(runR[(s * 2 * scale / 15) % runR.length], x, y, width, height);
			facing = 0;
		} else if (velX < -0.1) {
			p.image(runL[(s * 2 * scale / 15) % runL.length], x, y, width, height);
			facing = 1;
		} else {

			if (facing == 0) {
				p.image(runR[1], x, y);
			} else {
				p.image(runL[1], x, y);
			}
		}

		s++;

	}

}