package GAME;

import java.io.IOException;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) throws IOException
	{
		Integer action;
        do
        {    
        	//Console.Clear();
            System.out.println("1. Play new game");
            System.out.println("2. Exit");
            System.out.println("-------------------------");
            System.out.print("Choose action: ");
            @SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
            action = Integer.parseInt(sc.nextLine());
            if (action==1)
                gameMenu();
        } while (action!=2);
	}
	
	private static void gameMenu() throws IOException
	{
		 Integer action;
		 do
		 {
			 //Console.Clear();
			 System.out.println("1. Basic mode");
	         System.out.println("2. Quick mode");
	         System.out.println("3. Exit");
	         System.out.print("Choose game mode: ");
	         @SuppressWarnings("resource")
	         Scanner sc = new Scanner(System.in);
	         action = Integer.parseInt(sc.nextLine());
	         if(action == 1)
	        	 Rule.basicModeRule();
	         else if(action==2)
	        	 Rule.quickModeRule();
		 }while (action!=3);
         
	}
}
