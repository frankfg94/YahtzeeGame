package combos;
import core.Combo;
import core.ComboType;
import core.YahtzeeGame;

public class Yahtzee extends Combo
{

	public Yahtzee()
	{
		type = ComboType.Yahtzee;
	}
	
	@Override 
	public int GetScore()
	{
		return 50;
	}

	@Override
	public boolean TryDetect() 
	{
		for(int i = 0; i < YahtzeeGame.dices.length ; i++)
		{
			if(GetSameNumberCount(YahtzeeGame.dices[i].currentNumber) == 5)
			{
				return true;
			}
		}
		return false;
	}

}
