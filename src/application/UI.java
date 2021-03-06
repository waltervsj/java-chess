package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String enteredString = sc.nextLine();
			char column = enteredString.charAt(0);
			int row = Integer.parseInt(enteredString.substring(1));
			
			return new ChessPosition(column, row);			
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error to read ChessPosition: Valid values are from a1 to h8");
		}
	}
	
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece, boolean background) {
    	if (piece == null) {
            System.out.print(background ? "." : "-");
        }
        else {
        	System.out.print(background ? "." : "");
            if (piece.getColor() == Color.WHITE) {
               // System.out.print(ANSI_WHITE + piece + ANSI_RESET);
                System.out.print(piece);
            }
            else {
            	System.out.print(piece.toString().toLowerCase());
               // System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> listCapturedPieces) {
		printBoard(chessMatch.getPieces());
		printCapturedPieces(listCapturedPieces);
		System.out.println();
		System.out.println("Turn: " + chessMatch.getTurn());
		System.out.println("Waiting player: " + chessMatch.getCurrentPLayer());
		if (chessMatch.getCheck())
			System.out.println("CHECK!");
	}
	
	public static void clearScreen() {
		//System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	private static void printCapturedPieces(List<ChessPiece> listCaptured) {
			Predicate<ChessPiece> whiteFilter = piece -> piece.getColor() == Color.WHITE;
			Predicate<ChessPiece> blackFilter = piece -> piece.getColor() == Color.BLACK;
			List<ChessPiece> listWhite = listCaptured.stream().filter(whiteFilter).collect(Collectors.toList());
			List<ChessPiece> listBlack = listCaptured.stream().filter(blackFilter).collect(Collectors.toList());
			System.out.println("Captured pieces:");
			System.out.print("White: ");
			System.out.println(Arrays.toString(listWhite.toArray()));
			System.out.print("Black: ");
			System.out.println(Arrays.toString(listBlack.toArray()));
	}
}