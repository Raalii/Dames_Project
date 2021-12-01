package main;

import java.awt.Dimension;

import javax.swing.JFrame;

import models.Checkers;
import utils.Utils;;

public class Main {

	 public static void main(String[] args) {
		 	System.out.println("Joueur 1 veuillez entrer votre prénom : ");
		 	String player1 = Utils.readString();
		 	System.out.println("Joueur 2 veuillez entrer votre prénom : ");
		 	String player2 = Utils.readString();
	        JFrame window = new JFrame("Checkers");
	        Checkers content = new Checkers(player1, player2);
	        window.setContentPane(content);
	        window.pack();
	        // Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	        window.setSize(new Dimension(1200, 900));
	        window.setLocationRelativeTo(null);
	        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	        window.setResizable(true);  
	        window.setVisible(true);
	    }

}
