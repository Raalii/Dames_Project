package models;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import data.CheckersData;
import data.CheckersMove;

/**
 * This panel lets two users play checkers against each other. Red always starts
 * the game. If a player can jump an opponent's piece, then the player must
 * jump. When a player can make no more moves, the game ends.
 * 
 * The class has a main() routine that lets it be run as a stand-alone
 * application.
 */
public class Checkers extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Main routine makes it possible to run Checkers as a stand-alone
	 * application. Opens a window showing a Checkers panel; the program ends
	 * when the user closes the window.
	 */

	// ---------------------------------------------------------------------

	private JButton newGameButton; // Button for starting a new game.

	private JButton resignButton; // Button that a player can use to end
									// the game by resigning.
	private JButton goToMenuButton; // Button to go to the menu

	private JButton launchGameButton; // Button to start the gale (after the was
										// defines by players).

	private JLabel messageMenu; // Differents message to be send in menu (no
								// player2's name has selected...)

	private JTextField firstPlayer, secondPlayer; // Name define in the input;

	private boolean firstGame = false;

	private JLabel message; // Label for displaying messages to the user.

	String player1;
	String player2;

	Board board;

	public Checkers() {
		init();
		doGoToMenu();

	}

	void init() {
		setLayout(null); // I will do the layout myself.
		setPreferredSize(new Dimension(350, 250));

		setBackground(new Color(50, 50, 50));
		/* Create the components and add them to the panel. */

		board = new Board();
	}

	private void initGame(String player1, String player2) {
		if (launchGameButton != null && firstPlayer != null && secondPlayer != null) {
			remove(launchGameButton);
			remove(firstPlayer);
			remove(secondPlayer);
		}

		this.player1 = player1;
		this.player2 = player2;
		init();
		add(board);
		add(newGameButton);
		add(resignButton);
		add(message);
		add(goToMenuButton);

		board.setSize(new Dimension(800, 800));
		goToMenuButton.setBounds(810, 200, 240, 60);
		newGameButton.setBounds(810, 60, 240, 60);
		resignButton.setBounds(810, 130, 240, 60);
		message.setBounds(0, 800, 700, 60);

	}

	void doGoToMenu() {
		// Supprimer ou ne plus afficher les élements actuels.
		// Réafficher le menu classique
		remove(board);
		remove(newGameButton);
		remove(resignButton);
		remove(message);
		remove(goToMenuButton);
		add(launchGameButton);
		add(firstPlayer);
		add(secondPlayer);
		launchGameButton.setBounds(810, 130, 240, 60);
		repaint();
	}

	private class Board extends JPanel implements ActionListener, MouseListener {

		private static final long serialVersionUID = 1L;

		CheckersData board; // The data for the checkers board is kept here.
							// This board is also responsible for generating
							// lists of legal moves.

		boolean gameInProgress; // Is a game currently in progress?

		/*
		 * The next three variables are valid only when the game is in progress.
		 */

		int currentPlayer;

		int selectedRow, selectedCol;

		CheckersMove[] legalMoves; // An array containing the legal moves for
									// the
									// current player.

		Board() {
			setBackground(Color.BLACK);
			addMouseListener(this);
			// Definition of all components, also add event
			resignButton = new JButton("Abandonner");
			resignButton.addActionListener(this);
			newGameButton = new JButton("Nouvelle Partie");
			newGameButton.addActionListener(this);
			goToMenuButton = new JButton("Revenir au menu");
			goToMenuButton.addActionListener(this);
			launchGameButton = new JButton("Jouer la partie !");
			launchGameButton.addActionListener(this);
			firstPlayer = new JTextField("Nom du joueur 1");
			secondPlayer = new JTextField("Nom du joueur 2");
			firstPlayer.setBounds(20, 40, 200, 28);
			secondPlayer.setBounds(20, 80, 200, 28);
			message = new JLabel("", JLabel.CENTER);
			message.setFont(new Font("Serif", Font.BOLD, 25));
			message.setForeground(Color.PINK);
			board = new CheckersData();
			doNewGame();
		}

		public void actionPerformed(ActionEvent evt) {
			Object src = evt.getSource();
			if (src == newGameButton)
				doNewGame();
			else if (src == resignButton)
				doResign();
			else if (src == goToMenuButton)
				doGoToMenu();
			else if (src == launchGameButton) {
				initGame(firstPlayer.getText(), secondPlayer.getText());
			}
		}

		void doNewGame() {
			if (gameInProgress == true) {

				message.setText("Veuillez terminer la partie courante d'abord, ou abadonner la partie");
				return;
			}
			board.setUpGame(); // Set up the pieces.
			currentPlayer = CheckersData.RED; // RED moves first (=white in real
												// rules)
			legalMoves = board.getLegalMoves(CheckersData.RED); // Get RED's
																// legal moves.
			selectedRow = -1; // RED has not yet selected a piece to move (start
								// of game)
			message.setText(player1 + " : A vous de jouer, faites votre mouvement !");
			gameInProgress = true;
			newGameButton.setEnabled(false);
			resignButton.setEnabled(true);
			repaint();
		}

		void doResign() {
			if (gameInProgress == false) { // Should be impossible.
				message.setText("Il n'y a pas de partie en cours !");
				return;
			}
			if (currentPlayer == CheckersData.RED)
				gameOver(player1 + " a abandonné : " + player2 + " a gagné !");
			else
				gameOver(player2 + " a abandonné : " + player1 + " a gagné !");
		}

		void gameOver(String str) {
			message.setText(str);
			newGameButton.setEnabled(true);
			resignButton.setEnabled(false);
			gameInProgress = false;
		}

		void doClickSquare(int row, int col) {

			for (int i = 0; i < legalMoves.length; i++)
				if (legalMoves[i].getFromRow() == row && legalMoves[i].getFromCol() == col) {
					selectedRow = row;
					selectedCol = col;
					if (currentPlayer == CheckersData.RED)
						message.setText(player1 + " : A vous de jouer, faites votre mouvement !");
					else
						message.setText(player2 + " : A vous de jouer, faites votre mouvement !");
					repaint();
					return;
				}

			if (selectedRow < 0) {
				message.setText("Veuillez cliquer sur une pièce pour initier son mouvement");
				return;
			}

			for (int i = 0; i < legalMoves.length; i++)
				if (legalMoves[i].getFromRow() == selectedRow && legalMoves[i].getFromCol() == selectedCol
						&& legalMoves[i].getToRow() == row && legalMoves[i].getToCol() == col) {
					doMakeMove(legalMoves[i]);
					return;
				}

			message.setText("Veuillez cliquer sur la case où vous voulez vous diriger");

		}

		void doMakeMove(CheckersMove move) {

			board.makeMove(move);

			if (move.isJump()) {
				legalMoves = board.getLegalJumpsFrom(currentPlayer, move.getToRow(), move.getToCol());
				if (legalMoves != null) {
					if (currentPlayer == CheckersData.RED)
						message.setText(player1 + " vous devez continuer à manger ! MIAM");
					else
						message.setText(player2 + " vous devez continuer à manger ! MIAM");
					selectedRow = move.getToRow(); // Since only one piece can
													// be moved, select it.
					selectedCol = move.getToCol();
					repaint();
					return;
				}
			}

			if (currentPlayer == CheckersData.RED) {
				currentPlayer = CheckersData.BLACK;
				legalMoves = board.getLegalMoves(currentPlayer);
				if (legalMoves == null)
					gameOver(player2 + " n'a plus de pions, notre vainqueur est : " + player1);
				else if (legalMoves[0].isJump())
					message.setText(player2 + " vous devez manger le pion !");
				else
					message.setText(player2 + " : A vous de jouer, faites votre mouvement !");
			} else {
				currentPlayer = CheckersData.RED;
				legalMoves = board.getLegalMoves(currentPlayer);
				if (legalMoves == null)
					gameOver(player1 + " n'a plus de pions, notre vainqueur est : " + player2);
				else if (legalMoves[0].isJump())
					message.setText(player1 + " vous devez manger le pion !");
				else
					message.setText(player1 + " : A vous de jouer, faites votre mouvement !");
			}

			selectedRow = -1;

			if (legalMoves != null) {
				boolean sameStartSquare = true;
				for (int i = 1; i < legalMoves.length; i++)
					if (legalMoves[i].getFromRow() != legalMoves[0].getFromRow()
							|| legalMoves[i].getFromCol() != legalMoves[0].getFromCol()) {
						sameStartSquare = false;
						break;
					}
				if (sameStartSquare) {
					selectedRow = legalMoves[0].getFromRow();
					selectedCol = legalMoves[0].getFromCol();
				}
			}

			/* Make sure the board is redrawn in its new state. */

			repaint();

		}

		public void paintComponent(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			/* Draw a two-pixel black border around the edges of the canvas. */

			g.setColor(Color.black);
			g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
			g.drawRect(1, 1, getSize().width - 3, getSize().height - 3);

			/* Draw the squares of the checkerboard and the checkers. */

			for (int row = 0; row < 10; row++) {
				for (int col = 0; col < 10; col++) {
					if (row % 2 == col % 2)
						g.setColor(Color.WHITE);
					else
						g.setColor(Color.GRAY);
					g.fillRect(2 + col * 80, 2 + row * 80, 80, 80);
					switch (board.pieceAt(row, col)) {
					case CheckersData.RED:
						g.setColor(Color.RED);
						g.fillOval(10 + col * 80, 10 + row * 80, 60, 60);
						break;
					case CheckersData.BLACK:
						g.setColor(Color.BLACK);
						g.fillOval(10 + col * 80, 10 + row * 80, 60, 60);
						break;
					case CheckersData.RED_KING:
						g.setColor(Color.RED);
						g.fillOval(10 + col * 80, 10 + row * 80, 60, 60);
						g.setColor(Color.WHITE);
						g.setFont(new Font("Serif", Font.BOLD, 22));
						g.drawString("K", 32 + col * 80, 48 + row * 80);
						break;
					case CheckersData.BLACK_KING:
						g.setColor(Color.BLACK);
						g.setFont(new Font("Serif", Font.BOLD, 22));
						g.fillOval(10 + col * 80, 10 + row * 80, 60, 60);
						g.setColor(Color.WHITE);
						g.drawString("K", 32 + col * 80, 48 + row * 80);
						break;
					}
				}
			}

			if (gameInProgress) {
				g.setColor(Color.CYAN);
				for (int i = 0; i < legalMoves.length; i++) {
					g.drawRect(2 + legalMoves[i].getFromCol() * 80, 2 + legalMoves[i].getFromRow() * 80, 79, 79);
					g.drawRect(3 + legalMoves[i].getFromCol() * 80, 3 + legalMoves[i].getFromRow() * 80, 79, 79);
				}

				if (selectedRow >= 0) {
					g.setColor(Color.white);
					g.drawRect(2 + selectedCol * 80, 2 + selectedRow * 80, 79, 79);
					g.drawRect(3 + selectedCol * 80, 3 + selectedRow * 80, 79, 79);
					g.setColor(Color.GREEN);
					for (int i = 0; i < legalMoves.length; i++) {
						if (legalMoves[i].getFromCol() == selectedCol && legalMoves[i].getFromRow() == selectedRow) {
							g.drawRect(2 + legalMoves[i].getToCol() * 80, 2 + legalMoves[i].getToRow() * 80, 79, 79);
							g.drawRect(3 + legalMoves[i].getToCol() * 80, 3 + legalMoves[i].getToRow() * 80, 79, 79);
						}
					}
				}
			}

		}

		public void mousePressed(MouseEvent evt) {
			if (gameInProgress == false)
				message.setText("Click \"New Game\" to start a new game.");
			else {
				int col = (evt.getX() - 2) / 80;
				int row = (evt.getY() - 2) / 80;
				if (col >= 0 && col < 10 && row >= 0 && row < 10)
					doClickSquare(row, col);
			}
		}

		public void mouseReleased(MouseEvent evt) {
		}

		public void mouseClicked(MouseEvent evt) {
		}

		public void mouseEntered(MouseEvent evt) {
		}

		public void mouseExited(MouseEvent evt) {
		}

	}

}
