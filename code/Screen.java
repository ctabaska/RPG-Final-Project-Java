import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.text.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;


public class Screen extends JPanel implements MouseListener, MouseMotionListener{
	
	Action[] actions = new Action[18];
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static int screenWidth;
	public static int screenHeight;

	public static int screenX = 0;
	public static int screenY = 0;
	public static int scaleGUI = 4;

	public static boolean s;
	public static boolean w;
	public static boolean a;
	public static boolean d;
	public static boolean i;
	public static boolean e;
	public static boolean f;
	public static boolean up;
	public static boolean down;
	public static boolean mouseRightDown;
	public static boolean mouseDown;
	public static int mouseX;
	public static int mouseY;
	public static ButtonMenu escMenu;
	public static boolean esc;

	public Inventory inv;
	public Background bg;
	public static Player p;
	public static MainObject mouseMainObject = new MainObject(0,0,0,0);
	//public static NPC npctest = new NPC(50,50);
	public static ArrayList<NPC> npcs = new ArrayList<NPC>();
	public static ArrayList<Item> availableItems = new ArrayList<Item>();
	public static Dialouge dialouge;
	public static Map map = new Map();

	private StartScreen ss = new StartScreen();
	public static boolean startDone = false;
	public ItemIDs iid = new ItemIDs();

	public static int time = 0;
	private Color blackToClear = new Color(0f, 0f, 0f, 1f);

	private boolean actionSetup = false;

	public static ArrayList<Quest> quests = new ArrayList<Quest>();

	public static int currentQuest = 0;

	public Screen(){
		this.setLayout(null);


		screenWidth = (int)screenSize.getWidth();
		screenHeight = (int)screenSize.getHeight();
		String[] quitMenuLabels = {"Return to Game", "Options", "Quit"};
		escMenu = new ButtonMenu(screenWidth * 0.35, screenHeight * 0.3, screenWidth * 0.3, screenHeight * 0.4, quitMenuLabels);
		bg = new Background();
		inv = new Inventory();
		setKeyListenerStartMenu();
		String[] npcStrings = {"Are you okay?", "Those savages have been roaming these woods for as long as I can remember", 
			"You could really use some time to get better, follow me to my village and we can help you.", 
			"Here, take this apple it will help you feel better",
			"It looks like one of the savages dropped a sword, if you're going to survive on this world you're going to need that sword",
			"Our village is just to the east follow me"};
		npcs.add(new Guide(-60,screenHeight/ 2, npcStrings));
		p = new Player();
		npcs.add(new Savage(screenWidth/2 - 100, 200, 1, true));
		npcs.add(new Savage(screenWidth/2, 200, 1));
		npcs.add(new Savage(screenWidth/2 + 100, 200, 1));
		String[] merchantStrings = {"Come to check out what I have in stock?"};
		npcs.add(new Merchant(1000, 1000, merchantStrings));
		dialouge = new Dialouge();
		quests.add(new Quest1());

		addMouseListener(this);
		addMouseMotionListener(this);
		this.setFocusable(true);
	}
	
	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(screenWidth,screenHeight);
	}

	public void paintComponent(Graphics gTemp)
	{	
		Graphics2D g = (Graphics2D) gTemp;
		super.paintComponent(g);
		if(startDone)
		{
			//bg.drawBackground(g);
			g.setColor(Color.black);
			g.fillRect(0,0,screenWidth,screenHeight);
			map.drawMe(g);
			//npctest.drawMe(g);
			for (int i = 0 ; i < npcs.size() ; i++ ) {
				npcs.get(i).drawMe(g);
				npcs.get(i).drawHealth(g);
			}
			for (int i = 0; i < availableItems.size(); i++ ) {
				availableItems.get(i).drawMe(g);
			}
			p.draw(g);
			
			if(i){
				inv.drawMe(g);
			}
			
			quests.get(currentQuest).drawQuest(g);
			
			dialouge.drawMe(g);
			if(time < 100){
				g.setColor(blackToClear);
				g.fillRect(0,0, screenWidth, screenHeight);
				//g.setColor(Color.black);
				//g.fillRect(0,0, 600, 400);
			}
			escMenu.drawMe(g);

		} else {
			ss.drawMe(g);
		}
		//g.drawRect(0,screenHeight/2, screenWidth, 1);
		
	}
	public void mousePressed(MouseEvent e){
		if(SwingUtilities.isRightMouseButton(e))
		{
			mouseRightDown = true;
		}
		else
		{
			mouseDown = true;
		}	
	}
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseReleased(MouseEvent e){
		mouseDown = false;
		mouseRightDown = false;
	}
	public void mouseMoved(MouseEvent e){
		mouseY = e.getY();
		mouseX = e.getX();
		mouseMainObject.x = mouseX;
		mouseMainObject.y = mouseY;
	}
	public void mouseDragged(MouseEvent e){
		mouseY = e.getY();
		mouseX = e.getX();
		mouseMainObject.x = mouseX;
		mouseMainObject.y = mouseY;
	}

	public void setKeyListenerStartMenu()
	{
		actions[10] = new SpaceDownI();
		this.getInputMap().put( KeyStroke.getKeyStroke( "SPACE" ), "doSpaceDown" );
		this.getActionMap().put( "doSpaceDown", actions[10] );
	}

	static class SpaceDownI extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			startDone = true;

		}}

	public void setKeyListener()
	{
		
		actions[0] = new EscDown();
		this.getInputMap().put( KeyStroke.getKeyStroke( "ESCAPE" ), "doEscapeDown" );
		this.getActionMap().put( "doEscapeDown", actions[0] );
		actions[1] = new EscUp();
		this.getInputMap().put( KeyStroke.getKeyStroke( "released ESCAPE"), "doEscapeUp" );
		this.getActionMap().put( "doEscapeUp", actions[1] ); 
		actions[2] = new WUp();
		this.getInputMap().put( KeyStroke.getKeyStroke( "W"), "doWUp" );
		this.getActionMap().put( "doWUp", actions[2] );
		actions[3] = new WDown();
		this.getInputMap().put( KeyStroke.getKeyStroke( "released W"), "doWDown" );
		this.getActionMap().put( "doWDown", actions[3] );
		actions[4] = new SUp();
		this.getInputMap().put( KeyStroke.getKeyStroke( "S"), "doSUp" );
		this.getActionMap().put( "doSUp", actions[4] );
		actions[5] = new SDown();
		this.getInputMap().put( KeyStroke.getKeyStroke( "released S"), "doSDown" );
		this.getActionMap().put( "doSDown", actions[5] );
		actions[6] = new AUp();
		this.getInputMap().put( KeyStroke.getKeyStroke( "A"), "doAUp" );
		this.getActionMap().put( "doAUp", actions[6] );
		actions[7] = new ADown();
		this.getInputMap().put( KeyStroke.getKeyStroke( "released A"), "doADown" );
		this.getActionMap().put( "doADown", actions[7] );
		actions[8] = new DUp();
		this.getInputMap().put( KeyStroke.getKeyStroke( "D"), "doDUp" );
		this.getActionMap().put( "doDUp", actions[8] );
		actions[9] = new DDown();
		this.getInputMap().put( KeyStroke.getKeyStroke( "released D"), "doDDown" );
		this.getActionMap().put( "doDDown", actions[9] );
		actions[10] = new SpaceDown();
		this.getInputMap().put( KeyStroke.getKeyStroke( "SPACE" ), "doSpaceDown" );
		this.getActionMap().put( "doSpaceDown", actions[10] );
		actions[11] = new UPUp();
		this.getInputMap().put( KeyStroke.getKeyStroke( "UP"), "doUPUp" );
		this.getActionMap().put( "doUPUp", actions[11] );
		actions[12] = new UPDown();
		this.getInputMap().put( KeyStroke.getKeyStroke( "released UP"), "doUPDown" );
		this.getActionMap().put( "doUPDown", actions[12] );
		actions[13] = new DOWNUp();
		this.getInputMap().put( KeyStroke.getKeyStroke( "DOWN"), "doDOWNUp" );
		this.getActionMap().put( "doDOWNUp", actions[13] );
		actions[14] = new DOWNDown();
		this.getInputMap().put( KeyStroke.getKeyStroke( "released DOWN"), "doDOWNDown" );
		this.getActionMap().put( "doDOWNDown", actions[14] );
		actions[15] = new IDown();
		this.getInputMap().put( KeyStroke.getKeyStroke( "released I"), "doIDown" );
		this.getActionMap().put( "doIDown", actions[15] );

		actions[16] = new EDown();
		this.getInputMap().put( KeyStroke.getKeyStroke( "released E"), "doEDown" );
		this.getActionMap().put( "doEDown", actions[16] );

		actions[17] = new FDown();
		this.getInputMap().put( KeyStroke.getKeyStroke( "released F"), "doFDown" );
		this.getActionMap().put( "doFDown", actions[17] );

		actionSetup = true;
		 //Setting up all the classes for the keybindings
	}
	static class EscDown extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			esc = true;
			escMenu.toggleVisible();// toggles visibility of escape menu
		}}
	static class EscUp extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			esc = false;
		}}
	static class WUp extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			w = true;
		}}
	static class WDown extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			w = false;
		}}
	static class SUp extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			s = true;
		}}
	static class SDown extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			s = false;
		}}
	static class AUp extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			a = true;
		}}
	static class ADown extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			a = false;
		}}
	static class DUp extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			d = true;
		}}
	static class DDown extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			d = false;
		}}
	static class SpaceDown extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			p.checkInteraction();

		}}
	static class UPUp extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			up = true;
		}}
	static class UPDown extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			up = false;
		}}
	static class DOWNUp extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			down = true;
		}}
	static class DOWNDown extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			down = false;
		}}
	static class IDown extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			i = !i;
		}}
	static class EDown extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			p.checkPickup();
		}}
	static class FDown extends AbstractAction{ public void actionPerformed( ActionEvent tf ){
			p.checkInteraction();
		}}


	public static void menuButtonClick(String button){ //menu button actions
		/* Example
		if(button.equals(<Button Label>)){
			<action>
		}
		*/
		if(button.equals("Quit")){
			Screen.closeWindow();
		}else if(button.equals("Return to Game")){
			escMenu.toggleVisible();
		} else if(button.equals("Options")){
			escMenu.toggleVisible();
		}
	}

	public static void closeWindow(){ // closes the window
		System.exit(0);
	}
	



	public void animate(){ 
		while(true){
			//wait for .01 second
			try {
			    Thread.sleep(16);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			if(startDone){
				time++;
				if(time < 100){
					float transpa = (float)(((double)100 - time)/ (double)100.0);
					blackToClear = new Color(0f, 0f, 0f, transpa);
				} else if(!actionSetup){
					setKeyListener();
				}
				p.move();
				bg.move();
				map.move();
				for (int i = 0; i < npcs.size() ; i++ ) {
					npcs.get(i).move();
				}
				for (int i = 0; i < availableItems.size() ; i++ ) {
					availableItems.get(i).move();
				}

				if(up){
					p.changeSpeed(0.1);
				}else if(down){
					p.changeSpeed(-0.1);
				}
			}
			//repaint the graphics drawn
			repaint();
		}
	}
	
}
