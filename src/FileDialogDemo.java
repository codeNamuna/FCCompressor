import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class experiment extends JFrame{
	experiment()
	{
		super("blah");
		MyWindowAdapter adap=new MyWindowAdapter(this);
		addWindowListener(adap);
	}

}

class MyWindowAdapter extends WindowAdapter{
	experiment exp;
	public MyWindowAdapter(experiment exp)
	{
		this.exp=exp;
	}
	public void windowClosing(WindowEvent we)
	{
		exp.setVisible(false);
		
	}
	
}

class FileDialogDemo{
	public static void main(String args[])
	{
		JFrame f=new experiment();
		f.setVisible(true);
		f.setSize(100, 100);
		FileDialog fd=new FileDialog(f,"File Dialog",FileDialog.LOAD);
		String file=fd.getFile();
		JOptionPane.showMessageDialog(null,file);
		fd.setVisible(true);
	}
}
