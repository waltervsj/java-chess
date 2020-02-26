package chess;

import boardgame.Position;
import exceptions.ChessException;

public class ChessPosition {
	private char column;
	private int row;
	
	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8)
			throw new ChessException("Error to instanciate ChessPosition: Valid values are a1 to h8");
		
		this.column = column;
		this.row = row;
	}
	
	protected Position toPosition() {
		return new Position(8 - this.row, this.column - 'a');
	}
	
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)(position.getColumn() - 'a'), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + this.column + this.row;
	}
}
