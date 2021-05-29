import java.awt.Font;
public abstract class Objective
{
	public boolean complete = false;
	public String objectiveText;
	public static Font objectiveFont = new Font("Arial", Font.BOLD, 25);
	
	public abstract String getObjectiveText();
	public abstract boolean checkObjective();
	public abstract void completeObjective();
}