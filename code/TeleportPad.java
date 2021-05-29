import java.awt.*;
public class TeleportPad extends MainObject
{
	private int type;
	public TeleportPad(int x, int y)
	{
		super(x,y,256,256);
		this.type = type;
		
	}

	public void drawMe(Graphics2D g)
	{
		g.setColor(Color.gray);
		g.fillRect((int)Map.mapPosition.getX() + (int)x,(int)Map.mapPosition.getY() +(int)y,(int)width,(int)height);
		if(Screen.p.collision(new MainObject((double)Map.mapPosition.getX() + (double)x,(double)Map.mapPosition.getY() +(int)y,(double)width,(double)height))){
			Map.changeMap(2);
			Screen.quests.get(0).objectives[2].completeObjective();
		}
	}


}