import processing.core.PApplet;

public class Entity {

	protected float x;
	protected float y;

	protected int width;
	protected int height;

	protected PApplet p;

	protected float velX = 0;
	protected float velY = 0;

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getCenterX() {
		return x + width / 2;
	}

	public float getCenterY() {
		return y + height / 2;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Entity(float x, float y, int width, int height, PApplet parent) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		p = parent;

	}

	public boolean collidesWith(Entity obj) {

		return ((x + width > obj.getX()) && (x < obj.getX() + obj.getWidth()) && (y + height > obj.getY())
				&& (y < obj.getY() + obj.getHeight()));

	}

}