package combos;
import java.util.Arrays;

import core.Combo;
import core.ComboType;
import core.YahtzeeGame;

public class SmallStraight extends Combo
{

	public SmallStraight()
	{
		type = ComboType.SmallStraight;
	}
	
	@Override 
	public int GetScore()
	{
		score = 30;
		return 30;
	}

	@Override
	public boolean TryDetect() 
	{
		 // un optimized allow verification to save performance
        int[] dicesSorted = new int[5];
                    for(int i = 0; i < YahtzeeGame.dices.length; i++)
                    {
                        dicesSorted[i] = YahtzeeGame.dices[i].currentNumber;
                    }
                    Arrays.sort(dicesSorted);
                    
         int duplicatesCount = 0;
         int followCount = 0;
         for(int i = 0; i<dicesSorted.length - duplicatesCount && dicesSorted[i] <= 5 	;i++) // We adapt the smallStraight search to a possible greater amount of dices
         {
        	 if((dicesSorted[i]) == (dicesSorted[i+1]))
        	 {
        		 duplicatesCount++;
        		 //System.out.println("duplicates count" + duplicatesCount);
        	 }
			if((dicesSorted[i]+1) == (dicesSorted[i+1]))
             {
             	followCount++;
             	//System.out.println("Follow count" + followCount);
             	             	
             	if(followCount == 3)
             			return true;
             }
             else followCount = 0;
         }
             return false;
	}

}
