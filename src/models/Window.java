package models;



import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
	private static final long serialVersionUID = -20193400647936990L;

	public Window() {
		JFrame f = new JFrame("Jeux de Dame en Java");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1620, 640);
		f.setLocationRelativeTo(null);
		JPanel damier = new Checkerboard();
		//JPanel Pion = new Pion(null, rootPaneCheckingEnabled);
		f.add(damier);
		f.setVisible(true);
	}
}
