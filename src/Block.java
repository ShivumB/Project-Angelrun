import processing.core.PApplet;
import processing.core.PImage;

public class Block extends Entity {

	protected PImage sprite;
	protected String type;
	protected boolean dead;
	public void setDead(boolean dead) {this.dead = dead;}
	public boolean getDead() {return dead;}
	
	public String getType() {
		return type;
	}

	public Block(float x, float y, int width, int height, PImage sprite, PApplet parent) {

		super(x, y, width, height, parent);
		
		this.sprite = sprite;

	}

	public void show() {		
		p.image(sprite, x, y, 40, 40);
	}

}