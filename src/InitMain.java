import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
public class InitMain extends WindowMain{
	
	private Buttons b1;
	private Buttons b2;
	private Buttons clb1;
	private Buttons clb2;
	public String st;
	public String dt;
	public String dt2;
	public String sr;

	InitMain()throws IOException
	{
		add(new panel1()).setBounds(0,0,800,500);
		repaint();
		MyWindowAdapter adap=new MyWindowAdapter(this);
		addWindowListener(adap);
		st=" ";
		dt=" ";
	}	
	
	class panel1 extends JPanel{
		
		private Image image;
		private ImageIcon icon;
		panel1()throws IOException
		{

			try{
				icon= new ImageIcon(ImageIO.read(getClass().getResource("/images/tree2.png")));
				image= icon.getImage();
				repaint();

				b1=new Buttons(ImageIO.read(getClass().getResource("/images/close2.png")),ImageIO.read(getClass().getResource("/images/close3.png")));
				b2=new Buttons(ImageIO.read(getClass().getResource("/images/bbutton1.png")),ImageIO.read(getClass().getResource("/images/bbutton2.png")));
				
				}catch(IOException e){
					e.printStackTrace();
				}
			setLayout(null);
			setSize(800,500);
			
			add(b1).setBounds(755,10, 50, 50);
			add(b2).setBounds(595,390, 200, 100);
			b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			});
			
			b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					try{
					InitMain.this.remove(panel1.this);
					InitMain.this.setContentPane(new panel2());
					}catch(IOException ioex)
					{
						ioex.printStackTrace();
					}
					}
			});

		}
		
		protected void paintComponent(Graphics bg)
		{
			super.paintComponent(bg);
			Graphics2D g2d=(Graphics2D)bg;
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
			g2d.drawImage(image, 0, 0, 800,500,this);
		}
	}
	
	class panel2 extends JPanel{
		panel2()throws IOException
		{
			this.setLayout(null);
			this.setSize(800,500);
			repaint();
			try{
				b1=new Buttons(ImageIO.read(getClass().getResource("/images/close2.png")),ImageIO.read(getClass().getResource("/images/close3.png")));
				clb1=new Buttons(ImageIO.read(getClass().getResource("/images/ficon2.png")),ImageIO.read(getClass().getResource("/images/ficon1.png")));
				clb2=new Buttons(ImageIO.read(getClass().getResource("/images/ficon2.png")),ImageIO.read(getClass().getResource("/images/ficon1.png")));
				b2=new Buttons(ImageIO.read(getClass().getResource("/images/cmpbut2.png")),ImageIO.read(getClass().getResource("/images/cmpbut1.png")));

			}catch(IOException e)
			{
				e.printStackTrace();
			}
			
			add(b1).setBounds(740,20, 50, 50);
			add(clb1).setBounds(700,160, 45, 45);
			add(clb2).setBounds(700,262,45,45);
			add(b2).setBounds(430,340, 170, 50);
			
			b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			});
			
			clb1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					MyFileDialog dialog=new MyFileDialog(0);
					st=stripper(dialog.fname());
					dt=stripper(dialog.dname().concat("CopyOf".concat(st)));
					dt2=stripper(dialog.dname());
					sr=st;
				}
			});
			
			clb2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					MyFileDialog dialog2=new MyFileDialog(0);
				//	dt=stripper(dialog2.dname().concat("CopyOf".concat(st)));
				//	dt2=stripper(dialog2.dname());
				}
			});
			
			b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					if(st==null||dt==null||st==" "||dt==" ")
					{
						JOptionPane.showMessageDialog(null,"Please Specify both Parameters");
					}
					
					else
					{
					try{
						InitMain.this.remove(panel2.this);
						InitMain.this.setContentPane(new panel3());	
					}catch(IOException ioex)
					{
						ioex.printStackTrace();
					}

					}
				}
			});
			
		}
		
		public String stripper(String s)
		{
			char a[]=new char[s.length()];
			for(int i=0;i<s.length();i++)
			{
				if(s.charAt(i)=='\\')
					{
							a[i]='/';
					}
				else
					a[i]=s.charAt(i);
			}
			return new String(a); 
		}
		
		public String trimmer(String st)
		{
			String st2;
			if(st.length()>24)
			{
				char a[]=new char[30];
				int t=0;
				for(int i=0;i<5;i++)
				{
					a[t++]=st.charAt(i);
				}
				for(;t<9;t++)
				{
					a[t]='.';
				}
				for(int i=st.length()-7;i<st.length();i++)
				{
					a[t++]=st.charAt(i);
				}
				st2=new String(a);
			}
			else
			{
				st2=st;
			}
			
			return st2;
			
		}
		
		protected void paintComponent(Graphics bg)
		{
			super.paintComponent(bg);
			Graphics2D g2d=(Graphics2D)bg;
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
			try{
			g2d.drawImage(new ImageIcon(ImageIO.read(getClass().getResource("/images/collage.png"))).getImage(), 0, 0, 800,500,this);
			}catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(new Color(0,0,0));
			g2d.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
			g2d.drawString(trimmer(st),460,188);
			g2d.drawString(trimmer(dt),480,290);
		}
	}
	
	class panel3 extends JPanel implements Runnable{
		private int i;
		Thread t;
		panel3()throws IOException
		{
			t=new Thread(this);
			this.setLayout(null);
			this.setSize(800,500);
			ImgToBase64DIRECT im = new ImgToBase64DIRECT();
			//im.compress_main("C:\\Users\\Shivang Singh\\Desktop\\jcs.jpg","C:\\Users\\Shivang Singh\\Desktop\\","C:\\Users\\Shivang Singh\\Desktop\\copyjcs.jpg");
			im.compress_main(dt2.concat(st), dt2, dt);
			t.start();
		}
		
		public void run()
		{
			try{
				for(int j=0;j<12;j++)
				{
					i=j%3;
					repaint();
					Thread.sleep(300);
				}
				
				InitMain.this.remove(panel3.this);	//888888888888888888888888888888888888888888888Switching Mech
				InitMain.this.setContentPane(new panel4());
				
			}catch(InterruptedException e)
				{
					e.printStackTrace();
				}
		}
		
		protected void paintComponent(Graphics bg)
		{
			super.paintComponent(bg);
			Graphics2D g2d=(Graphics2D)bg;
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
			try{
			g2d.drawImage(new ImageIcon(ImageIO.read(getClass().getResource("/images/collage3.png"))).getImage(), 0, 0, 800,500,this);
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
			switch(i)
			{
				case 0:
					g2d.drawImage(new ImageIcon(ImageIO.read(getClass().getResource("/images/can11.png"))).getImage(), 600, 300, 200,200,this);	
					break;
				
				case 1:
					g2d.drawImage(new ImageIcon(ImageIO.read(getClass().getResource("/images/can22.png"))).getImage(), 600, 300, 200,200,this);	
					break;
				
				case 2:
					g2d.drawImage(new ImageIcon(ImageIO.read(getClass().getResource("/images/can33.png"))).getImage(), 600, 300, 200,200,this);	
					break;	
			}
			}catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
			
		}
			
	}
	
	class panel4 extends JPanel{
		panel4()
		{
			this.setLayout(null);
			this.setSize(800,500);
			repaint();
			try{
				
				b1=new Buttons(ImageIO.read(getClass().getResource("/images/close2.png")),ImageIO.read(getClass().getResource("/images/close3.png")));
				b2=new Buttons(ImageIO.read(getClass().getResource("/images/blogo1.png")),ImageIO.read(getClass().getResource("/images/blogo2.png")));
				
			}catch(IOException e)
			{
				e.printStackTrace();
			}
			
			add(b1).setBounds(740,20, 50, 50);
			add(b2).setBounds(690,20, 37, 37);
			b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			});
			
			b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					try{
					st=" ";
					dt=" ";
					dt2=" ";
					InitMain.this.remove(panel4.this);
					InitMain.this.setContentPane(new panel2());
					}catch(IOException ioex)
					{
						ioex.printStackTrace();
					}
				}
			});
		}
		
		protected void paintComponent(Graphics bg)
		{
			super.paintComponent(bg);
			Graphics2D g2d=(Graphics2D)bg;
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
			try{
			g2d.drawImage(new ImageIcon(ImageIO.read(getClass().getResource("/images/collage4.png"))).getImage(), 0, 0, 800,500,this);
			}catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
	
	class MyWindowAdapter extends WindowAdapter{
		InitMain init;
		public MyWindowAdapter(InitMain init)
		{
			this.init=init;
		}
		public void windowClosing(WindowEvent we)
		{
			init.setVisible(false);
			
		}
		
	}

	class MyFileDialog{
		public String file;
		public String dir;
		FileDialog fd;
		MyFileDialog(int i)
		{
			JFrame f=InitMain.this;
			f.setOpacity(0.8f);
			if(i==0)
				fd=new FileDialog(f,"Browse Images",FileDialog.LOAD);
			else
				fd=new FileDialog(f,"Browse Images",FileDialog.SAVE);
			fd.setLocation(f.getX(),f.getY());
			fd.setVisible(true);
			file=fd.getFile();
			dir=fd.getDirectory();
			f.setOpacity(1.0f);
		}
		
		String fname()
		{
			return file;
		}
		
		String dname()
		{
			return dir;
		}
	}
	
}

