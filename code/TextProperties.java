public class TextProperties
{
	public int stringWidth;
	public int stringHeight;
	public TextProperties( int stringHeight, int stringWidth)
	{
		this.stringWidth = stringWidth;
		this.stringHeight = stringHeight;
	}

	public int getTextWidth()
	{
		return stringWidth; 
	}

	public int getTextHeight()
	{
		return stringHeight; 
	}
}