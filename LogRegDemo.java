package first_page;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class LogRegDemo
{
	public static void main(String arg[])
	{
		try
		{
		LogReg frame=new LogReg();
		frame.setSize(500,440);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	catch(Exception e)
		{JOptionPane.showMessageDialog(null, e.getMessage());}
	}
}
