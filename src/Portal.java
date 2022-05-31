import processing.core.PApplet;
import processing.core.PImage;

public class Portal extends Block {

	private float theta = 0;

	private Particle[] particles = new Particle[100];

	public Portal(float x, float y, int width, int height, PImage sprite, PApplet parent) {

		super(x, y, width, height, sprite, parent);

		for (int i = 0; i < particles.length; i++) {
			particles[i] = new Particle(x + 20, y + 20, (float) (20 * Math.random()), (float) (20 * Math.random()), p);
		}

	}

	public void show(Player player) {

		if (Math.abs(player.getX() - x) >= 800)
			return;

		p.pushMatrix();

		p.translate(x + 20, y + 20);

		p.rotate(theta);

		p.image(sprite, -20, -20, 40, 40);

		theta += Math.random();
		theta %= 2 * Math.PI;

		p.popMatrix();

		for (int i = 0; i < particles.length; i++) {

			if (!particles[i].update()) {

				float baseVelX = 5 * (float) Math.cos(theta);
				float baseVelY = 5 * (float) Math.sin(theta);

				particles[i] = new Particle(x + 20, y + 20, baseVelX + (float) (2 * Math.random() - 4),
						baseVelY + (float) (2 * Math.random() - 4), p);
			}

		}

	}

}
