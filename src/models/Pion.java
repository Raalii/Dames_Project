package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Pion extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	Color couleur;
	int posX;
	int posY;
	int size;
	
	
	public Pion() {
		super();
	}
	
	
	public Pion(int posX, int posY, int size, Color couleur) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.couleur = couleur;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*
		 * Check event action of the user (capture click, etc...)
		 * */
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(this.couleur);
		g.fillOval(posX, posY, size, size);
	}	

}