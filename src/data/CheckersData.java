package data;

import java.util.ArrayList;

import utils.Utils;

public class CheckersData {

	static final int EMPTY = 0;

	public static final int RED = 1;

	public static final int RED_KING = 2;

	public static final int BLACK = 3;

	public static final int BLACK_KING = 4;

	public static String textFileName = "";

	int[][] board; // board[r][c] is the contents of row r, column c.

	/**
	 * Constructor. Create the board and set it up for a new game.
	 */
	public CheckersData() {
		board = new int[10][10];
		setUpGame();
	}

	public void updateTextFile(String player1, String player2) {
		textFileName = player1 + "_VS_" + player2;
		Utils.writeInTextFile("-------------------------------------------------------------------", textFileName);
	}

	/**
	 * No parametters mean a new will be started an separated in the text file
	 */
	public void updateTextFile() {
		Utils.writeInTextFile("-------------------------------------------------------------------", textFileName);
	}

	public void setUpGame() {
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				if (row % 2 == col % 2) {
					if (row < 4)
						board[row][col] = BLACK_KING;
					else if (row > 5)
						board[row][col] = RED_KING;
					else
						board[row][col] = EMPTY;
				} else {
					board[row][col] = EMPTY;
				}
			}
		}
	}

	public int pieceAt(int row, int col) {
		return board[row][col];
	}

	public void makeMove(CheckersMove move) {
		makeMove(move.getFromRow(), move.getFromCol(), move.getToRow(), move.getToCol());
	}

	void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
		board[toRow][toCol] = board[fromRow][fromCol];
		board[fromRow][fromCol] = EMPTY;
		if (fromRow - toRow != 1 && fromRow - toRow != -1) {
			if (board[toRow][toCol] == BLACK_KING || board[toRow][toCol] == RED_KING) {
				int jumpRow = (fromRow > toRow) ? toRow + 1 : toRow - 1;
				int jumpCol = (fromCol > toCol) ? toCol + 1  : toCol - 1;
				
				board[jumpRow][jumpCol] = EMPTY;			
			} else {
				// The move is a jump. Remove the jumped piece from the board.
				int jumpRow = (fromRow + toRow) / 2; // Row of the jumped piece.
				int jumpCol = (fromCol + toCol) / 2; // Column of the jumped piece.
				System.out.println("Pion mangé à : {" + jumpRow + ";" + jumpCol + "}");
				board[jumpRow][jumpCol] = EMPTY;
			}

		}
		if (toRow == 0 && board[toRow][toCol] == RED)
			board[toRow][toCol] = RED_KING;
		if (toRow == 9 && board[toRow][toCol] == BLACK)
			board[toRow][toCol] = BLACK_KING;

		Utils.writeInTextFile(board, textFileName);
	}

	public CheckersMove[] getLegalMoves(int player) {

		if (player != RED && player != BLACK)
			return null;

		int playerKing;
		if (player == RED)
			playerKing = RED_KING;
		else
			playerKing = BLACK_KING;

		ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>();

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				if (board[row][col] == player) {
					if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2))
						moves.add(new CheckersMove(row, col, row + 2, col + 2));
					if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
						moves.add(new CheckersMove(row, col, row - 2, col + 2));
					if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
						moves.add(new CheckersMove(row, col, row + 2, col - 2));
					if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
						moves.add(new CheckersMove(row, col, row - 2, col - 2));
				} else if (board[row][col] == playerKing) {
					for (int i = 0; i < board.length; i++) {
						if (canJump(player, row, col, row + i, col + i, row + i + 1, col + i + 1))
							moves.add(new CheckersMove(row, col, row + i + 1, col + i + 1));
						if (canJump(player, row, col, row - i, col + i, row - i - 1, col + i + 1))
							moves.add(new CheckersMove(row, col, row - i - 1, col + i + 1));
						if (canJump(player, row, col, row + i, col - i, row + i + 1, col - i - 1))
							moves.add(new CheckersMove(row, col, row + i + 1, col - i - 1));
						if (canJump(player, row, col, row - i, col - i, row - i - 1, col - i - 1))
							moves.add(new CheckersMove(row, col, row - i - 1, col - i - 1));
					}
				}
			}
		}

		if (moves.size() == 0) {
			for (int row = 0; row < 10; row++) {
				for (int col = 0; col < 10; col++) {
					if (board[row][col] == player) {
						if (canMove(player, row, col, row + 1, col + 1))
							moves.add(new CheckersMove(row, col, row + 1, col + 1));
						if (canMove(player, row, col, row - 1, col + 1))
							moves.add(new CheckersMove(row, col, row - 1, col + 1));
						if (canMove(player, row, col, row + 1, col - 1))
							moves.add(new CheckersMove(row, col, row + 1, col - 1));
						if (canMove(player, row, col, row - 1, col - 1))
							moves.add(new CheckersMove(row, col, row - 1, col - 1));
					} else if (board[row][col] == playerKing) {
						for (int i = 0; i < board.length; i++) {
							if (canMove(player, row, col, row + i, col + i))
								moves.add(new CheckersMove(row, col, row + i, col + i));
							if (canMove(player, row, col, row - i, col + i))
								moves.add(new CheckersMove(row, col, row - i, col + i));
							if (canMove(player, row, col, row + i, col - i))
								moves.add(new CheckersMove(row, col, row + i, col - i));
							if (canMove(player, row, col, row - i, col - i))
								moves.add(new CheckersMove(row, col, row - i, col - i));
						}
					}
				}
			}
		}

		if (moves.size() == 0)
			return null;
		else {
			CheckersMove[] moveArray = new CheckersMove[moves.size()];
			for (int i = 0; i < moves.size(); i++)
				moveArray[i] = moves.get(i);
			return moveArray;
		}

	}

	public CheckersMove[] getLegalJumpsFrom(int player, int row, int col) {
		if (player != RED && player != BLACK)
			return null;
		int playerKing; // The constant representing a King belonging to player.
		if (player == RED)
			playerKing = RED_KING;
		else
			playerKing = BLACK_KING;
		ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>();																	// list.
		if (board[row][col] == player) {
			if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2))
				moves.add(new CheckersMove(row, col, row + 2, col + 2));
			if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
				moves.add(new CheckersMove(row, col, row - 2, col + 2));
			if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
				moves.add(new CheckersMove(row, col, row + 2, col - 2));
			if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
				moves.add(new CheckersMove(row, col, row - 2, col - 2));
		} else if (board[row][col] == playerKing) {
			for (int i = 0; i < board.length; i++) {
				if (canJump(player, row, col, row + i, col + i, row + i + 1, col + i + 1))
					moves.add(new CheckersMove(row, col, row + i + 1, col + i + 1));
				if (canJump(player, row, col, row - i, col + i, row - i - 1, col + i + 1))
					moves.add(new CheckersMove(row, col, row - i - 1, col + i + 1));
				if (canJump(player, row, col, row + i, col - i, row + i + 1, col - i - 1))
					moves.add(new CheckersMove(row, col, row + i + 1, col - i - 1));
				if (canJump(player, row, col, row - i, col - i, row - i - 1, col - i - 1))
					moves.add(new CheckersMove(row, col, row - i - 1, col - i - 1));
			}
		}

		if (moves.size() == 0)
			return null;
		else {
			CheckersMove[] moveArray = new CheckersMove[moves.size()];
			for (int i = 0; i < moves.size(); i++)
				moveArray[i] = moves.get(i);
			return moveArray;
		}
	}

	private boolean canJump(int player, int r1, int c1, int r2, int c2, int r3, int c3) {

		if (r3 < 0 || r3 >= 10 || c3 < 0 || c3 >= 10)
			return false; // (r3,c3) is off the board.

		if (board[r3][c3] != EMPTY)
			return false; // (r3,c3) already contains a piece.

		if (player == RED) {
			if (board[r2][c2] != BLACK && board[r2][c2] != BLACK_KING)
				return false; // There is no black piece to jump.
			return true; // The jump is legal.
		} else {
			if (board[r2][c2] != RED && board[r2][c2] != RED_KING)
				return false; // There is no red piece to jump.
			return true; // The jump is legal.
		}

	} // end canJump()

	private boolean canMove(int player, int r1, int c1, int r2, int c2) {

		if (r2 < 0 || r2 >= 10 || c2 < 0 || c2 >= 10)
			return false; // (r2,c2) is off the board.

		if (board[r2][c2] != EMPTY)
			return false; // (r2,c2) already contains a piece.

		if (player == RED) {
			if (board[r1][c1] == RED && r2 > r1)
				return false; // Regular red piece can only move down.
			return true; // The move is legal.
		} else {
			if (board[r1][c1] == BLACK && r2 < r1)
				return false; // Regular black piece can only move up.
			return true; // The move is legal.
		}

	}

}
