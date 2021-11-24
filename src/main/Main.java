package main;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		Runnable showMyGUI = new Runnable() {
			public void run() {
				new models.Window();
			}
		};
		SwingUtilities.invokeLater(showMyGUI);

	}

}
