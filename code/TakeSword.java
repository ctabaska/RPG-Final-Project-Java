public class TakeSword  extends Objective
{
	public TakeSword(){
		objectiveText = "Take the sword";
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
			complete = Inventory.checkForItem(1);
			//System.out.println("Returning yes for apple? " + Inventory.checkForItem(0));
		}
		
		return complete;
	}
	public void completeObjective()
	{
		
	}
}