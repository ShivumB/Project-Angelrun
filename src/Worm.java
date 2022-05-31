import processing.core.PApplet;
import processing.core.PImage;

public class Worm extends Entity {

	private float velX = 0;
	private float velY = 0;

	private float theta = 0;

	private PImage sprite;

	public Worm(float x, float y, int width, int height, PApplet parent) {
		super(x, y, width, height, parent);

		sprite = p.loadImage("worm.png");

	}

	public void show(Player player) {

		p.pushMatrix();

		p.translate(getCenterX(), getCenterY());

		// Calculate pupil - distance from head to player
		float distX = (float) (29 * Math.sqrt(2) * Math.cos(theta + Math.PI / 4)) - player.getCenterX();
		float distY = (float) (29 * Math.sqrt(2) * Math.sin(theta + Math.PI / 4)) - player.getCenterY();

		float eyeTheta = (float) Math.atan2(distY, distX);
		float eyeX = -5 * (float) Math.cos(eyeTheta);
		float eyeY = -5 * (float) Math.sin(eyeTheta);

		p.rotate(theta - (float) Math.PI / 4);

		p.image(sprite, -20, -20, 40, 40);

		p.stroke(0);
		p.strokeWeight(1);
		p.fill(0);
		p.ellipse(9 + eyeX, 9 + eyeY, 10, 10);

		p.popMatrix();

		//

	}

	public void attract(Player player) {

		theta = (float) Math.atan2(velY, velX);

		float distX = x - player.getCenterX();
		float distY = y - player.getCenterY();

		float dist = (float) Math.sqrt(distX * distX + distY * distY);

		if (dist < 20)
			return;

		float accel = -4000 / (dist * dist);

		if (velX < -30)
			velX = -30;
		if (velX > 30)
			velX = 30;

		if (velY < -30)
			velY = -30;
		if (velY > 30)
			velY = 30;

		velX += accel * distX / dist;
		velY += accel * distY / dist;

		x += velX;
		y += velY;

		distX = x - player.getCenterX();
		distY = y - player.getCenterY();

		float newDist = (float) Math.sqrt(distX * distX + distY * distY);

		if (newDist > dist && dist >= 150) {
			velX = 0;
			velY = 0;
		}

	}

}
