package first_page;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JFrame;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class Exper extends JFrame implements ActionListener 
{
	private String eid;
	
	JPanel panel;
	JButton BUT_ADD = new JButton("Add");
	JLabel[] label_disp = new JLabel[6];
	JTextField[] text_disp = new JTextField[6];
	
	public Exper()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   setTitle("Add Past Experience Details");
	}
	public void set_eid(String s)
	{
		eid = s;
	}
	public void display()
	{
		this.setSize(500, 500);
		panel=new JPanel(new GridLayout(13,1));
		
		int i,j;
		for(i=0;i<6;i++)
		{
			label_disp[i] = new JLabel();
			text_disp[i]= new JTextField(20);
		}
		label_disp[0].setText("Past Designation:");
		
		label_disp[1].setText("Describe your main responsibilities:");
		
		label_disp[2].setText("Name of Company:");
		
		label_disp[3].setText("City:");
		
		label_disp[4].setText("Starting Date (in the format:21-Jun-11) :");
		
		label_disp[5].setText("Ending Date (in the format:21-Jun-11) :");
		
		for(i=0;i<6;i++)
		{
			panel.add(label_disp[i]);
			panel.add(text_disp[i]);
		}
		panel.add(BUT_ADD);
		this.add(panel);
		
		BUT_ADD.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		
		try
		{
		String str = ae.getActionCommand();
		int i;
		
		if(str.equals("Add"))
		{
			String[] value_text = new String[6];
			for(i=0;i<6;i++)
				value_text[i] =  text_disp[i].getText();
			String cmp = value_text[2].toUpperCase();
//insert into dpp_experience values(1,
//(select cid FROM dpp_companies where cname='ORACLE'),'TESTER','TESTING','Kolkata','Y','20-Apr-08','21-Jun-10',
//((select MAX(expid) from dpp_experience)+1));			
			String query = "insert into dpp_experience values("+eid+",";
			query+="(select cid FROM dpp_companies where cname=\'"+cmp+"\'),";
			query+="\'"+value_text[0]+"\',";
			query+="\'"+value_text[1]+"\',";
			query+="\'"+value_text[3]+"\','N',";
			query+="\'"+value_text[4]+"\',";
			query+="\'"+value_text[5]+"\',";
			query+="((select MAX(expid) from dpp_experience)+1))";
			if(sql_insert(query)==true)
			{
				this.setVisible(false);
				JOptionPane.showMessageDialog(this,"Your Experience has been updated","Success",JOptionPane.INFORMATION_MESSAGE);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				HomeEmp page_home_emp=new HomeEmp();
	        	
	        	page_home_emp.set_eid(eid);
	        	
	        	page_home_emp.display();
	        	
	        	page_home_emp.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Sorry, Experience could not be added","Error",JOptionPane.ERROR_MESSAGE);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public static boolean sql_insert(String query)throws SQLException
	{
		boolean b;
	 	int check=0,check1=0;
	 	
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   Connection conn = null;
		   Statement stmt = null, stmt_check;
		   ResultSet rset = null,rset_check;
		   
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		   String check_query = "select count(*) from dpp_experience";
		   
		   //checking for insert success start
		   stmt_check = conn.createStatement();
		   rset_check = stmt_check.executeQuery(check_query);
		   if(rset_check.next())
			   check = rset_check.getInt(1);
		   rset_check.close();
		   stmt_check.close();
		   //checking for insert success end
		   
		   
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery(query);
		   
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery("commit");
		   
		   
		 //checking for insert success start
		   stmt_check = conn.createStatement();
		   rset_check = stmt_check.executeQuery(check_query);
		   if(rset_check.next())
			   check1 = rset_check.getInt(1);
		   rset_check.close();
		   stmt_check.close();
		   
		   if (check1 == (check+1))
			   b= true;
		   else
			   b = false;
		 //checking for insert success end
		   
		   rset.close();
		   
		   
		   
		   stmt.close();
		   conn.close();
		   
		   return b;
	}
}
