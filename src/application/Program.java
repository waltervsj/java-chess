package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import exceptions.ChessException;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> listCaptured = new ArrayList<>();
		
		while(true) {
			try {
				//UI.clearScreen();
				UI.printMatch(chessMatch, listCaptured);
				System.out.println();
				System.out.println("Source: ");
				ChessPosition sourcePosition = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(sourcePosition);
				//UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.println("Target: ");
				ChessPosition targetPosition = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(sourcePosition, targetPosition);
				if (capturedPiece != null) 
					listCaptured.add(capturedPiece);
				
			} catch(ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}

}
