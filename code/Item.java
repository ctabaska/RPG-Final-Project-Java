import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Item extends MainObject
{
	private int amount;
	private int itemID;
	private String itemName;
	private boolean inInventory;
	private int initialX;
	private int initialY;
	private BufferedImage[] itemImgTemps = new BufferedImage[2];
	private Image[] itemImg = new Image[2];
	public Item(int itemID, boolean inInventory)
	{
		super(0,0,0,0);
		initialX = 0;
		initialY = 0;
		try{
			itemImgTemps[0] = ImageIO.read(new File("images/apple.png"));
			itemImgTemps[1] = ImageIO.read(new File("images/swordRight.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		for (int i = 0; i < itemImgTemps.length ; i++ ) {
			width = itemImgTemps[i].getWidth() * Screen.scaleGUI;
			height = itemImgTemps[i].getHeight() * Screen.scaleGUI;
			itemImg[i] = itemImgTemps[i].getScaledInstance( (int)width, (int)height, Image.SCALE_FAST);
		}

		this.amount = 1;
		this.inInventory = inInventory;
		this.itemID = itemID;

		this.itemName = ItemIDs.itemIDs.get(itemID).getItemName();
		System.out.println("Item created, ItemID: " + itemID + " ItemName: " + itemName);
	}
	public Item(int itemID, boolean inInventory, int x, int y)
	{
		super(x,y,0,0);
		initialX = x;
		initialY = y;
		try{
			itemImgTemps[0] = ImageIO.read(new File("images/apple.png"));
			itemImgTemps[1] = ImageIO.read(new File("images/swordRight.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		for (int i = 0; i < itemImgTemps.length ; i++ ) {
			width = itemImgTemps[i].getWidth() * Screen.scaleGUI;
			height = itemImgTemps[i].getHeight() * Screen.scaleGUI;
			itemImg[i] = itemImgTemps[i].getScaledInstance( (int)width, (int)height, Image.SCALE_FAST);
		}
		this.amount = 1;
		this.inInventory = inInventory;
		this.itemID = itemID;
		this.itemName = ItemIDs.itemIDs.get(itemID).getItemName();
		System.out.println("Item created, ItemID: " + itemID + " ItemName: " + itemName);
	}

	//public Item()

	public void drawMe(Graphics2D g)
	{
		g.drawImage(itemImg[itemID], (int)x, (int)y, null);
		if(inInventory)
		{
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 14));
			g.drawString(itemName + " " + amount, (int)(x) + 60, (int)y + 30);
		}
		
	}

	public int getItemID(){
		return itemID;
	}

	public String getItemName()
	{
		return itemName;
	}

	public void increaseAmount(int addition)
	{
		amount += addition;
	}

	public int getAmount()
	{
		return amount;
	}
	public void move()
	{
		if(!fixed)
		{
			x = initialX + Screen.screenX;
			y = initialY + Screen.screenY;
		}
	}
}