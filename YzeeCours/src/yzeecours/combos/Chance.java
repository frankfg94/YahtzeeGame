package combos;
import core.Combo;
import core.ComboType;
import core.YahtzeeGame;

public class Chance extends Combo 
{
	public Chance()
	{
		this.type = ComboType.Chance;
	}
	
	@Override
	public int GetScore()
	{
		score = 0;
		// We add the total of all dices
				for(int i = 0; i < YahtzeeGame.dices.length;i++)
				{
					score+=YahtzeeGame.dices[i].currentNumber;
				}
				return score;
   }
	
	@Override
	public boolean TryDetect()
	{
		return true;
	}
}
