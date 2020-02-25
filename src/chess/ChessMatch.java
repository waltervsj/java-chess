package chess;

import boardgame.Board;

public class ChessMatch {
	private Board board;

	public ChessMatch() {
		this.board = new Board(8, 8);
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[this.board.getRows()][this.board.getColumns()];
		
		for(int row = 0; row < this.board.getRows(); row ++) {
			for (int column = 0; column < this.board.getColumns(); column ++) {
				mat[row][column] = (ChessPiece) board.piece(row, column);
			}
		}
		
		return mat;
	}
	
}
