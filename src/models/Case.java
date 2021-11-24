package models;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Case extends JPanel implements ActionListener {

	Color couleur;
	int posX;
	int posY;
	int size;
	private static final long serialVersionUID = 1L;

	public Case(Color couleur, int posX, int posY, int size) {
		this.couleur = couleur;
		this.posX = posY;
		this.posY = posY;
		this.size = size;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(this.couleur);
		g.fillRect(posX, posY, size * 100 / 2, size * 100 / 2);
	}

	// Couleur color;
}
