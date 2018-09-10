package combos;
import core.Combo;
import core.ComboType;
import core.Dice;
import core.YahtzeeGame;

public class FullHouse extends Combo
{

	public FullHouse()
	{
		this.type = ComboType.FullHouse;
	}

	@Override
	public boolean TryDetect() 
	{
	       for (Dice dice1 : YahtzeeGame.dices) {
               if (GetSameNumberCount(dice1.currentNumber) == 3) {
                   for (Dice dice : YahtzeeGame.dices) {
                       if (GetSameNumberCount(dice.currentNumber) == 2) {
                           return true;
                       }
                   }
               }
           }
		return false;
	}
       
	@Override 
	public int GetScore()
	{
		score = 25;
		return score;
	}
	
	
          public void IsNumber(int number)
       {
       	   int count = 0;
           for(int i = 0; i < 5 ; i++)
           {
               if((number) == YahtzeeGame.dices[i].currentNumber)
               {
               	count++;
               };
           }
           if(count > 0)
           System.out.println("Obtained the number "+ number +" , "+  count + " times");

	}

}
