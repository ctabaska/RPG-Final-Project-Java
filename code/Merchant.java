import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Merchant extends NPC
{
	private BufferedImage merchant;
	public Merchant(int x, int y, String[] dialouge)
	{
		super(x,y,dialouge);
		try{
			merchant = ImageIO.read(new File("images/merchant.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}

		super.damagable = false;
	}

	@Override
	public void drawMe(Graphics2D g)
	{
		if(Map.currentMap == 2)
			g.drawImage(merchant, (int)x, (int)y, null);
	}
}