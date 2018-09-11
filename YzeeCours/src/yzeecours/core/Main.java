package core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main 
{	
	public static void main(String[] args)
	{
		// Welcome to the Yahtzee GITHUB project
		//TestCombos(10000);
		YahtzeeGame game = new YahtzeeGame();
		game.ShowMenu();
	}
	
	/**Function used for batch combination testing*/
	private static void TestCombos(int nbOfTests)
	{
		Player p = new Player("Test Player");
		PrintStream printStream;
		try {
			printStream = new PrintStream(new FileOutputStream("Testing.txt"));
			System.setOut(printStream);
		} catch (FileNotFoundException e) {
			System.out.println("Error redirecting output to textFile");
		}
		for(int i = 0; i < nbOfTests; i++)
		{
			System.out.println("\n\n///////    Test N°" + i + "////////\n");
			p.AskToRollDicesSkip();
			p.ShowAvailableCombos();
		}
	}
	 
}
