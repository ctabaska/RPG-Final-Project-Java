import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.util.ArrayList;
public class StartScreen
{
	private Font arial = new Font("Arial", Font.BOLD, 20);
	private ArrayList<String> startTexts;
	private ArrayList<TextProperties> startTextsProperties;
	private String startText;
	private int fontHeight;
	private boolean setup = false; //setup boolean for setting up the position of the text
	private int time;
	private String pSpace = "Press SPACEBAR to start";
	private TextProperties ofPSpace;
	public StartScreen()
	{
		startText = "...";
		startTexts = new ArrayList<String>();
		startTextsProperties = new ArrayList<TextProperties>();
		time = 0;
	}

	public void drawMe(Graphics2D g)
	{
		if(!setup){
			startTexts = DrawString.splitText(startText, arial, g, (int)(Screen.screenWidth * 0.7));
			FontMetrics arialMetrics = g.getFontMetrics(arial);
			ofPSpace = new TextProperties(arialMetrics.getHeight(), arialMetrics.stringWidth(pSpace));
			for (int i = 0; i < startTexts.size() ; i++ ) 
			{
				startTextsProperties.add(new TextProperties( arialMetrics.getHeight(), arialMetrics.stringWidth(startTexts.get(i))));
			}
			fontHeight = arialMetrics.getHeight();
			setup = true;
		}
		g.setColor(Color.black);
		g.fillRect(0,0, Screen.screenWidth, Screen.screenHeight);
		g.setColor(Color.white);
		g.setFont(arial);
		for (int i = 0; i < startTexts.size() ; i++ ) 
		{
			g.drawString(startTexts.get(i), 
				(int)(Screen.screenWidth - startTextsProperties.get(i).stringWidth)/2, 
				(int)(Screen.screenHeight * 0.4) + (fontHeight * i));
		}
		g.drawString(pSpace,
			(int)(Screen.screenWidth - ofPSpace.getTextWidth())/2, 
				(int)(Screen.screenHeight * 0.8));
		time++;
	}
}