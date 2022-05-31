import processing.core.PApplet;

public class Particle {

	private float x;
	private float y;
	
	private float velX;
	private float velY;
	
	private float g = 0.25f;
	
	private int life;
	
	private PApplet p;
	
	public Particle(float x, float y, float velX, float velY, PApplet parent) {
		
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		
		life = (int)(Math.random()*50) + 50;
		
		p = parent;
		
	}
	
	public boolean update() {
		
		p.stroke(0, 255, 255);
		p.strokeWeight(2);
		
		p.line(x,  y, (float) (x + velX * 0.5), (float) (y + velY * 0.5));
		
		life --;
		
		x += velX;
		y += velY;
		
		velY += g;
		
		if(life <= 0 || y >= 600) return false;
		
		return true;
		
	}
	
}
