import processing.core.PApplet;

public class Button {

  private int x;
  private int y;
  private int width;
  private int height;

  private PApplet p;

  public Button(int x, int y, int width, int height, PApplet parent) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;

    p = parent;
  }
  
  public void show() {
    p.rect(x, y, width, height, 40);
  }

  public boolean checkMouse() {

    return (p.mouseX > x && p.mouseX < x + width && p.mouseY > y && p.mouseY < y + height);
    
  }

  
}