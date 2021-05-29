import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.*;
public class Quest
{
	private String questName;
	public Objective[] objectives;
	private int currentObjective;
	public boolean complete;
	public static Font questFont = new Font("Arial", Font.BOLD, 40);
	private FontMetrics questFontMetrics;
	private FontMetrics objectiveFontMetrics;
	private boolean setup = false;
	public Quest(String questName, int numObjectives)
	{
		this.questName = questName;
		this.objectives = new Objective[numObjectives];
		this.currentObjective = 0;
	}

	public void checkObjectives()
	{
		if(objectives[currentObjective].checkObjective())
		{
			if(currentObjective + 1 < objectives.length )
				currentObjective++;
			else
				complete = true;
		}
	}

	public void drawQuest(Graphics2D g)
	{
		if(!setup){
			questFontMetrics = g.getFontMetrics(questFont);
			objectiveFontMetrics = g.getFontMetrics(Objective.objectiveFont);
			setup = true;
		}

		g.setColor(Color.white);
		g.setFont(questFont);
		g.drawString(questName, (int)(Screen.screenWidth - questFontMetrics.stringWidth(questName)), (int)(Screen.screenHeight *0.3));
		//System.out.println("run");
		g.setFont(Objective.objectiveFont);
		for (int i = 0; i < objectives.length ; i++ ) {
			//System.out.println("run2");
			//System.out.println(objectives[i].checkObjective());
			if(objectives[i].checkObjective())
			{
				//System.out.println("??");
				g.setColor(Color.green);
			} else
				g.setColor(Color.white);
			g.drawString(objectives[i].getObjectiveText(), (int)(Screen.screenWidth - objectiveFontMetrics.stringWidth(objectives[i].getObjectiveText())), (int)(Screen.screenHeight *0.3) + 20 + objectiveFontMetrics.getHeight()*i);
		}
	}
}