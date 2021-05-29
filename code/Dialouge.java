import java.awt.*;
import java.util.ArrayList;
public class Dialouge extends MainObject
{
	public static boolean setup = false;
	public static String currentDialouge = "";
	private ArrayList<String> dialougeStrings = new ArrayList<String>();
	private ArrayList<TextProperties> dialougeStringsProperties = new ArrayList<TextProperties>();
	public static Font dialougeFont = new Font("Arial", Font.BOLD, 50);
	private TextProperties dialougeProperties;
	private int fontHeight;
	FontMetrics dialougeFontMetrics;

	public Dialouge()
	{
		super(0, Screen.screenHeight * 0.8, Screen.screenWidth, Screen.screenHeight * 0.2);
		
	}

	public void drawMe(Graphics2D g)
	{
		if(!setup){
			dialougeStrings = DrawString.splitText(currentDialouge, dialougeFont, g, (int)(Screen.screenWidth));
			dialougeFontMetrics = g.getFontMetrics(dialougeFont);
			
			fontHeight = dialougeFontMetrics.getHeight();
			
			setup = true;
		}
		if(!currentDialouge.equals(""))
		{
			g.setColor(Color.gray);
			g.fillRect((int)x,(int)y,(int)width,(int)height);
			g.setColor(Color.white);
			g.setFont(dialougeFont);
			for (int i = 0 ; i < dialougeStrings.size() ; i++ ) {
				g.drawString(dialougeStrings.get(i), (int)((Screen.screenWidth - dialougeFontMetrics.stringWidth(dialougeStrings.get(i))) / 2.0), (int)y + 50 + fontHeight * i);
			}
			
		}
		
	}

	public static void nextLine(String nextLine)
	{
		currentDialouge = nextLine;
		setup = false;
	}
}