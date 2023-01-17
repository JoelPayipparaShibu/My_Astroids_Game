import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//@author joel shibu

public class Ship {
	private int x, y, width, length, life;
	BufferedImage ship1;
	ArrayList<Bullets> shot;
	int score;

	public Ship(int x, int y, int width, int length, int life, BufferedImage ship1) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.length = length;
		shot = new ArrayList<Bullets>();
		this.ship1 = ship1;
		this.life = life;

	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Bullets> getShot() {
		return shot;
	}

	public void setShot(ArrayList<Bullets> shot) {
		this.shot = shot;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		// g.fillRect(x, y, width, length);
		g.drawImage(ship1, x, y, width, length, null);

	}

	public void outofScreen(ArrayList<Asteroid> asteroids) {                  //when stuf goes out of screen
		if (x > 1366) {
			x = -20;
		}
		if (x < -20) {
			x = 1370;
		}
		if (y > 768) {
			y = -20;
		}
		if (y < -20) {
			y = 778;
		}
		for (int j = 0; j < asteroids.size(); j++) {
			if (asteroids.get(j).getX() > 1368 || asteroids.get(j).getX() < -20 || asteroids.get(j).getY() > 778
					|| asteroids.get(j).getY() < -20) {
				asteroids.remove(j);
			}
		}
		for (int i = 0; i < shot.size(); i++) {

			if (shot.get(i).getbX() > 1368 || shot.get(i).getbX() < -20 || shot.get(i).getbY() > 778
					|| shot.get(i).getbY() < -20) {
				shot.remove(i);
			}
		}

	}

	public void addingBullets(int mouseX, int mouseY) {

		shot.add(new Bullets(x + (width / 2), y + (length / 2), mouseX, mouseY, 20.0));

	}

	public void asteroidWithbullet(ArrayList<Asteroid> asteroids) {
		// x < x2 + w2 && y < y2 + h2 && x2 < x + w && y2 < y + h - the collision rule
		System.out.println(asteroids.size() + "  " + shot.size());

		for (int j = 0; j < asteroids.size(); j++) {
			for (int i = 0; i < shot.size(); i++) {
				if (asteroids.get(j).getX() < shot.get(i).getbX() + 10
						&& asteroids.get(j).getY() < shot.get(i).getbY() + 10
						&& shot.get(i).getbX() < asteroids.get(j).getX() + asteroids.get(j).getWidth()
						&& shot.get(i).getbY() < asteroids.get(j).getY() + asteroids.get(j).getWidth()) {
					shot.remove(i);
					score += 10;
					asteroids.get(j).setLife(asteroids.get(j).getLife() - 1);
					if (asteroids.get(j).getLife() == 0) {
						asteroids.remove(j);
					}

				}
			}
		}

	}

}
