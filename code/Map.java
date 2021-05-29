import java.io.*;
import java.util.ArrayList;
import java.awt.*;
public class Map
{
	private BufferedReader reader;
	private ArrayList<String> test = new ArrayList<String>();
	private Tile[][] map1;
	private Tile[][] map2;
	private Tile[][] map3;
	private Tile[][] map4;
	public static Point mapPosition = new Point();
	public static int currentMap = 1;
	public Map()
	{
		System.out.println("Loading Map 1...");
		try {
		    File file = new File("map1.dat");
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    while ((line = reader.readLine()) != null) {
		    	test.add(line);
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

		String[] temp = test.get(0).split(" ");
		map1 = new Tile[test.size()][ temp.length];
		System.out.println("map size: " + map1.length + " " + map1[0].length);
		for(int r = 0; r < map1.length; r++)
		{
			temp = test.get(r).split(" ");
			for (int c = 0; c < map1[r].length ; c++) 
			{
				map1[r][c] = new Tile(c*256, r*256, Integer.parseInt(temp[c]));
			}
		}

		for(Tile[] each: map1){
			for(Tile weach: each){
				System.out.print(weach + " ");
			}
			System.out.println("");
		}


		System.out.println("Loading Map 2...");
		System.out.println("");
		test.clear();
		try {
		    File file = new File("map2.dat");
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    while ((line = reader.readLine()) != null) {
		    	test.add(line);
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

		temp = test.get(0).split(" ");
		map2 = new Tile[test.size()][ temp.length];
		System.out.println("map size: " + map2.length + " " + map2[0].length);
		for(int r = 0; r < map2.length; r++)
		{
			temp = test.get(r).split(" ");
			for (int c = 0; c < map2[r].length ; c++) 
			{
				map2[r][c] = new Tile(c*256, r*256, Integer.parseInt(temp[c]));
			}
		}

		for(Tile[] each: map2){
			for(Tile weach: each){
				System.out.print(weach + " ");
			}
			System.out.println("");
		}


		//map 3
		System.out.println("Loading Map 3...");
		System.out.println("");
		test.clear();
		try {
		    File file = new File("map3.dat");
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    while ((line = reader.readLine()) != null) {
		    	test.add(line);
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

		temp = test.get(0).split(" ");
		map3 = new Tile[test.size()][ temp.length];
		System.out.println("map size: " + map3.length + " " + map3[0].length);
		for(int r = 0; r < map3.length; r++)
		{
			temp = test.get(r).split(" ");
			for (int c = 0; c < map3[r].length ; c++) 
			{
				map3[r][c] = new Tile(c*256, r*256, Integer.parseInt(temp[c]));
			}
		}

		for(Tile[] each: map3){
			for(Tile weach: each){
				System.out.print(weach + " ");
			}
			System.out.println("");
		}


		//Map 4
		System.out.println("Loading Map 4...");
		System.out.println("");
		test.clear();
		try {
		    File file = new File("map4.dat");
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    while ((line = reader.readLine()) != null) {
		    	test.add(line);
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

		temp = test.get(0).split(" ");
		map4 = new Tile[test.size()][ temp.length];
		System.out.println("map size: " + map4.length + " " + map4[0].length);
		for(int r = 0; r < map4.length; r++)
		{
			temp = test.get(r).split(" ");
			for (int c = 0; c < map4[r].length ; c++) 
			{
				map4[r][c] = new Tile(c*256, r*256, Integer.parseInt(temp[c]));
			}
		}

		for(Tile[] each: map4){
			for(Tile weach: each){
				System.out.print(weach + " ");
			}
			System.out.println("");
		}
	}

	public void drawMe(Graphics2D g)
	{
		if(currentMap == 1){
			for(Tile[] each: map1){
				for(Tile weach: each){
					weach.drawMe(g);
				}
			}
		} else if(currentMap == 2){
			for(Tile[] each: map2){
				for(Tile weach: each){
					weach.drawMe(g);
				}
			}
		}
	}

	public void move()
	{
		mapPosition.setLocation(Screen.screenX, Screen.screenY);
	}

	public static void changeMap(int nextMap){
		System.out.println("doing it?");
		Screen.screenX = 0;
		Screen.screenY = 0;
		currentMap++;
	}

	// public static boolean checkFutureCollsionUp()
	// {
	// 	for(int r = 0; r < map1.length; r++)
	// 	{
	// 		for (int c = 0; c < map1[r].length ; c++) 
	// 		{
	// 			if(map1[r][c].obstacleType == 1)
	// 			{
					
	// 			}
	// 		}
	// 	}
	// }
	// public static boolean checkFutureCollsionDown()
	// {
	// 	for(int r = 0; r < map1.length; r++)
	// 	{
	// 		for (int c = 0; c < map1[r].length ; c++) 
	// 		{
	// 			if(map1[r][c].obstacleType == 1)
	// 			{
					
	// 			}
	// 		}
	// 	}
	// }
	// public static boolean checkFutureCollsionLeft()
	// {
	// 	for(int r = 0; r < map1.length; r++)
	// 	{
	// 		for (int c = 0; c < map1[r].length ; c++) 
	// 		{
	// 			if(map1[r][c].obstacleType == 1)
	// 			{
					
	// 			}
	// 		}
	// 	}
	// }
	// public static boolean checkFutureCollsionRight()
	// {
	// 	for(int r = 0; r < map1.length; r++)
	// 	{
	// 		for (int c = 0; c < map1[r].length ; c++) 
	// 		{
	// 			if(map1[r][c].obstacleType == 1)
	// 			{
					
	// 			}
	// 		}
	// 	}
	// }
}