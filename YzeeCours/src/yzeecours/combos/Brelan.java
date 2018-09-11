package combos;
import core.Combo;
import core.ComboType;
import core.YahtzeeGame;

public class Brelan extends Combo
{
	
	public Brelan()
	{
		this.type = ComboType.Brelan;
	}
	
	public int GetScore()
	{
		score = 0;
		// We add the total of all dices
				for(int i = 0; i < YahtzeeGame.dices.length;i++)
				{
					score+=YahtzeeGame.dices[i].currentNumber;
				}
				return score;	}

	@Override
	public boolean TryDetect() 
	{
		for(int i = 0; i < YahtzeeGame.dices.length ; i++)
		{
			if(GetSameNumberCount(YahtzeeGame.dices[i].currentNumber) == 3)
			{
				targetNumber = YahtzeeGame.dices[i].currentNumber;
				return true;
			}
		}
		return false;
	}

}
