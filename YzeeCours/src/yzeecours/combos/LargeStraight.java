package combos;
import java.util.Arrays;

import core.Combo;
import core.ComboType;
import core.Dice;
import core.YahtzeeGame;

public class LargeStraight extends Combo
{

	public LargeStraight()
	{
		this.type = ComboType.LargeStraight;
	}

	
	@Override 
	public int GetScore()
	{
		score = 40;
		return score;
	}
	
	@Override
	public boolean TryDetect() 
	{
		   int uniqueCount= 0;
           for (Dice dice1 : YahtzeeGame.dices) {
               if (GetSameNumberCount(dice1.currentNumber) == 1) 
               {
                       uniqueCount++;
               }
           }
           if(uniqueCount >= 5 )
           {
                               
                               int[] dicesSorted = new int[5];
                               for(int i = 0; i < YahtzeeGame.dices.length; i++)
                               {
                                   dicesSorted[i] = YahtzeeGame.dices[i].currentNumber;
                               }
                               Arrays.sort(dicesSorted);
 
                               
                               for(int i = 0; i < YahtzeeGame.dices.length-1; i++)
                               {
                                   if((dicesSorted[i]+1 != ((dicesSorted[i+1]))))
                                   {
                                       return false;
                                   }
     
                               }
                               return true;
           }
           else
		return false;
	}

}
