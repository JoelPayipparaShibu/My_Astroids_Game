
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;


//@author joel shibu

public class GUI extends JPanel implements ActionListener {
	// Create variables

	Timer t;
	int mouseX;
	int mouseY;
	int frametimmer;
	int r = (int) (Math.random() * 10 + 1);
	int r1 = (int) (Math.random() * 2 + 1);

	FileWriter f1;
	PrintWriter p;
	File f;
	Scanner s = null;

	boolean collision = true, swaped = true;

	BufferedImage ship, endScreen, asteroid_B, asteroid_A, background;

	Ship s1;

	ArrayList<Asteroid> asteroids;
	ArrayList<Integer> scores;

	Font font = new Font("Serif", Font.ITALIC | Font.BOLD, 30);
	Font font1 = new Font("Serif", Font.ITALIC | Font.BOLD, 60);

	// end of variables//

	public GUI() {

		asteroids = new ArrayList<Asteroid>();
		scores = new ArrayList<Integer>();

		try {
			f1 = new FileWriter("src/Scores.txt", true);
			f = new File("src/Scores.txt");
			p = new PrintWriter(f1);
			s = new Scanner(f);
			ship = ImageIO.read(new File("src/ship2.png"));
			endScreen = ImageIO.read(new File("src/EndScreen.png"));
			asteroid_B = ImageIO.read(new File("src/Asteroid_B.png"));
			asteroid_A = ImageIO.read(new File("src/Asteroid_A.png"));
			background = ImageIO.read(new File("src/Background.png"));
		} catch (IOException e) {
			System.out.println("NOT LOADED");
		}

		s1 = new Ship(300, 300, 50, 50, 3, ship);
		initComponents();
		t = new Timer(30, this);
		t.start();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// all your drawing will go in here
		g.drawImage(background, 0, 0, 1366, 760, this);
		g.setFont(font);
		s1.draw(g);

		s1.outofScreen(asteroids);
		String score = Integer.toString(s1.score);
		g.drawString("Score: " + score, 1207, 37);
		g.drawString("Lives: " + s1.getLife() + "", 1207, 70);
		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).draw(g);
		}
		for (int i = 0; i < s1.getShot().size(); i++) {
			s1.getShot().get(i).draw(g);
		}
		if (collision) {
			shipWithAsteroid();
			s1.asteroidWithbullet(asteroids);
		}
		if (s1.getLife() <= 0) {
			death(g);
		}

		//// end of your drawing
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// x < x2 + w2 && y < y2 + h2 && x2 < x + w && y2 < y + h
		level1();
		recallingHighScores();
		soarting();
		if (r1 == 1) { // choosing asteroid A or B
			asteroids.add(new Asteroid_A(0, 0, 2, 10, 29, 20, 1, asteroid_A));
		}
		if (r1 == 2) {
			asteroids.add(new Asteroid_B(0, 0, 2, 10, 29, 30, 2, asteroid_B));
		}

		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).move();
		}
		for (int i = 0; i < s1.getShot().size(); i++) {
			s1.getShot().get(i).shooting();
		}

		repaint();
	}

	public void recallingHighScores() {
		while (s.hasNextInt()) {
			scores.add(s.nextInt());
		}
	}

	public void shipWithAsteroid() {
		// x < x2 + w2 && y < y2 + h2 && x2 < x + w && y2 < y + h - the collision rule
		for (int i = 0; i < asteroids.size(); i++) {
			if (s1.getX() < asteroids.get(i).getX() + asteroids.get(i).getWidth()
					&& s1.getY() < asteroids.get(i).getY() + asteroids.get(i).getWidth()
					&& asteroids.get(i).getX() < s1.getX() + s1.getWidth()
					&& asteroids.get(i).getY() < s1.getY() + s1.getLength()) {
				asteroids.remove(i);
				System.out.println("life Before" + s1.getLife());
				s1.setLife(s1.getLife() - 1);
				System.out.println("life After" + s1.getLife());
			}
		}
	}

	public void death(Graphics g) {
		String score = Integer.toString(s1.score);
		printingToFile();
		collision = false;
		g.setColor(Color.BLACK);
		g.drawImage(endScreen, 0, 0, 1366, 766, this);
		g.setColor(Color.RED);
		g.setFont(font1);
		g.drawString(score, 686, 338);
		g.setFont(font);
		g.drawString("1)" + scores.get(scores.size() - 1) + " ", 659, 470);
		g.drawString("2)" + scores.get(scores.size() - 2) + " ", 659, 505);
		g.drawString("3)" + scores.get(scores.size() - 3) + " ", 659, 540);
		g.drawString("4)" + scores.get(scores.size() - 4) + " ", 659, 575);
		g.drawString("5)" + scores.get(scores.size() - 5) + " ", 659, 610);

	}

	public void level1() {
		frametimmer++; // creating the different asteroids
		if (frametimmer > r) {
			r = (int) (Math.random() * 10 + 1);
			r1 = (int) (Math.random() * 2 + 1);
			frametimmer = 0;
		}
	}

	public void printingToFile() {
		p.println(s1.score);
		p.close();
	}

	public ArrayList<Integer> soarting() {
		int temp;
		while (swaped) {
			swaped = false;
			for (int j = 0; j < scores.size() - 1; j++) {
				if (scores.get(j) > scores.get(j + 1)) {
					temp = scores.get(j);
					scores.set(j, scores.get(j + 1));
					scores.set(j + 1, temp);
					swaped = true;
				}
			}
		}
		return scores;

	}

	public void keyPressed(KeyEvent e) {
		System.out.println("keyPressed=" + KeyEvent.getKeyText(e.getKeyCode()));
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			s1.setX(s1.getX() - 10);
			System.out.println(s1.score);
		}
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			s1.setY(s1.getY() - 10);
		}
		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			s1.setX(s1.getX() + 10);
		}
		if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			s1.setY(s1.getY() + 10);
		}
		if (key == KeyEvent.VK_SPACE) {

		}
		if (key == KeyEvent.VK_S || key == KeyEvent.VK_ENTER) {

		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {

		}
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {

		}
		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {

		}
		if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {

		}
		if (key == KeyEvent.VK_SPACE) {

		}
		if (key == KeyEvent.VK_S || key == KeyEvent.VK_ENTER) {

		}
	}

	// USED FOR INTERACTION THAT INCLUDES DRAGGING OF MOUSE
	public void dragMouse(MouseEvent e) {
	}

	// USED FOR INTERACTION THAT INCLUDES RELEASING OF MOUSEBUTTON
	public void releaseMouse(MouseEvent e, Graphics g) {
	}

	private void formMouseClicked(java.awt.event.MouseEvent evt, Graphics g) {// GEN-FIRST:event_formMouseClicked
		mouseX = evt.getX();
		mouseY = evt.getY();

		System.out.println(mouseX + "," + mouseY);
		s1.addingBullets(mouseX, mouseY);
	}
	// GEN-LAST:event_formMouseClicked

	private void initComponents() {
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

		this.setLayout(layout);
		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Graphics g = null;
				formMouseClicked(evt, g);
			}
		});
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));

	}
}
