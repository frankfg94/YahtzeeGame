package combos;
import core.Combo;
import core.ComboType;
import core.YahtzeeGame;

public class Number extends Combo
{

	int storedValue = -1;
	public Number()
	{
	}
	

	
	public Number(int value)
	{
		storedValue = value;
	   	targetNumber = storedValue;
		switch(value)
		{
		case 1:
			type = ComboType.One;
			break;
		case 2:
			type = ComboType.Two;
			break;
		case 3:
			type = ComboType.Three;
			break;
		case 4:
			type = ComboType.Four;
			break;
		case 5:
			type = ComboType.Five;
			break;
		case 6:
			type = ComboType.Six;
			break;
		}
	}

	@Override
	public int GetScore() // number of times our dices have the same number for the current Roll
	{
		score =  targetNumber*GetSameNumberCount(targetNumber);
		return score;
	}
	
	@Override
	public boolean TryDetect()
	{

     for(int i = 0; i < 5 ; i++)
     {
         if((storedValue) == YahtzeeGame.dices[i].currentNumber)
         {
     		return true;
         };
     }
     return false;
	}

}
