public class TakeApple extends Objective
{
	public TakeApple(){
		objectiveText = "Take the apple";
	}

	public String getObjectiveText()
	{
		return objectiveText;
	}

	public boolean checkObjective()
	{
		//System.out.println("checking objective?");
		if(!Inventory.checkEmpty()){
			//System.out.println("Inventory: " + Inventory.checkEmpty());
			return false;
		} else
		{
			complete = Inventory.checkForItem(0);
			//System.out.println("Returning yes for apple? " + Inventory.checkForItem(0));
		}
		
		return complete;
	}
	public void completeObjective()
	{
		
	}
}