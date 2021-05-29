import javax.swing.JFrame;
import java.awt.Dimension;
import java.*;
public class Runner
{
	public static void main(String args[])
	{
		Screen screen = new Screen();
		JFrame frame = new JFrame("Final Project Semester 2 - Chris Tabaska");
		
		frame.setExtendedState (JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.add(screen);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		screen.animate();
	}
}
