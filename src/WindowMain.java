import javax.swing.JFrame;
import java.awt.event.*;
public class WindowMain extends JFrame{
	private int x;
	private int y;
	WindowMain()
	{
			super("The Compressor");
			this.x=getX();
			this.y=getY();
			this.setUndecorated(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.setVisible(true);
			this.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					x=e.getX();
					y=e.getY();
				}
			});
				
			this.addMouseMotionListener(new MouseAdapter(){
				public void mouseDragged(MouseEvent e) {
					setLocation(e.getXOnScreen()-x,e.getYOnScreen()-y);
				}
			});
	
	}
	
}
