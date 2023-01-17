import java.awt.Color;
import java.awt.Graphics;

//@author joel shibu

public class Bullets {
	
	private int bX,bY;
	private double rise,run,mouseX,mouseY;
	private double bulletvelocity, angle, xVelocity,yVelocity;
	 
	public Bullets(int bX, int bY,int mouseX,int mouseY,double bulletvelocity) {
		this.bX = bX;
		this.bY = bY;
		this.mouseX=mouseX;
		this.mouseY= mouseY;
		this.bulletvelocity = bulletvelocity;
	 rise = (mouseY - bY);
	 run = (mouseX - bX);
	}

	public int getbX() {
		return bX;
	}

	public void setbX(int bX) {
		this.bX = bX;
	}

	public int getbY() {
		return bY;
	}

	public void setbY(int bY) {
		this.bY = bY;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.gray);
		g.fillOval(bX, bY, 10, 10);
		
	}
	public void shooting() {
		angle = Math.atan2(rise,run);
		xVelocity = (bulletvelocity)*Math.cos(angle);
		yVelocity = (bulletvelocity)*Math.sin(angle);
		bX += xVelocity;
		bY += yVelocity;
	}

}
