package first_page;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class ViewJobs extends JFrame implements ActionListener 
{
	private int counter; 
	private String[] qry_rs;
	private String eid;
	
	
	JPanel panel;
	JLabel[] label_srch_rs;
	JButton[] BUT_VIEW;
	ViewJobs()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setTitle("Search Results");
       
	}
	public void set_counter(int i)
	{
		counter=i;
	}
	public void set_eid(String s)
	{
		eid=s;
	}
	public void set_qry_rs(String[] s)
	{
		qry_rs=s;
	}
	public void display()
	{
		this.setSize(500, (100*counter));
		panel=new JPanel(new GridLayout((3*counter),1));
		
		label_srch_rs = new JLabel[2*counter];
		BUT_VIEW = new JButton[counter];
		
		int i;
		for (i=0;i<counter;i++)
		{
			label_srch_rs[(2*i)] = new JLabel();
			label_srch_rs[((2*i)+1)] = new JLabel();
			
			label_srch_rs[(2*i)].setText("          ");
			label_srch_rs[((2*i)+1)].setText(qry_rs[i].substring((qry_rs[i].indexOf("_")+1)));
			
			BUT_VIEW[i] = new JButton("View Opening "+(i+1));
			
			panel.add(label_srch_rs[(2*i)]);
			panel.add(label_srch_rs[((2*i)+1)]);
			panel.add(BUT_VIEW[i]);

			BUT_VIEW[i].addActionListener(this);
		}

		this.add(panel);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
		int i;
		for(i=0;i<counter;i++)
		{
			if(str.equals("View Opening "+(i+1)))
			{
				OpDetails od = new OpDetails();
				od.set_eid(eid);
				od.set_openid(qry_rs[i].substring(0,qry_rs[i].indexOf("_")));
				
				od.display();
	        	
	        	od.setVisible(true);
			}
					
		}
		
	}
}
