package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	private Color color;
	
	public Color getColor() {
		return color;
	}

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		return false;
	}
	
	protected void increaseMoveCount() {
		
	}
	
	protected void decreaseMoveCount() {
		
	}
	
}
