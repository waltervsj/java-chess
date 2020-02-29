package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import exceptions.ChessException;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			try {
				//UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());
				System.out.println();
				System.out.println("Source: ");
				ChessPosition sourcePosition = UI.readChessPosition(sc);
				
				System.out.println();
				System.out.println("Target: ");
				ChessPosition targetPosition = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(sourcePosition, targetPosition);				
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
