package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
import exceptions.ChessException;

public class ChessMatch {
	private Board board;
	private int turn;
	private Color currentPLayer;
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	private boolean check;
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPLayer() {
		return currentPLayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public ChessMatch() {
		this.board = new Board(8, 8);
		this.turn = 1;
		this.currentPLayer = Color.WHITE;
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
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		this.validatePosition(position);
		return this.board.piece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		this.validatePosition(source);
		this.validateTargetPosition(source, target);
		
		Piece capturedPiece = this.makeMove(source, target);
		
		if(testCheck(currentPLayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourselft in check");
		}
		
		this.check = testCheck(opponent(currentPLayer));
		
		nextTurn();

		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece piece = board.removePiece(source);
		Piece capturedPiece = this.board.removePiece(target);
		this.board.placePiece(piece, target);	
		if(capturedPiece != null) {
			this.piecesOnTheBoard.remove(capturedPiece);
			this.capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece piece = board.removePiece(target);
		board.placePiece(piece, source);
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			this.capturedPieces.remove(capturedPiece);
			this.piecesOnTheBoard.add(capturedPiece);
		}
	}

	private void validatePosition(Position position) {
		if (!this.board.thereIsAPiece(position))
			throw new ChessException("There is no piece on this position");

		if (this.currentPLayer != ((ChessPiece)board.piece(position)).getColor())
			throw new ChessException("The chosen piece is not yours");
		
		if (!this.board.piece(position).isThereAnyPossibleMove())
			throw new ChessException("There is no possible movies for the chosen piece");
		
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if (!this.board.piece(source).possibleMove(target))
			throw new ChessException("The chosen piece can't move to target position");
	}

	private void nextTurn() {
		this.turn ++;
		this.currentPLayer = opponent(this.currentPLayer);
	}
	
	private Color opponent(Color color) {
		return color == Color.WHITE ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		List<Piece> listPiece = this.piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece piece : listPiece) {
			if (piece instanceof King)
				return (ChessPiece)piece;
		}
		
		throw new IllegalStateException("There is no " + color + " king on the board");
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> listOpponentPieces = this.piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece piece : listOpponentPieces) {
			boolean[][] matrix = piece.possibleMoves();
			if (matrix[kingPosition.getRow()][kingPosition.getColumn()])
				return true;
		}
		return false;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
		this.piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
	
}
