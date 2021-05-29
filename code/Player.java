import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Player extends MainObject
{
	public static int facingPos = 3;
	public static double speed = 5;
	private BufferedImage[] charImgTemp = new BufferedImage[4];
	private BufferedImage[] swordImgTemp = new BufferedImage[4];
	private Image[] charImg = new Image[4];
	private Image[] swordImg = new Image[4];

	public static MainObject interactionArea;
	public Player()
	{
		super((double)Screen.screenWidth/2,(double)Screen.screenHeight/2,(double)24,(double)24);
		try{
			charImgTemp[0] = ImageIO.read(new File("images/upChar.png"));
			charImgTemp[1] = ImageIO.read(new File("images/rightChar.png"));
			charImgTemp[2] = ImageIO.read(new File("images/downChar.png"));
			charImgTemp[3] = ImageIO.read(new File("images/leftChar.png"));
			
			swordImgTemp[0] = ImageIO.read(new File("images/swordUp.png"));
			swordImgTemp[1] = ImageIO.read(new File("images/swordRight.png"));
			swordImgTemp[2] = ImageIO.read(new File("images/swordDown.png"));
			swordImgTemp[3] = ImageIO.read(new File("images/swordLeft.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		for (int i = 0; i < swordImg.length ; i++ ) {
			width = swordImgTemp[i].getWidth() * Screen.scaleGUI;
			height = swordImgTemp[i].getHeight() * Screen.scaleGUI;
			swordImg[i] = swordImgTemp[i].getScaledInstance( (int)width, (int)height, Image.SCALE_FAST);
		}
		for (int i = 0; i < charImg.length ; i++ ) {
			width = charImgTemp[i].getWidth() * Screen.scaleGUI;
			height = charImgTemp[i].getHeight() * Screen.scaleGUI;
			charImg[i] = charImgTemp[i].getScaledInstance( (int)width, (int)height, Image.SCALE_FAST);
		}
		

		interactionArea = new MainObject(x - width/2 - width/3, y - height/2, width - width/3, height);
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.black);
		//g.fillRect((int)x - (int)width/2,(int) y - (int)height/2,(int) width,(int) height);
		g.drawImage(charImg[facingPos], (int)x - (int)width/2, (int) y - (int)height/2, null);
		g.setColor(new Color(0f, 0f, 1f, 0.2f));
		g.fillRect((int)interactionArea.x, (int)interactionArea.y, (int)interactionArea.width, (int)interactionArea.height);
		if(Screen.quests.get(0).objectives[1].complete){

			g.drawImage(swordImg[facingPos], (int)interactionArea.x, (int)interactionArea.y + (int)interactionArea.height - (int)(swordImgTemp[facingPos].getHeight() * Screen.scaleGUI), null);
		}
		
	}

	public void move(){
		if (Screen.w) {
			//if(!Map.checkFutureCollsionUp())
				interactionArea = new MainObject(x - width/2,  y - height/2  - height/3, width, width);
			facingPos = 0;
		}
		if (Screen.s) {
			//if(!Map.checkFutureCollsionDown())
				interactionArea = new MainObject(x - width/2,  y, width, width);
			facingPos = 2;
		} 
		if (Screen.a) {
			//if(!Map.checkFutureCollsionLeft())
				interactionArea = new MainObject(x - width/2 - width/3, y - height/2, width - width/3, height);
			facingPos = 3;
		} 
		if (Screen.d) {
			//if(!Map.checkFutureCollsionRight())
				interactionArea = new MainObject(x, y - height/2, width - width/3, height);
			facingPos = 1;
		}
	}

	public void changeSpeed(double speedChange){
		speed += speedChange;
	}

	public void checkPickup()
	{
		if(!Inventory.checkFull())
		{
			
			for (int i = 0; i < Screen.availableItems.size() ; i++ ) {
				
				if( interactionArea.collision(Screen.availableItems.get(i)))
				{
					
					Inventory.addItem(Screen.availableItems.get(i));
					Screen.availableItems.remove(i);
					Screen.quests.get(0).checkObjectives();
					if(Screen.quests.get(0).complete){

					}
					Screen.npcs.get(0).advanceDialouge();
				}
			}
		}
	}

	public void checkInteraction()
	{
		for(int i = 0; i < Screen.npcs.size(); i++){
			if(interactionArea.collision(Screen.npcs.get(i))){
				Screen.npcs.get(i).advanceDialouge();
				break;
			} else if(Screen.npcs.get(i).getDPosition() > -1){
				Screen.npcs.get(i).advanceDialouge();
				break;
			}
		}
		checkAttack();
		
	}

	public void checkAttack()
	{
		for(int i = 0; i < Screen.npcs.size(); i++)
		{
			//System.out.println("going through the arraylist");
			//System.out.println(Screen.npcs.get(i).damagable + " " + interactionArea.collision(Screen.npcs.get(i)));
			if( Screen.npcs.get(i).damagable && interactionArea.collision(Screen.npcs.get(i)))
			{
				//System.out.println("?");
				Screen.npcs.get(i).takeDamage(15);
			}
		}
	}
}