package core;
import java.util.Scanner;
import combos.Brelan;
import combos.Chance;
import combos.FullHouse;
import combos.LargeStraight;
import combos.SmallStraight;
import combos.Square;
import combos.Yahtzee;
import graphics.Drawings;
import combos.Number;

public class Player
{
	public String name = "Unnamed Player";
	public int score = 0;
	public Combo[] combos;
	public int upperSectionScore = 0;
	private int comboIndex = 0;
	public boolean ranked = false;
	private static final int errorNumber = -999;
	private boolean manualSacrifice = false;
	Scanner keyIn, s1, s2, s3;


	public Player(String name) 
	{
		this.name = name;
		AssignCombos();
	}
	
	/** Method that creates the combos for each combo type in the ComboType Enum*/
	private void AssignCombos()
	{
		combos = new Combo[ComboType.values().length];// There are 13 possibles combinations in a Yahtzee Game
		int comboID = 0;
		for(ComboType typeInEnum : ComboType.values())
		{
			combos[comboID] = ReturnCorrectComboClass(typeInEnum);
			comboID++;
		}
	}
	
	/** Method that returns the correct inherited class from the Combo base class */
	private Combo ReturnCorrectComboClass(ComboType t)
	{
		Combo comb = new Combo();
		switch(t)
		{
		case Brelan:
			comb = new Brelan();
			break;
		case Square:
			comb = new Square();
			break;
		case FullHouse:
			comb = new FullHouse();
			break;
		case Yahtzee:
			comb = new Yahtzee();
			break;
		case SmallStraight:
			comb = new SmallStraight();
			break;
		case LargeStraight:
			comb = new LargeStraight();
			break;
		case Chance:
			comb = new Chance();
			break;
		case One:
			comb = new Number(1);
			break;
		case Two:
			comb = new Number(2);
			break;
		case Three:
			comb = new Number(3);
			break;
		case Four:
			comb = new Number(4);
			break;
		case Five:
			comb = new Number(5);
			break;
		case Six:
			comb = new Number(6);
			break;
		default :
			System.out.println("Error, Undefined class to return for this enum :" + t);
			break;
		}
		
		return comb;
	}
	
	
	public void AskToRollDicesSkip() // used for the first turn or when the player doesn't wish to keep any dice
	{
		for(int i = 0; i < YahtzeeGame.dices.length; i++ )
		{	
			YahtzeeGame.dices[i].Roll();
			System.out.print(YahtzeeGame.dices[i].currentNumber + "\t");
		}	
		System.out.print("\n");
	}
	
	private void WaitForEnterPress()
	{
		keyIn = new Scanner(System.in);
		System.out.println("\t\t>> Press Enter to roll the dices <<");
		keyIn.nextLine();
	}
	
	public void AskToRollDices() // used for the first turn or when the player doesn't wish to keep any dice
	{
		WaitForEnterPress();	
		for(int i = 0; i < YahtzeeGame.dices.length; i++ )
		{	
			YahtzeeGame.dices[i].Roll();
		}
		System.out.print("\n");
	}
	
	public void AskToRollSpecificDices(int[] Ids) // Useful when the player wants to keep some dices and roll other dices
	{
		for(int i = 0; i < Ids.length; i++)
		{
			YahtzeeGame.dices[i].Roll();
		}
	}
	
	public boolean HasUpperSectionBonus()
	{

		if(upperSectionScore > YahtzeeGame.UpperSectionBonusThreshold )
			return true;
		else 
			return false;
			
	}
	
	public boolean IsUpperSection(Combo c)
	{
		if(c.type == ComboType.One || 
				c.type == ComboType.Two ||
				c.type == ComboType.Three ||
				c.type == ComboType.Four ||
				c.type == ComboType.Five ||
				c.type == ComboType.Six)
		{
			return true;
		}
		return false;
	}
	
	/**Displaying Available or the value of the combo for the current line in the score array */
	private void DisplayInScoreTab(ComboType type, String s)
	{
		if(type != ComboType.SmallStraight && type != ComboType.LargeStraight && type != ComboType.FullHouse)	// Fix Spacing for long enum names
		{
			System.out.print("\t");
		}
		System.out.println("\t\t"+s);
	}
	
	private void DisplayUpperSectionScore()
	{
		System.out.println("_________________________________\n");
		System.out.println(" TOTAL UPPER SECTION : " + upperSectionScore);
	}
	
	private void DisplayTotalScore()
	{
		System.out.println("_________________________________\n");
		System.out.println(" TOTAL : " + score);
		System.out.println("_________________________________\n");
	}
	
	/**The score array specific to the player*/
	public void ShowHimTheArray()
	{
		boolean available = true;
		
		for( ComboType t : ComboType.values())
		{
			int scoreForCurrentLine = -1;
			System.out.print("_________________________________\n");
			System.out.print(t);
			for(Combo c : combos)
			{
				if(c.type == t )
					if(c.isAvailable)
						available = true;						
					else 
					{
						available = false;
						scoreForCurrentLine = c.score;
					}
			}
			
			if(available)
				DisplayInScoreTab(t,"Available");			
			else if(scoreForCurrentLine!=-1)
				DisplayInScoreTab(t,String.valueOf(scoreForCurrentLine));
			else 
				DisplayInScoreTab(t,"Error");
			
			if(t == ComboType.Six)
			{
				DisplayUpperSectionScore();
			}
		}
		DisplayTotalScore();	// the total score is displayed at the end of the array
	}
	
	/**Method used in the case where the player wants to obtain a better dice combination, or want to sacrifice directly a combination*/
	public void AskToRollDicesAgain()
	{
		System.out.println("Choose dice (1-5) to roll specific dices");
		System.out.println("Choose 0 to sacrifice");
		 s1 = new Scanner(System.in);
		String choose_dice = "-1";
		int diceID = -1; //  id used to designate errors
		
		while (diceID != 9)
		{
		try
		{
		choose_dice = s1.nextLine();
		diceID = Integer.valueOf(choose_dice);
		if (diceID <= 5 && diceID >= 1)
		{
		YahtzeeGame.dices[diceID - 1].Roll();
		System.out.println("\nYour dice " + diceID + " has been rolled");
		}
		else if(diceID == 0)
		{
			manualSacrifice = true;
			AskToSacrificeCombo();
			return;
		}
		else if (diceID != 9 && (diceID > 5 || diceID < 1))
		{
		System.out.println("Please enter between 5 and 1 or 9");
		}
		}
		catch (NumberFormatException e)
		{
		System.out.println("Unrecognized input, please enter a number");
		}
		if(diceID != 9)
		{
			System.out.println("Choose dice (1-5) to roll specific dices");
			System.out.println( "Use 9 to stop rolling");
		}

		}
		return;
	}
	
	
	
	
	private int comboChoice = 0;
	
	/**Ask the user to input a number, there are 2 errors types : 
	 * <p><b>Error1</b> : The input is not a number  
	 * <br><b>Error2</b> : The input is a number but not equal to a choice number*/
	private void SafeComboChoiceInput(int comboCount, int remainingRolls)
	{
		try
		{
			 s3 = new Scanner(System.in);
			comboChoice = s3.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Please enter a number");
			
			if (comboChoice > comboCount || comboChoice < 0) 
			{
				if(comboChoice != errorNumber )
				{
					System.out.println("The number "+ comboChoice +" is incorrect");
					
				}
			}
			AskToChooseCombo(remainingRolls);
		}
		
	}
	
	/**Launch combo detections algorithms for each combo for the current player and display available combos*/
	private void DisplayChooseMessageCombo()
	{
		comboIndex = 0;
		System.out.println("\t\t Choose the combination you want for this combo");
		Drawings.ShowDicesHorizontal(YahtzeeGame.dices);
		for(Combo c : combos)
		{		
			if(c.isAvailable)	// We check if the combo is not already used in the same game
				if(c.TryDetect())	// We check is the combo is compatible with the dices numbers
				{
					System.out.print("\nChoice " + comboIndex + " : "+ c.type + "    ");
					YahtzeeGame.AddSpacing(c.type.toString());
					System.out.print("| " + c.GetScore());
					comboIndex++;
				}
		}
	}
	
	/**Use the combo corresponding to the entered choice by the player */
	private void UseSelectedCombo()
	{
		int j = 0;
		for(Combo c : combos)
		{
			if(c.isAvailable)
				if(c.TryDetect())
				{
					if(j == comboChoice)
					{
						System.out.println("\nYou used the combination : " + c.type +" because you entered " + comboChoice );
						System.out.println("You gain " + c.GetScore() + " points" );
						this.score+=c.GetScore();
						if(IsUpperSection(c))
							upperSectionScore+= c.GetScore();
						c.Use();
					}
					j++;
				}
		}
		comboChoice = errorNumber; // To avoid over use of combos due to string input (e.g : "dhfu,s9") will select us 2 combos and not only 1 on the next user input
	}
	
	/**We ask the user to input a number corresponding to a possible number of dice combination */
	public void AskToChooseCombo(int remainingRolls)
	{
		if(manualSacrifice) // We check if the sacrifice is a voluntary act, then we can't choose anymore a combo 
		{
			manualSacrifice = false;
			return;					
		}
		
		DisplayChooseMessageCombo();

			if (remainingRolls > 0)
				System.out.println("\nChoice "+ comboIndex   +" : Roll Again \n"); // If the player still has the right to roll dices, propose this choice to him
			if(comboIndex <= 0 && remainingRolls <=0) // No combo was detected 
			{
				AskToSacrificeCombo();
			}
			else	// if at least one combo is detected
			{
				SafeComboChoiceInput(comboIndex,remainingRolls);
				if(comboChoice == comboIndex && remainingRolls > 0) // Special case if we want to roll dices again
				{
				AskToRollDicesAgain();
				AskToChooseCombo(remainingRolls-1);
				}
				else	// if not, simply use the combo (add it's score and disable it)
				{
				UseSelectedCombo();
				}
		}
		}
		
	
	private int sacrificeChoice = 0;
	
	private void InputSacrificeNumberSafe()
	{
		try
		{
			 s2 = new Scanner(System.in);
			 sacrificeChoice = s2.nextInt();
			 System.out.println(" "  + sacrificeChoice);
			 if(sacrificeChoice > sacrificeCount -1 || sacrificeChoice < 0)
			 {
				 System.out.println("Please choose a combo to sacrifice with a number between 0 and " + (sacrificeCount -1));
				 InputSacrificeNumberSafe();
			 }
		}
		catch(Exception e)
		{
			System.out.println("You must enter the number of a combo to be sacrificed");
			DisplayAvailableCombos();
			InputSacrificeNumberSafe();
		}
		sacrificeCount = 0; // Once the sacrifice is done, reset the counter
	}
	
	int sacrificeCount = 0;
	
	/**Displays one dice combination per line, associated to a number*/
	private void DisplayAvailableCombos()
	{
		System.out.println("\t\t Choose the combo you want to disable");
		int i = 0;
		for(Combo c : combos)
		{
			if(c.isAvailable)
			{
				System.out.println("Choice " + i + " : "+ c.type);
				i++;			
			}
		}
		sacrificeCount = i;
	}
	
	
	private void DisplayLastSacrificedCombo()
	{
		int i = 0;
		for(Combo c : combos)
		{
			if(c.isAvailable)
			{
				if(sacrificeChoice == i)
				{
					System.out.println("Choice " + i + " : "+ c.type + " DISABLED");
					c.Use();
				}
				i++;
			}
		}
	}
		/**Prompt the user to sacrifice one of it's combinations, he can only disable the combinations he hasn't used yet*/
		public void AskToSacrificeCombo()
		{
			DisplayAvailableCombos();
			InputSacrificeNumberSafe();
			DisplayLastSacrificedCombo();
		}
		
	
	
}
