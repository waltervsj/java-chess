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
		boolean[][] possibleMovesMatrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position scanPos = new Position(0, 0);
				
		// Above
		scanPos.setValues(position.getRow() - 1, position.getColumn());
		while(getBoard().positionExists(scanPos) && !getBoard().thereIsAPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
			scanPos.setRow(scanPos.getRow() - 1);
		}
		if (getBoard().positionExists(scanPos) && isThereOpponentPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
		}
		
		// Left
		scanPos.setValues(position.getRow(), position.getColumn() - 1);
		while(getBoard().positionExists(scanPos) && !getBoard().thereIsAPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
			scanPos.setColumn(scanPos.getColumn() - 1);
		}
		if (getBoard().positionExists(scanPos) && isThereOpponentPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
		}
		
		// Right
		scanPos.setValues(position.getRow(), position.getColumn() + 1);
		while(getBoard().positionExists(scanPos) && !getBoard().thereIsAPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
			scanPos.setColumn(scanPos.getColumn() + 1);
		}
		if (getBoard().positionExists(scanPos) && isThereOpponentPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
		}

		// Down
		scanPos.setValues(position.getRow() + 1, position.getColumn());		
		while(getBoard().positionExists(scanPos) && !getBoard().thereIsAPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
			scanPos.setRow(scanPos.getRow() + 1);
		}
		if (getBoard().positionExists(scanPos) && isThereOpponentPiece(scanPos)) {
			possibleMovesMatrix[scanPos.getRow()][scanPos.getColumn()] = true;
		}
		
		return possibleMovesMatrix;
	}
	
}
