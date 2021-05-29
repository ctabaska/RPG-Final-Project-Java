import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Obstacle extends MainObject
{
	private int type;
	private BufferedImage treeTemp;
	private Image tree;	
	public Obstacle(int x, int y, int type)
	{
		super(x,y,0,0);
		this.type = type;
		try{
			treeTemp = ImageIO.read(new File("images/Tree.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		width = treeTemp.getWidth() * Screen.scaleGUI;
		height = treeTemp.getHeight() * Screen.scaleGUI;
		tree = treeTemp.getScaledInstance( (int)width, (int)height, Image.SCALE_DEFAULT);
	}

	public void drawMe(Graphics2D g)
	{
		g.drawImage(tree, (int)Map.mapPosition.getX() + (int)x, (int)Map.mapPosition.getY() + (int)y, null);
	}
}