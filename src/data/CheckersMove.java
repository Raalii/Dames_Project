package data;

public class CheckersMove {
    int fromRow;  // Position of piece to be moved.
	 int fromCol;
    private int toRow;      // Square it is to move to.
	private int toCol;
    public CheckersMove(int r1, int c1, int r2, int c2) {
            // Constructor.  Just set the values of the instance variables.
        setFromRow(r1);
        setFromCol(c1);
        setToRow(r2);
        setToCol(c2);
    }   
    public boolean isJump() {
            // Test whether this move is a jump.  It is assumed that
            // the move is legal.  In a jump, the piece moves two
            // rows.  (In a regular move, it only moves one row.)
        return (getFromRow() - getToRow() ==  2 || getFromRow() - getToRow() == -2);
    }
	public int getFromCol() {
		return fromCol;
	}
	public void setFromCol(int fromCol) {
		this.fromCol = fromCol;
	}
	public int getToCol() {
		return toCol;
	}
	public void setToCol(int toCol) {
		this.toCol = toCol;
	}
	public int getFromRow() {
		return fromRow;
	}
	public void setFromRow(int fromRow) {
		this.fromRow = fromRow;
	}
	public int getToRow() {
		return toRow;
	}
	public void setToRow(int toRow) {
		this.toRow = toRow;
	}
}  // end class CheckersMove.