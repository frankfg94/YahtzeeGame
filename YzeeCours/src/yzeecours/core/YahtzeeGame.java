package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Scanner;
import graphics.Drawings;




public class YahtzeeGame 
{
	private int turnID = 0;
	public static Dice[] dices = new Dice[5]; // there are 5 dices for each YatzheeGame, this is a constant number of dices so we put the 'final' statement
	Player currentPlayer;
	Player[] playerArray;
	 
	final private static int maxTurnNumber = 13;
	final public static int UpperSectionBonusThreshold = 63;
	final public static int reRollCount = 2;
	final private static int UpperSectionBonusScore = 35;
	private int playerCount;
	private boolean isMultiplayer = false;
	
	public YahtzeeGame()
	{
		for(int i = 0 ; i < dices.length ; i++)
			dices[i] = new Dice();
	}
	
	String menuChoice;
	public void ShowMenu()
	{
		Drawings.ShowMainMenuLogo();
		System.out.println("        Choose an option:\n");
		System.out.println("           1. Single Player");
		System.out.println("           2. Multiplayer");
		System.out.println("           3. Quit");
		Scanner sc = new Scanner(System.in);
		menuChoice = sc.nextLine();
		QueryMenu(menuChoice, sc);
		sc.close();
	}
	
	Scanner s1;
	
	private void SafeUserCountInput()
	{
		System.out.println("\n\nHow many persons want to play this game?");
		System.out.print("Count : ");
		 s1 = new Scanner(System.in);
		try
		{
			playerCount = s1.nextInt();
		}
		catch (Exception e)
		{
			System.out.println("Please enter a valid number");
			StartMultiplayer();
		}
	}
	
	/**Generate players based on the playerCount variable*/
	private void CreatePlayers()
	{
		playerArray = new Player[playerCount];
	    String name = "Unnamed Player";
		for(int i = 0; i < playerCount; i++)
		{
			System.out.print("Name of player " + (i+1) + "/" + playerCount + " : ");
			s = new Scanner(System.in);
			name = s.nextLine();
			if (name.length() == 0) name = Integer.toString(i+1);
			playerArray[i] = new Player(name);
		}
		System.out.print(" - Done - ");
	}
	
	/** Multiplayer set up*/
	public void StartMultiplayer() 
	{
		isMultiplayer = true;
		
		SafeUserCountInput();
		
		CreatePlayers();
		
		Start();
	}
		
	/** Select the menu choice for the chosen number*/
	public void QueryMenu(String choice, Scanner sc) //
	{
			System.out.println("Choice : " + choice);
		switch(choice)
		{
		case "1": Start();
		break;
		case "2": StartMultiplayer();
		break;
		case "3":
		Quit();
		break;
		default: System.out.println("Invalid command, choose an another command");
		menuChoice = sc.nextLine();
		QueryMenu(menuChoice, sc);
		break;
		}
	}
	
	/**get score from all player and save it in a file*/
	private void SaveScoreToFile(String txtFilePath)
	{
		LocalDateTime time = LocalDateTime.now();
		String msg = System.lineSeparator() + System.lineSeparator()+ System.lineSeparator() +"Game done on the " + time.getDayOfMonth() + "/"+time.getMonthValue() + "/"+time.getYear() + " | " + time.getHour() +":"+ time.getMinute()+":" + time.getSecond() +" | "+ playerCount +" players" + "\r\n"; 
		int i = 0;
		msg+="-------------------- Leaderboard --------------------------"+ System.lineSeparator();
		for(Player player : GetPlayersByScore())
		{
			
			if(isMultiplayer)
				msg+="/ "+(i+1) +".  "+ player.name ;
			else if(player.name.isEmpty())
				msg+="/ " + "Player";
			else 
				msg+= "/ " + player.name;
			
			int tabCount = GetSpacing(player.name);
			for(int j = 0 ; j < tabCount;j++)
			{
				msg+="\t";
			}
			msg+=(" | Score is " + player.score + " | upper : " + player.upperSectionScore +" lower : " + (player.score - player.upperSectionScore) +" \t/ \r\n" ) ;
			msg+="-----------------------------------------------------------"+System.lineSeparator() ;
			i++;
		}
		
		String saveDoneMsg = "Save Failed";
		File f = new File(txtFilePath);
		if(f.exists() && !f.isDirectory())	// If the file exist, just append the new scores
		{
			 saveDoneMsg = System.lineSeparator()+ "Scores saved successfully on the path : " + Paths.get(txtFilePath).toAbsolutePath();
			try 
			{
			    Files.write(Paths.get(txtFilePath), msg.getBytes(), StandardOpenOption.APPEND);
				System.out.println(saveDoneMsg);
			}catch (IOException e) 
			{
				System.out.println("Error saving game scores at path : " + txtFilePath + "\n" + e);
			}
		}
		else 	// But if the file doesn't exist, create and write the scores
		{
			try (PrintWriter out = new PrintWriter(txtFilePath)) 
			{
			    out.println(msg);
				System.out.println(saveDoneMsg);
			} catch (FileNotFoundException e)
			{
				System.out.println("Error creating txt file and saving scores at path : " + txtFilePath + "\n" +e);
			}
		}

		
		
	}

	private void DisplayEndMessage(Player p)
	{
		if(p.HasUpperSectionBonus())
		{
			System.out.println("BONUS upper section bonus : " + p.upperSectionScore + " > " + YahtzeeGame.UpperSectionBonusThreshold);
			p.score+=UpperSectionBonusScore;	// adding the bonus
		}
		else
		{
			System.out.println("NO BONUS : " + p.upperSectionScore + " < " + YahtzeeGame.UpperSectionBonusThreshold);
		}
		
		System.out.println("\t\t Your final score : " + p.score);
		System.out.println("\t\t\t Your Upper score : " + p.upperSectionScore);
		System.out.println("\t\t\t Your Lower score : " + (p.score - p.upperSectionScore));		
	}
	
	Scanner s;
	
	/**Start a Yahtzee Game, it is by default on SinglePlayer if StartMultiplayer() is not called before*/
	public void Start() 
	{

		if(!isMultiplayer)
		{
			// set up solo-player name
			playerArray = new Player[1];
			 String name = "Unnamed Player";
			    System.out.print("Name of player : ");
			     s = new Scanner(System.in);
				name = s.nextLine();
				playerArray[0] = new Player(name);
		}
		// set up the game, every player will play 13 turns
		// first round for all players than second round etc...
				for(int i = 0; i < YahtzeeGame.GetMaxTurn(); i++) 
				{
					for(Player player : playerArray )
					{
						System.out.println("\n\n///////////");
						System.out.println("/////Player " + player.name);
						System.out.println("///////////");
					player.AskToRollDices();
					player.ShowHimTheArray();;
					player.AskToChooseCombo(reRollCount);
					}
				}
				
				//When all turns are done, display ending message
				for(Player player : playerArray )
				player.ShowHimTheArray();
				System.out.println("\t\t ..//GAME IS FINISHED\\..");
				for(Player player : playerArray )
				{
					DisplayEndMessage(player);
				}
				
				if(isMultiplayer)
				{
					ShowLeaderBoard();
				}
				
				SaveScoreToFile("Scores.txt");
				System.out. println("\n\n\n \t\t\t >> Press Enter to return to the Main Menu << ");
				Scanner s = new Scanner(System.in);
				s.nextLine();
				ShowMenu();
				s.close();
		}
	
	public static void AddSpacing(String s) // for display 
	{
		int tabCount = 2;
		if(	s.length()>8)
		{
			tabCount--;
		}
		
		for(int i = 0; i < tabCount; i++)
			System.out.print("\t");
	}
	
	public static int GetSpacing(String s)
	{
		int tabCount = 2;
		if(	s.length()>10)
		{
			tabCount--;
		}
		
		return tabCount;
	}
	
	
	public void ShowLeaderBoard()
	{
		System.out.println("-------> And the winner is " + GetWinner().name);
		
		System.out.println("\n\n-------------- Leaderboard------------------");
		int i = 1;
		for(Player player : GetPlayersByScore() )
		{
			System.out.print("/ "+i +".  "+ player.name);
			AddSpacing(player.name);
			System.out.print("| Score is " + player.score+" \t/ \n" ) ;
			System.out.println("---------------------------------------------") ;
			i++;
		}
	}
	
	/**Ranks players using the variable score of the class Player*/
	private Player[] GetPlayersByScore()
	{
		Player[] players = new Player[playerArray.length];
		int oldScore = 0;
		Player currentWinner = null;
		for(Player p : playerArray)
		{
			p.ranked = false;
		}
		for(int i = 0; i< playerArray.length;i++)
		{
			for(Player p : playerArray)
			{
					if(p.score >= oldScore && !p.ranked)
					{
						currentWinner = p;
						oldScore = currentWinner.score;
					}
			}
			oldScore = 0;
			players[i] = currentWinner; 
			players[i].ranked = true;
			currentWinner = null;
		}
			return players;		
	}
	
	private Player GetWinner()
	{
		int oldScore = 0;
		Player currentWinner = null;
		for(Player p : playerArray)
		{
			if(p.score >= oldScore)
			{
				currentWinner = p;
				oldScore = currentWinner.score;
			}
		}
		return currentWinner;
	}
	
	public void Quit()
	{
		System.exit(0);
	}
	
	public int GetTurn()
	{
		return turnID;
	}
	
	public static int GetMaxTurn()
	{
		return maxTurnNumber;
	}
	

	public int GetSameNumberCount(int diceNumber)
	{
		int count = 0;
		for(int i = 0; i < YahtzeeGame.dices.length ; i++)
		{
				if(diceNumber == YahtzeeGame.dices[i].currentNumber)
				{
					count++;
				}			
		}
		return count;
	}
	
	  public void DetectCombos(Player p)
		{
			for(Combo c : p.combos)
			{
				if(c.isAvailable)
				{
					if(c.TryDetect())
					{
						System.out.println(c.type + " detected !");
					}
				}
				else
				{
					System.out.println(c.type + " already used");
				}
			}
		}
        
	  /**Used mainly for batch testing*/
        public void DetectAndUseCombos(Player p)
	   {
		for(Combo c : p.combos)
		{
			if(c.isAvailable)
			{
				if(c.TryDetect())
				{
					System.out.println(c.type + " detected and used !");
					c.Use();					
				}
				else 
				{
					System.out.println(c.type + " not matching combination");
				}
			}
			else
			{
				System.out.println(c.type + " already used");
			}
		}
	}
}
