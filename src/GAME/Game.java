package GAME;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import BOARD.Board;
import LOCATION.File;
import LOCATION.Location;
import PIECE.Piece;
import PIECE.PieceColor;
import SQUARE.Square;

public class Game 
{
	static Board board = new Board();
	static Map<Location, Square> squareMap = board.getLocationSquareMap();
	
	
	@SuppressWarnings("resource")
 	public static void basicMode() throws IOException
	{
		Player player1 = new Player(0);
		Player player2 = new Player(1);
	    int currentTurn = player1.getId();
		boolean gameOver = false;

		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(!gameOver)
		{
			board.print_board();
			System.out.println("-------------------------");
			System.out.println("1. Show killed pieces.");
			System.out.println("2. Surrender/ Exit.");
			System.out.println("3. Show history moves (valid moves only). )");
			System.out.println("Or continue playing by enter valid move (e.g: e2-e4)");
			Piece killedPiece = null;
			boolean isOccup = false;
			String scanner = sc.nextLine();
			if(scanner.length() == 1)
			{
				char scan = scanner.charAt(0);
				if(scan == '1')
				{
					System.out.println("Player 1: ");
                    print_list_of_pieces(player1.getListKilledPieces());
                    System.out.println();
                    System.out.println("Player 2: ");
                    print_list_of_pieces(player2.getListKilledPieces());
                    System.out.println();
                    br.readLine();
                    continue;
				}
				else if(scan=='2')
				{
					gameOver = true;
					continue;
				}
				else if(scan == '3')
				{
					System.out.println("Player 1:");
					print_list(player1.getHistoryMoves()); 
					System.out.println();
                    System.out.println("Player 2: ");
                    print_list(player2.getHistoryMoves());
					br.readLine();
					continue;
				}
			}
			
			//Determine how the piece moves.
            //Start from
			String[] fromTo = scanner.split("-");
			
			File fromFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[0].charAt(0))));
			int fromRank = Integer.parseInt(String.valueOf(Character.toUpperCase(fromTo[0].charAt(1))));
			File toFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[1].charAt(0))));
			int toRank = Integer.parseInt(String.valueOf(Character.toUpperCase(fromTo[1].charAt(1))));
			
			Square fromSq = new Square();
			Location from = new Location(fromFile, fromRank);
			Square toSq = new Square();
			Location to = new Location(toFile, toRank);
			
			if(squareMap.containsKey(from))
				fromSq = squareMap.get(from);
			if(squareMap.containsKey(to))
				toSq = squareMap.get(to);
			
			if(fromSq == null || toSq == null || !fromSq.getIsOccupied())
			{
				System.out.println("Invalid move!!! Enter to continue.");
				br.readLine();
				continue;
			}
			
			if (fromSq.getCurrentPiece().getPieceColor().ordinal() != currentTurn)
            {
				System.out.println("Ivalid move!1!");
				System.out.println("The turn is now " + ((currentTurn == 0) ? "Player 1. " : "Player 2. ") + "Enter to continue.");
				br.readLine();
				continue;
            }
			
			Piece piece = fromSq.getCurrentPiece();
			List<Location> validMoves = piece.getValidMoves(board);
			
			if(!validMoves.contains(toSq.getLocation()))
			{
				System.out.println("Invalid move!!! Enter to continue.");
				br.readLine();
				continue;
			}
			
			if(toSq.getIsOccupied())
			{
				killedPiece = toSq.getCurrentPiece();
				isOccup = true;
			}

				
			fromSq.getCurrentPiece().makeMove(toSq);
			fromSq.reset();
			
			Piece currentKing = (currentTurn == 0) ? board.getLightKing():board.getDarkKing();
			if(currentKing.isInDanger(board))
			{
				System.out.println("The king is checkmated!!! Change your moves or Press enter than press 2 to surrender");
				br.readLine();
                toSq.getCurrentPiece().makeMove(fromSq);
                toSq.reset();
                if(isOccup)
                {
                	toSq.setIsOccupied(isOccup);
                	toSq.setCurrentPiece(killedPiece);
                }
                continue;
			}
			
			if(isOccup)
			{
				if(currentTurn == 0)
					player1.addKilledPiece(killedPiece);
				else
					player2.addKilledPiece(killedPiece);
			}
			
			if(toSq.getIsOccupied() && toSq.getCurrentPiece().nameEqual(currentKing))
			{
				if (toSq.getCurrentPiece().getPieceColor().equals(PieceColor.Light))
                    board.setLightKing(toSq.getCurrentPiece());
                else
                	board.setLightKing(toSq.getCurrentPiece());
			}
			
			if(currentTurn == 0)
				player1.addHistory(toSq.getCurrentPiece().getName() +" " + toSq.getCurrentPiece().getPieceColor() + " " + fromTo[0] + "->" + fromTo[1]);
			else
				player2.addHistory(toSq.getCurrentPiece().getName() +" " + toSq.getCurrentPiece().getPieceColor() + " " + fromTo[0] + "->" + fromTo[1]);
			
			//switch turn
            currentTurn = (currentTurn==0)?1:0;
		}
		
		System.out.println( (currentTurn == 0) ? "Player 2 won" : "Player 1 won" );
		br.readLine();
		return;
	}
	
	@SuppressWarnings("resource")
	public static void quickMode() throws IOException
	{
		Player player1 = new Player(0,0);
		Player player2 = new Player(1,0);
		int currentTurn = 0;
		boolean gameOver = false;
		int remainingTurn = 100;
		
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(!gameOver)
		{
			board.print_board();
			System.out.println("-------------------------");
			System.out.println("1. Show killed pieces.");
			System.out.println("2. Surrender/ Exit.");
			System.out.println("3. Show history moves (valid moves only). )");
			System.out.println("Or continue playing by enter valid move (e.g: e2-e4)");
			System.out.println("Remaining turns: " + remainingTurn);
			Piece killedPiece = null;
			boolean isOccup = false;
			String scanner = sc.nextLine();
			if(scanner.length() == 1)
			{
				char scan = scanner.charAt(0);
				if(scan == '1')
				{
					System.out.println("Player 1: ");
                    print_list_of_pieces(player1.getListKilledPieces());
                    System.out.println();
                    System.out.println("Player 2: ");
                    print_list_of_pieces(player2.getListKilledPieces());
                    System.out.println();
                    br.readLine();
                    continue;
				}
				else if(scan=='2')
				{
					gameOver = true;
					continue;
				}
				else if(scan == '3')
				{
					System.out.println("Player 1: ");
                    print_list(player1.getHistoryMoves());
                    System.out.println();
                    System.out.println("Player 2: ");
                    print_list(player2.getHistoryMoves());
                    System.out.println();
					br.readLine();
					continue;
				}
			}
			
			//Determine how the piece moves.
            //Start from
			String[] fromTo = scanner.split("-");
			
			File fromFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[0].charAt(0))));
			int fromRank = Integer.parseInt(String.valueOf(Character.toUpperCase(fromTo[0].charAt(1))));
			File toFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[1].charAt(0))));
			int toRank = Integer.parseInt(String.valueOf(Character.toUpperCase(fromTo[1].charAt(1))));
			
			Square fromSq = new Square();
			Location from = new Location(fromFile, fromRank);
			Square toSq = new Square();
			Location to = new Location(toFile, toRank);
			
			if(squareMap.containsKey(from))
				fromSq = squareMap.get(from);
			if(squareMap.containsKey(to))
				toSq = squareMap.get(to);
			
			if(fromSq == null || toSq == null || !fromSq.getIsOccupied())
			{
				System.out.println("Invalid move!!! Enter to continue.");
				br.readLine();
				continue;
			}
			
			if (fromSq.getCurrentPiece().getPieceColor().ordinal() != currentTurn)
            {
				System.out.println("Invalid move!1!");
				System.out.println("The turn is now " + ((currentTurn == 0) ? "Player 1. " : "Player 2. ") + "Enter to continue.");
				br.readLine();
				continue;
            }
			
			Piece piece = fromSq.getCurrentPiece();
			List<Location> validMoves = piece.getValidMoves(board);
			
			if(!validMoves.contains(toSq.getLocation()))
			{
				System.out.println("Invalid move!!! Enter to continue.");
				br.readLine();
				continue;
			}
			
			if(toSq.getIsOccupied())
			{
				killedPiece = toSq.getCurrentPiece();
				isOccup = true;
			}

				
			fromSq.getCurrentPiece().makeMove(toSq);
			fromSq.reset();
			
			Piece currentKing = (currentTurn == 0) ? board.getLightKing():board.getDarkKing();
			
			if(isOccup)
			{
				if(currentTurn == 0)
				{
					player1.addKilledPiece(killedPiece);
					player1.setPlayPoint(player1.getPlayPoint()+killedPiece.getValuePoint());
				}
				else
				{
					player2.addKilledPiece(killedPiece);
					player2.setPlayPoint(player2.getPlayPoint()+killedPiece.getValuePoint());
				}
				if(killedPiece.nameEqual(currentKing))
                {
                    gameOver = true;
                    System.out.println("The king is captured. Both player's point is calculating... Press continue to see the result");
                    br.readLine();
                    continue;
                }
			}
			
			if(toSq.getIsOccupied() && toSq.getCurrentPiece().nameEqual(currentKing))
			{
				if (toSq.getCurrentPiece().getPieceColor().equals(PieceColor.Light))
                    board.setLightKing(toSq.getCurrentPiece());
                else
                	 board.setLightKing(toSq.getCurrentPiece());
			}
			
			if(currentTurn == 0)
				player1.addHistory(toSq.getCurrentPiece().getName() +" " + toSq.getCurrentPiece().getPieceColor() + " " + fromTo[0] + "->" + fromTo[1]);
			else
				player2.addHistory(toSq.getCurrentPiece().getName() +" " + toSq.getCurrentPiece().getPieceColor() + " " + fromTo[0] + "->" + fromTo[1]);
			
			remainingTurn--;
			if(remainingTurn == 0)
				gameOver = true;
			//switch turn
            currentTurn = (currentTurn==0)?1:0;
		}
		
		System.out.println("Player 1: " + player1.getPlayPoint());
		System.out.println("Player 2: " + player2.getPlayPoint());
		if(player2.getPlayPoint() != player1.getPlayPoint())
			System.out.println( (player2.getPlayPoint()>player1.getPlayPoint() ) ? "Player 2 won" : "Player 1 won" );
		else
			System.out.println("Draw");
		br.readLine();
		return;
	}
	
	private static void print_list_of_pieces(List<Piece> listPiece)
    {

        for (Piece piece : listPiece)
            print_piece(piece);

    }
	
	private static void print_list(List<String> list)
	{
		for(String data:list)
		{
			System.out.println(data);
		}
	}

    private static void print_piece(Piece piece)
    {
        System.out.println(piece.getName() + " " + piece.getPieceColor());
    }
}
