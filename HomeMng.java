package first_page;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.driver.*;
import oracle.sql.*;
 
class HomeMng extends JFrame implements ActionListener
{
	JPanel panel;
	
	JLabel label_wlc;
	JLabel label_name;
	JLabel label_desig;
	JLabel label_team_name;
	JLabel label_comp;
	JLabel label_space1;
	
	JButton BUT_POST = new JButton("Post an opening");
	
	JButton BUT_LOGOUT = new JButton("Logout");
	private String mid;
	HomeMng()
 	{
	   //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setTitle("Manager Home Page");
       setSize(400, 200);
	    
	}
	public void set_mid(String str)
	{
		mid = str;
	}
	public void display()
	{
		try
		{
			this.setSize(400, 300);
			String query_mng_fetch = "select * from dpp_managers where mid="+mid;
        	
        	String query_mng_comp_fetch = "select cname from dpp_companies c1, dpp_managers m1 where m1.mid="+mid+" and m1.cid = c1.cid";
        	//creation of JComponents START
        	
        	
        	label_wlc = new JLabel();
        	label_name = new JLabel();
        	label_desig = new JLabel();
        	label_team_name = new JLabel();
        	label_comp = new JLabel();
        	label_space1 = new JLabel();
        	label_wlc.setText("Welcome");
        	
        	
        	String[] result_mng = sql_fetch_mng(query_mng_fetch);
        	
        	String result_comp = sql_fetch_comp(query_mng_comp_fetch);
        	if (!(result_mng[0].equals("failure"))) 
        	{		
        		label_name.setText(result_mng[0]);
        		label_desig.setText(result_mng[1]);
        		label_team_name.setText(result_mng[2]);
        	}	
        	else
        		label_name.setText("Our database is down at the moment. The inconvenience caused is deeply regretted");
        	
        	
        	if (!(result_comp.equals("failure in retrieving company name")))
        			label_comp.setText("at "+result_comp);
        	else
        		label_comp.setText("Our database is down at the moment. The inconvenience caused is deeply regretted");
    	
        	label_space1.setText("             ");
        	//creation of JComponents END
        	
        	panel=new JPanel(new GridLayout(8,1));
            panel.add(label_wlc);
            panel.add(label_name);
            panel.add(label_desig);
            panel.add(label_team_name);
            panel.add(label_comp);
            panel.add(BUT_POST);
            panel.add(label_space1);
            panel.add(BUT_LOGOUT);
            
            BUT_POST.addActionListener(this);
            BUT_LOGOUT.addActionListener(this);
            
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
		if(str.equals("Logout"))
		 {
			 this.setVisible(false);
			 LogReg page=new LogReg();
	        	page.setVisible(true);
		 }
		else if(str.equals("Post an opening"))
		{
			this.setVisible(false);
			PostOpening obj = new PostOpening();
			obj.set_mid(mid);
			obj.display();
			obj.setVisible(true);
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public static String sql_fetch_comp(String comp_query) throws SQLException
	   {
		   //String[] result = new String[2];
		   String result=null;
		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rset = null;
		   
		   
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		   
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery(comp_query);
		   if(rset.next())
		   {
			   result = rset.getString(1);
			   
		   }
		   else
			   result = "failure in retrieving company name";
	   
		   rset.close();
		   stmt.close();
		   conn.close();
		   
		   return result;
	   }
	   public String[] sql_fetch_mng(String mng_query) throws SQLException
	   {
		   String[] result = new String[3];
		   //String result=null;
		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rset = null;
		   
		   
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		   
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery(mng_query);
		   if(rset.next())
		   {
			   result[0] = rset.getString(3)+" "+rset.getString(4);
			   result[1] = rset.getString(6);
			   result[2] = rset.getString(5);
			   
		   }
		   else
		   {
			   result[0] = "failure";
			   result[1] = " in retrieving from";
			   result[2] = " manager table";
		   }
		   rset.close();
		   stmt.close();
		   conn.close();
		   
		   return result;
	   }
	
 }
