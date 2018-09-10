package graphics;

import core.Dice;

public class Drawings
{

	public static void ShowMainMenuLogo()
	{
		System.out.println("\n\n ______________________________________________________________________________________");
		System.out.println("|                                                                                      |");
		System.out.println("|                                                                                      |"); System.out.println("|                                                                                      |");
		System.out.println("| YMM     MM    db       7MMF    7MMF MMP\"\"MM\"\"7MM MMM\"\"\"AMV   MM\"\"\"YMM    MM\"\"\"YMM    |\r\n" + 
		"|  VMA   ,V    ;MM:       MM      MM       MM           AMV    MM          MM          |\r\n" + 
		"|   VMA ,V    ,V^MM.      MM      MM       MM          AMV     MM          MM          |\r\n" + 
		"|    VMMP    ,M  `MM      MMmmmmmmMM       MM         AMV      MMmmMM      MMmmMM      |\r\n" + 
		"|     MM     AbmmmqMA     MM      MM       MM        AMV       MM          MM          |\r\n" + 
		"|     MM    A'     VML    MM      MM       MM       AMV        MM          MM          |\r\n" + 
		"|    JMML  AMA     AMMA  JMML    JMML     JMML     AMVmmmmMM   MMmmmmMMM   MMmmmmMMM   |");
		System.out.println("|                                                                                      |");
		System.out.println("|                                                                                      |");
		System.out.println("|                                                                                      |");
		System.out.println("|______________________________________________________________________________________|\n\n");
	}
	
	public static void ShowDicesHorizontal(Dice[] dices)
	{
		String[] lines =new String[5];
		String space = "\t";
		for(int lineID = 0; lineID < 5; lineID++ )
		{
			lines[lineID]="";
			for(int diceID = 0; diceID < dices.length; diceID++)
			{
				lines[lineID] += GetDiceDrawing(dices[diceID].currentNumber)[lineID] + space;
			}
		}
				
		for(String line : lines)
		{
			System.out.println(line);
		}
	}
	
	public static String[] GetDiceDrawing(int diceNumber)
	{
		String[] diceDrawing = {"--Error unknown dice number--"}; // The drawing that will be obtained
		switch(diceNumber)
		{
		case 1:
			diceDrawing = new String[]
					{" #########",
				     " #       #",
					 " #   0   #",
					 " #       #",
					 " #########"};
			break;
		case 2:
			diceDrawing = new String[]
					{
						" #########" ,
						" # 0     #" , 
						" #       #" , 
						" #     0 #" , 
						" #########"	
					};
			break;
		case 3:
			diceDrawing = new String[]
					{
						" #########" ,
						" # 0     #" , 
						" #   0   #" , 
						" #     0 #" , 
						" #########"	
					};
			break;
		case 4:
			diceDrawing = new String[]
					{
						" #########" ,
						" # 0   0 #" , 
						" #       #" , 
						" # 0   0 #" , 
						" #########"	
					};
			break;
		case 5:
			diceDrawing = new String[]
					{
						" #########" ,
						" # 0   0 #" , 
						" #   0   #" , 
						" # 0   0 #" , 
						" #########"	
					};
			break;
		case 6:
			diceDrawing = new String[]
					{
						" #########" ,
						" # 0   0 #" , 
						" # 0   0 #" , 
						" # 0   0 #" , 
						" #########"	
					};
			break;
	    default:
			System.out.println("Error, Unknown dice number for getting graphical array, please check range : " + diceNumber );
			break;
		}
		return diceDrawing;
	}
}
