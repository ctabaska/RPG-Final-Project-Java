import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Tile extends MainObject
{
	private BufferedImage grassTemp;
	private BufferedImage woodTemp;
	private Image grass;
	private Image wood;
	private Obstacle tileObstacle;
	private TeleportPad tileTeleportPad;
	public int obstacleType;
	public Tile(int x, int y, int obstacleType)
	{
		super(x,y,0,0);
		try{
			grassTemp = ImageIO.read(new File("images/grass.jpg"));
			woodTemp = ImageIO.read(new File("images/wood.jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		this.obstacleType = obstacleType;
		width = grassTemp.getWidth() * Screen.scaleGUI;
		height = grassTemp.getHeight() * Screen.scaleGUI;
		grass = grassTemp.getScaledInstance( (int)width, (int)height, Image.SCALE_FAST);
		wood = woodTemp.getScaledInstance( (int)width, (int)height, Image.SCALE_FAST);
		if(obstacleType == 1){
			tileObstacle = new Obstacle(x,y,0);
		} else if(obstacleType == 2){
			tileTeleportPad = new TeleportPad(x,y);
		}
		
	}

	public void drawMe(Graphics2D g)
	{
		if(obstacleType == 0 || obstacleType == 1)
			g.drawImage(grass, (int)Map.mapPosition.getX() + (int)x, (int)Map.mapPosition.getY() + (int)y, null);
		else if(obstacleType == 0)
			g.drawImage(grass, (int)Map.mapPosition.getX() + (int)x, (int)Map.mapPosition.getY() + (int)y, null);
		if(obstacleType == 1)
			tileObstacle.drawMe(g);
		if(obstacleType == 2){
			//System.out.println("draw ed boy coordinates: " + x + " " + y);
			tileTeleportPad.drawMe(g);
		}
		if(obstacleType == 3){
			g.drawImage(wood, (int)Map.mapPosition.getX() + (int)x, (int)Map.mapPosition.getY() + (int)y, null);
		}
	}

	public String toString()
	{
		return  "" + obstacleType;
	}
}