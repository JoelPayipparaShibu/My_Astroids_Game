import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

//@author joel shibu

public class Asteroid_B extends Asteroid {
	BufferedImage asteroid_B;

	
	public Asteroid_B(int x, int y, double minVelocity, double maxVelocity, double direction,int width,int life,BufferedImage asteroid_B) {
		super(x, y, minVelocity, maxVelocity, direction,width, life);
		// TODO Auto-generated constructor stub
		
		double vel = minVelocity + Math.random()*(maxVelocity-minVelocity);
		super.setDirection(2*Math.PI*Math.random());
		super.setxVelocity(vel*Math.cos(super.getDirection()));
		super.setyVelocity(vel*Math.sin(super.getDirection()));
		super.setWidth(width);
		super.setLife(life);
		this.asteroid_B = asteroid_B;
	}
	
	public void move() {
		super.setX(super.getX()+super.getxVelocity());
		super.setY(super.getY()+super.getyVelocity());
		
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawImage(asteroid_B,(int)super.getX(), (int) super.getY(), getWidth(), getWidth(),null);
		
	}
	public void splitting() {
		
	}

	
	
}
