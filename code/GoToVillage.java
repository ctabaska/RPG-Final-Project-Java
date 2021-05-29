public class GoToVillage  extends Objective
{
	public GoToVillage(){
		objectiveText = "Go to the village";
	}

	public String getObjectiveText()
	{
		return objectiveText;
	}

	public boolean checkObjective()
	{
		
		return complete;
	}

	public void completeObjective()
	{
		complete = true;
	}
}