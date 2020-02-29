package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] possibleMovesMatrix = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		
		Position scanPos = new Position(0, 0);
		
		scanPos.setValues(this.position.getRow() - 1, this.position.getColumn());
		
		// Above
		while(this.getBoard().positionExists(scanPos) && !this.getBoard().thereIsAPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
			scanPos.setRow(scanPos.getRow() - 1);
		}
		if (this.getBoard().positionExists(scanPos) && this.isThereOpponentPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
		}
		
		// Left
		while(this.getBoard().positionExists(scanPos) && !this.getBoard().thereIsAPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
			scanPos.setColumn(scanPos.getColumn() - 1);
		}
		if (this.getBoard().positionExists(scanPos) && this.isThereOpponentPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
		}
		
		// Right
		while(this.getBoard().positionExists(scanPos) && !this.getBoard().thereIsAPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
			scanPos.setColumn(scanPos.getColumn() + 1);
		}
		if (this.getBoard().positionExists(scanPos) && this.isThereOpponentPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
		}
		
		// Below
		while(this.getBoard().positionExists(scanPos) && !this.getBoard().thereIsAPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
			scanPos.setRow(scanPos.getColumn() + 1);
		}
		if (this.getBoard().positionExists(scanPos) && this.isThereOpponentPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
		}
		
		return possibleMovesMatrix;
	}
	
}
