public class ItemID
{
	private int itemID;
	private String itemName;
	public ItemID(int itemID, String itemName)
	{
		this.itemID = itemID;
		this.itemName = itemName;
	}

	public int getItemID()
	{
		return itemID;
	}

	public String getItemName()
	{
		return itemName;
	}
}