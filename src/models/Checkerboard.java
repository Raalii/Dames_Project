package models;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

class Checkerboard extends JPanel {

	private static final long serialVersionUID = 1L;
	static final int CHECKER_SIZE = 120;

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		int nbrOfBlackCases = 1;

		for (int y = 0, row = 0; y < 600; y += CHECKER_SIZE / 2, ++row) {
			for (int stripeX = 0; stripeX < 600; stripeX += CHECKER_SIZE) {
				int x = (row % 2 != 0) ? stripeX : (stripeX + CHECKER_SIZE / 2);
				/*
				 * Code qui ne fonctionne pas 
				 * add(new Case(Color.BLACK, x, y, CHECKER_SIZE/2));
				 * Ajoute une case mais d'une dimension très inferieure et dans un "plan" différent
				 * */
				g.setColor(Color.BLACK);
				g.fillRect(x, y, CHECKER_SIZE/2, CHECKER_SIZE/2);

				if (nbrOfBlackCases < 21) {
					/*
					 * Code qui ne fonctionne pas 
					 * Pour le pion, le "cercle" ne s'affiche même pas
					 * 
					 * */
					g.setColor(Color.GRAY);
					g.fillOval(x + 5, y + 5, 50, 50);
				} else if (nbrOfBlackCases > 30) {
					g.setColor(Color.WHITE);
					g.fillOval(x + 5, y + 5, 50, 50);
				}

				nbrOfBlackCases++;
			}
		}
	}
}
