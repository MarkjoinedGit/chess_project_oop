package GAME;

import java.io.IOException;
import java.util.Scanner;

public class Rule 
{
	public static void basicModeRule() throws IOException
	{
		System.out.println("-Basic mode: ");
		System.out.println("+The game is over when one of players choose to surrender/exit.");
		System.out.println("+You cannot move your piece if your king is check mated.");
		System.out.println("+You cannot move a piece that is dangerous for the king.");
		System.out.println("+You cannot make some speacial moves: ");
		System.out.println("	+You cannot castling between King and Rook.");
		System.out.println("	+You cannot make an en passant when opponent's pawn passed the line.");
		System.out.println("Continue?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Integer action = Integer.parseInt(sc.nextLine());
        if(action == 1)
       	 	Game.basicMode();
        else
       	 	return;
	}
	
	public static void quickModeRule() throws IOException
	{
		System.out.println("-Quick mode:");
		System.out.println("+There are totally 100 turns for both player.");
		System.out.println("+After 100 turns, the game is over, player having more points will win.");
		System.out.println("+Point for each kind of piece:");
		System.out.println("	+Pawn: 1 point.");
		System.out.println("	+Rook: 2 points.");
		System.out.println("	+Bishop: 2 points.");
		System.out.println("	+Knight: 2 points.");
		System.out.println("	+Queen: 3 points");
		System.out.println("	+King: 5 points");
		System.out.println("+This mode allow both players can capture opponent's king. When any king is captured, the game is ended and player having more points will win.");
		System.out.println("+You cannot make somwe special moves like castling or en passant");
		System.out.println("Continue?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Integer action = Integer.parseInt(sc.nextLine());
        if(action == 1)
       	 	Game.quickMode();
        else
       	 	return;
	}
}
