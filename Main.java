import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame w = new JFrame();
		w.setSize(1366, 760);
		w.setTitle("Asteroids");
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setResizable(false);
		GUI m = new GUI();
		w.add(m);
		w.addKeyListener(new KeyInput(m));
		w.setVisible(true);

	}

}