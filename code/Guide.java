import java.awt.Graphics2D;
public class Guide extends NPC
{
	private boolean giveApple = false;
	private boolean checkPlayer = false;
	public Guide(int x, int y, String[] dialouge)
	{
		super(x, y, dialouge);
		super.y = (int)((Screen.screenHeight - height)/2.0);
		super.initialY = (int)((Screen.screenHeight - height)/2.0);
		super.damagable = false;

	}


	@Override
	public void drawMe(Graphics2D g)
	{
		g.drawImage(npc[4], (int)x, (int)y, null);
		if(!checkPlayer){
			if(initialX < Screen.screenWidth * 0.4){
				//System.out.println("running?");
				initialX+=5;
			} else{
				advanceDialouge();
				checkPlayer = true;
			}
		}
	}

	@Override
	public void advanceDialouge()
	{
		if(!giveApple && dialougePos + 1 < dialouge.length){
			dialougePos++;
			Dialouge.nextLine(dialouge[dialougePos]);
			if(!giveApple && dialougePos == 3 ){
				Screen.availableItems.add(new Item(0, false, (int)initialX + (int)(width/2) +  50, (int)initialY));
				giveApple = true;
			}
		} else if(Screen.quests.get(0).objectives[0].complete && dialougePos < 4){
			dialougePos = 4;
			Dialouge.nextLine(dialouge[dialougePos]);
			dialougePos = 5;
		} else if(Screen.quests.get(0).objectives[1].complete && dialougePos == 5){
			Dialouge.nextLine(dialouge[dialougePos]);
			dialougePos = 6;
		}
		else{
			Dialouge.nextLine("");
			dialougePos = -1;
		}
	}
}