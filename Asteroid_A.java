import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Asteroid_A extends Asteroid {
	BufferedImage asteroid_A;

	
	public Asteroid_A(int x, int y, double minVelocity, double maxVelocity, double direction,int width,int life,BufferedImage asteroid_A) {
		super(x, y, minVelocity, maxVelocity, direction, width,life);
		// TODO Auto-generated constructor stub
		
		double vel = minVelocity + Math.random()*(maxVelocity-minVelocity);
		super.setDirection(2*Math.PI*Math.random());
		super.setxVelocity(vel*Math.cos(super.getDirection()));
		super.setyVelocity(vel*Math.sin(super.getDirection()));
		super.setWidth(width);
		super.setLife(life);
		this.asteroid_A = asteroid_A;
	}
	
	public void move() {
		super.setX(super.getX()+super.getxVelocity());
		super.setY(super.getY()+super.getyVelocity());
		
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawImage(asteroid_A,(int)super.getX(), (int) super.getY(), super.getWidth(), super.getWidth(),null);
		
	}

	


}
