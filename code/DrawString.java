import java.awt.FontMetrics;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.ArrayList;

public class DrawString
{
	public static ArrayList<String> splitText(String text, Font font, Graphics2D g, int width)
	{
		ArrayList<String> returnList = new ArrayList<String>();
		String[] words = text.split(" ");//String method that splits a String phrase into words, since " " is passed in
		int i = 0;//Count integer
		while (i < words.length)
		{
			String currentLine = words[i++];//String that holds the characters that will be printed on the current line
			while (( i < words.length ) && (g.getFontMetrics(font).stringWidth(currentLine + " " + words[i]) < width))//While loop that runs while the pixel width of the string is less than the width passed in
			{
				currentLine = currentLine + " " + words[i];//Adds as many words as fit onto the line
				i++;
			}
			returnList.add(currentLine);
		}
		return returnList;
	}
}