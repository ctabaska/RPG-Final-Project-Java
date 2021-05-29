import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Savage extends NPC
{
	private int type;
	private BufferedImage alertTemp;
	private Image alert;
	public static boolean alerted;
	private int alertTimeStamp = 0;
	private boolean dropItem = false;
	public Savage(int x, int y, int type)
	{
		super(x, y, 256, 256);
		this.type = type;

		try{
			alertTemp = ImageIO.read(new File("images/alert.png"));
			
		}
		catch(IOException e){
			e.printStackTrace();
		}

		width = alertTemp.getWidth() * Screen.scaleGUI;
		height = alertTemp.getHeight() * Screen.scaleGUI;
		alert = alertTemp.getScaledInstance( (int)width, (int)height, Image.SCALE_FAST);

		super.damagable = true;
	}

	public Savage(int x, int y, int type, boolean dropItem)
	{
		super(x, y, 256, 256);
		this.type = type;
		this.dropItem = true;
		try{
			alertTemp = ImageIO.read(new File("images/alert.png"));
			
		}
		catch(IOException e){
			e.printStackTrace();
		}

		width = alertTemp.getWidth() * Screen.scaleGUI;
		height = alertTemp.getHeight() * Screen.scaleGUI;
		alert = alertTemp.getScaledInstance( (int)width, (int)height, Image.SCALE_FAST);
	}

	@Override
	public void drawMe(Graphics2D g)
	{
		if(y > -250)
			g.drawImage(npc[type], (int)x, (int)y, null);
		if((Math.abs(Screen.npcs.get(0).getX() - x) < 200 || alerted) && initialY  + 4* height> 0){
			//System.out.println("alerted: " + height);
			if(Screen.time - alertTimeStamp > 30)
			{ 
				initialY-= 9;
			}
			if(alerted == false)
				alertTimeStamp = Screen.time;
			if(dropItem){
				System.out.println("create new items?");
				Screen.availableItems.add(new Item(1, false, (int)x, (int)y + 20));
				dropItem = false;
			}
			alerted = true;
			g.drawImage(alert, (int)x, (int)y, null);
		}
	}


}