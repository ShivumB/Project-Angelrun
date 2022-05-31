import java.util.*;
import processing.core.PApplet;
import processing.core.PImage;

public class Slider extends Entity {

	private float g = 1.5f;
	private float speed = 20;

	private boolean dead = false;

	public boolean isDead() {
		return dead;
	};

	private PImage[] spritesR;
	private PImage[] spritesL;
	
	private int frame = (int)(10 * Math.random());

	public Slider(float x, float y, int width, int height, PImage[] spritesR, PImage[] spritesL, PApplet parent) {
		super(x, y, width, height, parent);
		this.spritesR = spritesR;
		this.spritesL = spritesL;
		
		velX = -5;
		velY = 0;
	}

	public void move(ArrayList<Block> blocks, int rightBound) {

		velY += g;

		if (velX < -speed)
			velX = -speed;
		if (velX > speed)
			velX = speed;

		if (velY > 25)
			velY = 25;

		if((frame / 6) % spritesR.length > 7) {
			x += velX;
		}
		
		x += velX / 5;

		for (Block e : blocks) {
			if (collidesWith(e)) {
				if (getCenterX() < e.getCenterX()) {
					x = e.getX() - width;

					// turn around, make sure facing left
					velX = -1 * Math.abs(velX);
				} else {
					x = e.getX() + e.getWidth();

					// turn around, make sure facing right
					velX = Math.abs(velX);
				}
			}
		}

		y += velY;

		for (Block e : blocks) {
			if (collidesWith(e)) {
				if (getCenterY() < e.getCenterY()) {
					y = e.getY() - height;
				} else {
					y = e.getY() + e.getHeight();
				}
				velY = 0;
			}
		}

		if (x + width < 0 || x > rightBound || y >= 600) {
			dead = true;
		}

	}

	public void show() {

		if(velX > 0) {
			p.image(spritesR[(frame / 6) % spritesR.length], x, y);
		} else {
			p.image(spritesL[(frame / 6) % spritesL.length], x, y);
		}
		
		frame ++;
	}

}