import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	GUI g;

	public KeyInput(GUI gr) {
		g=gr;
	}
	public void keyPressed(KeyEvent e) {
		g.keyPressed(e);
	}
	public void keyReleased(KeyEvent e) {
		g.keyReleased(e);
	}
}

