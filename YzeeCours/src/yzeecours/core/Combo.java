package core;


 public class Combo
{
  boolean isAvailable = true;
  public ComboType type;
  protected int score = 0;
  protected int targetNumber = 0; // Main number that is used for the combo
  
  /**We use constructor overloading*/
  public Combo(ComboType type)
  {
	  this.type = type;
  }
  
  public Combo()
  {
	  
  }
  
  public int GetScore()
  {
	return score;
  }
  
  public void Use()
  {
	  isAvailable = false;
  }
  
  /** Returns true if the associated dice combination is matching the dices numbers for this roll*/
  public boolean TryDetect()
  {
	  return false;
  }
  
  /**Returns the number of times we have the same dice number for the current roll
   * @param diceNumber : The number that will be checked*/
  protected int GetSameNumberCount(int diceNumber)
	{
		int count = 0;
		for(int i = 0; i < YahtzeeGame.dices.length ; i++)
		{
				if(diceNumber == YahtzeeGame.dices[i].currentNumber)
				{
					count++;
				}
		}
		return count;
	}
}
 
