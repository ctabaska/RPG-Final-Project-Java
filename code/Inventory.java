import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Inventory
{
	public static boolean visible = true;
	public static Item[] inventory = new Item[10];
	private BufferedImage invBox;
	public Inventory()
	{
		try{
			invBox = ImageIO.read(new File("images/invBox.png"));
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void drawMe(Graphics2D g)
	{
		g.drawImage(invBox, 20, 65, null);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("Inventory", 10, 60);
		for (int r = 0; r < inventory.length ; r++ ){
			if(inventory[r] != null){
				inventory[r].drawMe(g);
			}
		}			
	}

	public static void addItem(Item newItem)
	{
		for (int r = 0; r < inventory.length ; r++ ){
			if(inventory[r] == null){
				inventory[r] = newItem;
				inventory[r].x = 20;
				inventory[r].y = 70 + 70 * r;
				break;
			} else if(inventory[r].getItemID() == newItem.getItemID()){
				inventory[r].increaseAmount(newItem.getAmount());
				break;
			}
		}
	}

	public static boolean checkFull()
	{
		for (int r = 0; r < inventory.length ; r++ ){
			if(inventory[r] == null){
				return false;
			}
		}
		return true;
	}

	public static boolean checkForItem(int itemID)
	{
		for (int r = 0; r < inventory.length ; r++ ){
			//System.out.println(inventory[r]);
			if(inventory[r] != null){
				//System.out.println("slot is not empty");
				if(inventory[r].getItemID() == itemID){
					//System.out.println("does it even return true");
					return true;
				}
				//System.out.println(inventory[r].getItemID() + " " + itemID);
			} 
		}
		return false;
	}

	public static boolean checkForItem(String itemName)
	{
		for (int r = 0; r < inventory.length ; r++ ){
			//System.out.println(inventory[r]);
			if(inventory[r] != null){
				//System.out.println("slot is not empty");
				if(inventory[r].getItemName().equals( itemName)){
					//System.out.println("does it even return true");
					return true;
				}
				//System.out.println(inventory[r].getItemName() + " " + itemName);
			} 
		}
		return false;
	}

	public static boolean checkEmpty()
	{
		for (int r = 0; r < inventory.length ; r++ ){
			if(inventory[r] != null){
				return true;
			}
		}
		return false;
	}
}