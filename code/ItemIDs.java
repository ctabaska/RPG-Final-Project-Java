import java.io.*;
import java.util.ArrayList;
public class ItemIDs
{
	private BufferedReader reader;
	public static ArrayList<ItemID> itemIDs = new ArrayList<ItemID>();
	public ItemIDs()
	{
		try {
		    File file = new File("itemIDs.dat");
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    while ((line = reader.readLine()) != null) {
		    	itemIDs.add(new ItemID(Integer.parseInt(line.substring(0, line.indexOf(' '))), line.substring(line.indexOf(' ')+1, line.length())));
		    	System.out.println(Integer.parseInt(line.substring(0, line.indexOf(' '))));
		    }
		    reader.close();

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	}
}