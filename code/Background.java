import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Background
{
	private int grassWidth;
	private int grassHeight;
	private BufferedImage grassTemp;
	private Image grass;
	private Rectangle[][] backgroundSquares;
	public Background()
	{
		try{
			grassTemp = ImageIO.read(new File("images/grass.jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}

		backgroundSquares = new Rectangle[5][3];

		grassWidth = grassTemp.getWidth();
		grassHeight = grassTemp.getHeight(); 
		grass = grassTemp.getScaledInstance( (int)grassWidth*Screen.scaleGUI, (int)grassHeight*Screen.scaleGUI, Image.SCALE_FAST);
	}

	public void drawBackground(Graphics2D g){
		for (int r = -1 - (int)(Screen.screenY/(64.0 * Screen.scaleGUI)); r < Screen.screenHeight / (64.0 * Screen.scaleGUI)  + 2 - (int)(Screen.screenY/(64.0 * Screen.scaleGUI)); r++ ) 
		{
			for (int c = -1 - (int)(Screen.screenX/(64.0 * Screen.scaleGUI)); c < Screen.screenWidth / (64.0 * Screen.scaleGUI)  + 2 - (int)(Screen.screenX/(64.0 * Screen.scaleGUI)); c++ ) 
			{
				g.drawImage(grass, Screen.screenX + (int)(64.0 * Screen.scaleGUI) * c, Screen.screenY + (int)(64.0 * Screen.scaleGUI) * r , null);
			}
		}
	}

	public void move()
	{
		if (Screen.w) {
			Screen.screenY +=5;
		}
		if (Screen.s) {
			Screen.screenY -=5;
		} 
		if (Screen.a) {
			Screen.screenX +=5;
		} 
		if (Screen.d) {
			Screen.screenX -=5;
		}
	}	
}