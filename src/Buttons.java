import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Buttons extends JButton{
	private Image image;
	private ImageIcon icon;
	
	Buttons(final BufferedImage path,final BufferedImage hoverPath){
		icon= new ImageIcon(path);
		image= icon.getImage();
		setContentAreaFilled(false);
		setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		
		this.addMouseListener(new MouseAdapter(){

			public void mouseEntered(MouseEvent e) {
				icon= new ImageIcon(hoverPath);
				image= icon.getImage();
				repaint();
			}

			public void mouseExited(MouseEvent e) {
				icon= new ImageIcon(path);
				image= icon.getImage();
				repaint();
			}
		});
		repaint();
	}
	
	protected void paintComponent(Graphics g){	
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2d.drawImage(image, 0, 0, getWidth(),getHeight(),this);
	}
}
