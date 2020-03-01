package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matPossibleMoves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];

		Position scanPos = new Position(0, 0);

		// North
		scanPos.setValues(this.position.getRow() - 1, this.position.getColumn());
		if (getBoard().positionExists(scanPos) && canMove(scanPos))
		matPossibleMoves[scanPos.getRow()][scanPos.getColumn()] = true;

		// NorthEast
		scanPos.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);
		if (getBoard().positionExists(scanPos) && canMove(scanPos))
			matPossibleMoves[scanPos.getRow()][scanPos.getColumn()] = true;

		// East
		scanPos.setValues(this.position.getRow(), this.position.getColumn() + 1);
		if (getBoard().positionExists(scanPos) && canMove(scanPos))
			matPossibleMoves[scanPos.getRow()][scanPos.getColumn()] = true;

		// SouthEast
		scanPos.setValues(this.position.getRow() + 1, this.position.getColumn() - 1);
		if (getBoard().positionExists(scanPos) && canMove(scanPos))
			matPossibleMoves[scanPos.getRow()][scanPos.getColumn()] = true;

		// South
		scanPos.setValues(this.position.getRow() + 1, this.position.getColumn());
		if (getBoard().positionExists(scanPos) && canMove(scanPos))
			matPossibleMoves[scanPos.getRow()][scanPos.getColumn()] = true;

		// SouthWest
		scanPos.setValues(this.position.getRow() + 1, this.position.getColumn() + 1);
		if (getBoard().positionExists(scanPos) && canMove(scanPos))
			matPossibleMoves[scanPos.getRow()][scanPos.getColumn()] = true;

		// West
		scanPos.setValues(this.position.getRow(), this.position.getColumn() - 1);
		if (getBoard().positionExists(scanPos) && canMove(scanPos))
			matPossibleMoves[scanPos.getRow()][scanPos.getColumn()] = true;

		// NorthWest
		scanPos.setValues(this.position.getRow() - 1, this.position.getColumn() - 1);
		if (getBoard().positionExists(scanPos) && canMove(scanPos))
			matPossibleMoves[scanPos.getRow()][scanPos.getColumn()] = true;

		return matPossibleMoves;
	}

	private boolean canMove(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().piece(position);
		return piece == null || (piece != null && piece.getColor() != getColor());
	}
}
