package core;
import	java.util.Random;

public class Dice 
{
	public int currentNumber = 0;
	public boolean isKept = false;
	boolean isRolled = false;
	
	public void Roll()
	{
		Random r = new Random();
		currentNumber = r.nextInt(6);
		currentNumber++; // The dice number goes from 0 to 5, we add 1 to avoid currentNumber == 0
		isRolled = true;
	}
	

	
	public void Keep()
	{ 
		isKept = true;
	}
}
