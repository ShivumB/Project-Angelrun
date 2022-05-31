import processing.core.PApplet;
import java.util.*;

public class Cannon extends Entity {

	private ArrayList<Worm> worms = new ArrayList<>();
	private int maxWorms = 3;
	private int delay = 10;
	
	public Cannon(float x, float y, int width, int height, PApplet parent) {
		super(x, y, width, height, parent);
	}

	public void shoot() {
		if (worms.size() < maxWorms && delay == 0) {
			worms.add(new Worm(x, y, 40, 40, p));
			delay = 255;
		}
		delay--;
		if(delay < 0) {
			delay = 255;
		}
	}

	public void show() {
		
		p.noStroke();
		
		p.fill(0);
		p.ellipse(x + 20, y + 20, 40, 40);
		
		p.fill(255 - delay, 0, 0);
		p.ellipse(x + 20, y + 20, 30, 30);
		
	}

	public void update(Player player, ArrayList<SafeSpot> safeSpots) {

		shoot();
		show();
		
		if(player.getReset()) {
			worms.clear();
		}
		
		for (int i = 0; i < worms.size(); i++) {

			if (Math.abs(worms.get(i).getX() - player.getX()) <= 800) {

				worms.get(i).attract(player);
				worms.get(i).show(player);

				//kill player if contact
				if (worms.get(i).collidesWith(player)) {
					player.setDead(true);
				}
				
				// if worm dies, purge
				for (int j = 0; j < safeSpots.size(); j++) {

					if(i >= worms.size()) {
						break;
					}
					
					float distX = worms.get(i).getCenterX() - safeSpots.get(j).getCenterX();
					float distY = worms.get(i).getCenterY() - safeSpots.get(j).getCenterY();

					float dist = (float) Math.sqrt(distX * distX + distY * distY);
										
					if (dist < safeSpots.get(j).getRadius() + 20) {
						worms.remove(i);
					}

				}
			}

		}

	}

}
