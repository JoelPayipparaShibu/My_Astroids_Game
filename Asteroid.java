import java.awt.Graphics;

import java.util.ArrayList;

//@author joel shibu

public abstract class Asteroid {
	
	private int life,width;
	private double x,y;
	private double xVelocity,yVelocity,direction;

	
	public Asteroid(int x, int y, double minVelocity, double maxVelocity, double direction,int width,int Life) {
		this.x = x;
		this.y = y;
		
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public double getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}

	public double getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public double getX() {
		return x;
	}

	public void setX(double d) {
		this.x = d;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public abstract void move();
	public abstract void  draw(Graphics g);
	//public abstract void  collisionWithbullet(ArrayList<Bullets> bullets);

}
