package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private Board board;

	public ChessMatch() {
		this.board = new Board(8, 8);
		this.initialSetup();
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
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void initialSetup() {
		this.placeNewPiece('b', 6, new Rook(this.board, Color.WHITE));
		this.placeNewPiece('e', 8, new King(this.board, Color.BLACK));
		this.placeNewPiece('e', 1, new King(this.board, Color.WHITE));
	}
	
}
