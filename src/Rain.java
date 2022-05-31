import processing.core.*;

public class Rain {

	private static float[][] particles = new float[100][3];

	static {

		for (int i = 0; i < particles.length; i++) {
			particles[i][0] = (float) (800 * Math.random());
			particles[i][1] = (float) (600 * Math.random());
			particles[i][2] = (float) (10 * Math.random());
		}

	}

	public static void show(PApplet p, boolean update) {

		p.fill(0, 0, 255);
		p.noStroke();

		for (int i = 0; i < particles.length; i++) {

			p.rect(particles[i][0], particles[i][1], 2, particles[i][2]);

			if (update) {
				particles[i][1] += 15;
			}

			if (particles[i][1] > 600) {
				particles[i][1] = -10;
				particles[i][0] = (float) (800 * Math.random());
				particles[i][2] = (float) (10 * Math.random());
			}

		}

	}

}
