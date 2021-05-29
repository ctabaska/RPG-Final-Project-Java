import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class NPC extends MainObject
{
	public BufferedImage[] npcTemp = new BufferedImage[5];
	public Image[] npc = new Image[5];
	private BufferedImage tree;
	public String[] dialouge;
	public int dialougePos;
	private boolean fixed = false;
	public int initialX;
	public  int initialY;
	public boolean damagable;

	public int health;
	public int totalHealth;


	public NPC(int x, int y, String[] dialouge)
	{
		super(x, y, 50, 50);
		this.initialX = x;
		this.initialY = y;
		this.health = 100;
		this.totalHealth = 100;
		try{
			npcTemp[0] = ImageIO.read(new File("images/skullKid.png"));
			npcTemp[1] = ImageIO.read(new File("images/skullKid1.png"));
			npcTemp[2] = ImageIO.read(new File("images/skullKid2.png"));
			npcTemp[3] = ImageIO.read(new File("images/skullKid3.png"));
			npcTemp[4] = ImageIO.read(new File("images/RobeGuy.png"));
			//tree = ImageIO.read(new File("images/Tree.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}

		
		for (int i = 0; i < npcTemp.length ; i++ ) {
			width = npcTemp[i].getWidth() * Screen.scaleGUI;
			height = npcTemp[i].getHeight() * Screen.scaleGUI;
			npc[i] = npcTemp[i].getScaledInstance( (int)width, (int)height, Image.SCALE_FAST);
		}
		this.dialougePos = -1;
		this.dialouge = dialouge;
	}
	public NPC(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		this.initialX = x;
		this.initialY = y;
		this.health = 100;
		this.totalHealth = 100;
		try{
			npcTemp[0] = ImageIO.read(new File("images/skullKid.png"));
			npcTemp[1] = ImageIO.read(new File("images/skullKid1.png"));
			npcTemp[2] = ImageIO.read(new File("images/skullKid2.png"));
			npcTemp[3] = ImageIO.read(new File("images/skullKid3.png"));
			npcTemp[4] = ImageIO.read(new File("images/RobeGuy.png"));
			//tree = ImageIO.read(new File("images/Tree.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}

		
		for (int i = 0; i < npcTemp.length ; i++ ) {
			width = npcTemp[i].getWidth() * Screen.scaleGUI;
			height = npcTemp[i].getHeight() * Screen.scaleGUI;
			npc[i] = npcTemp[i].getScaledInstance( (int)width, (int)height, Image.SCALE_FAST);
		}
		this.dialougePos = -1;
		//this.dialouge = dialouge;
	}

	public void drawMe(Graphics2D g)
	{
		g.drawImage(npc[0], (int)x, (int)y, null);
	}

	public void drawHealth(Graphics2D g)
	{
		if(health / totalHealth != 1)
		{
			g.fillRect((int)x, (int)(y - height/10), (int)width, (int)(height/10));
		}
	}

	public void advanceDialouge()
	{
		if(dialougePos + 1 < dialouge.length){
			dialougePos++;
			Dialouge.nextLine(dialouge[dialougePos]);
		}
		else{
			Dialouge.nextLine("");
			dialougePos = -1;
		}
	}

	public int getDPosition()
	{
		return dialougePos;
	}

	public void move()
	{
		if(!fixed)
		{
			x = initialX + Screen.screenX;
			y = initialY + Screen.screenY;
		}
	}

	public double getX()
	{
		return x;
	}

	public void takeDamage(int damage)
	{
		this.health -= damage;
	}
}