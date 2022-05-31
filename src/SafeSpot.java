import processing.core.PApplet;
import processing.core.PImage;

public class SafeSpot extends Block {

	private int r = 200;
	public int getRadius() {return r;}
	
	public SafeSpot(float x, float y, int width, int height, PImage sprite, PApplet parent) {
		
		super(x, y, width, height, sprite, parent);
		
	}
	
	public void show() {
		p.stroke(0);
		p.strokeWeight(1);
		p.fill(0, 255, 255);
		p.ellipse(getCenterX(),  getCenterY(),  width,  height);
		
		
		p.stroke(0, 0, 255);
		p.fill(0, 255, 255, 100);
		p.ellipse(getCenterX(), getCenterY(), 2 * r, 2 * r);
	}
	
}
