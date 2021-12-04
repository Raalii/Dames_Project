package main;

import java.awt.Dimension;

import javax.swing.JFrame;

import models.Checkers;
import utils.Utils;;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame("Checkers");
		Checkers content = new Checkers();
		window.setContentPane(content);
		window.pack();
		// Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize(new Dimension(1200, 900));
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);
	}

}