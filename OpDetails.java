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

public class OpDetails extends JFrame implements ActionListener 
{
	private String openid;
	private String eid;
	
	JPanel panel;
	JLabel[] label_disp = new JLabel[14];
	JButton BUT_APPLY = new JButton("Apply");
	
	public void set_eid(String s)
	{
		eid = s;
	}
	
	public void set_openid(String s)
	{
		openid = s;
	}
	public OpDetails()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   setTitle("Opening Details");
	}
	public void display()
	{
		try
		{
		this.setSize(500, 700);
		panel=new JPanel(new GridLayout(16,1));
		
		int i,j=0;
		for(i=0;i<14;i++)
			label_disp[i]= new JLabel();
		
		for(i=0;i<14;i=i+2)
			label_disp[i].setText("            ");
		
		String query_fetch = "select jb.title, cp.cname, jb.jdesc, mn.team_name,jb.exp_min,jb.exp_max,jb.sal_min,jb.sal_max,jb.city,jb.deadline from dpp_openings jb, dpp_companies cp, dpp_managers mn where jb.opid="+openid+" and jb.mid = mn.mid and jb.cid = cp.cid";
		String[] result_fetch = sql_fetch(query_fetch);
		if(result_fetch[0].equals(null))
		{
			for(i=1;i<14;i=i+2)
			label_disp[1].setText("Database is down at the moment");
			
		}
		else
		{
			for(i=1;i<14;i=i+2)
				label_disp[i].setText(result_fetch[j++]);
		}
		for(i=0;i<14;i++)
			panel.add(label_disp[i]);
		panel.add(BUT_APPLY);
		
		
		BUT_APPLY.addActionListener(this);
		
		this.add(panel);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
		String str = ae.getActionCommand();
		
		if(str.equals("Apply"))
		{
			String query = "insert into dpp_applications values("+openid+","+eid+")";
			if(sql_insert(query)== true)
			{
				this.setVisible(false);
				JOptionPane.showMessageDialog(this,"You have successfully applied for the opening","Success",JOptionPane.INFORMATION_MESSAGE);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Sorry, Your application was unsuccessful. Kindly try again after some time.","Error",JOptionPane.ERROR_MESSAGE);
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
		   String check_query = "select count(*) from dpp_applications";
		   
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
	public static String[] sql_fetch(String query) throws SQLException
	   {
		   String[] result = new String[7];
		   //String result=null;
		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rset = null;
		   
		   
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		   
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery(query);
		   int i=0;
		   if(rset.next())
		   {
			   result[0] = rset.getString(1)+" at "+rset.getString(2);
			   
			   result[1] = "Description: "+rset.getString(3);
			   
			   result[2] = "Team: "+rset.getString(4);
			   
			   result[3] = "Experience: "+rset.getString(5)+"-"+rset.getString(6)+" years";
			   
			   result[4] = "Salary: "+rset.getString(7)+"-"+rset.getString(8)+"  INR per month";
			   
			   result[5] = "City: "+rset.getString(9);
			   
			   result[6] = "Deadline: "+rset.getString(10);
			   
		   }
		   else
		   {
			  result[0]=null;
		   }
		   rset.close();
		   stmt.close();
		   conn.close();
		   
		   return result;
	   }
	
}
