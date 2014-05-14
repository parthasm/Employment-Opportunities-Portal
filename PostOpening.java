package first_page;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class PostOpening extends JFrame implements ActionListener 
{
	private String mid;

	JPanel panel;
	
	JLabel[] label_disp = new JLabel[8];
	JTextField[] text_disp = new JTextField[8];
	JButton BUT_POST = new JButton("Post the Opening");
	
	public PostOpening()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   setTitle("Enter Opening Details");
	}
	public void set_mid(String str)
	{
		mid = str;
	}
	public void display()
	{
		this.setSize(500, 500);
		panel=new JPanel(new GridLayout(17,1));
		
		int i,j;
		for(i=0;i<8;i++)
		{
			label_disp[i] = new JLabel();
			text_disp[i]= new JTextField(20);
		}
		
		label_disp[0].setText("Title:");
		
		label_disp[1].setText("Job Description:");
		
		label_disp[2].setText("Location:");
		
		label_disp[3].setText("Starting Salary( INR per month):");
		
		label_disp[4].setText("Maximum Salary that may be offered( INR per month):");
		
		label_disp[5].setText("Minimum Experience required(in years):");
		
		label_disp[6].setText("Maximum Experience allowed(in years):");
		
		label_disp[7].setText("Deadline (eg:21-Jul-12)");
		
		for(i=0;i<8;i++)
		{
			panel.add(label_disp[i]);
			panel.add(text_disp[i]);
		}
		panel.add(BUT_POST);
		this.add(panel);
		
		BUT_POST.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
		String str = ae.getActionCommand();
		int i;
		
		if(str.equals("Post the Opening"))
		{
			String[] value_text = new String[8];
			for(i=0;i<8;i++)
				value_text[i] =  text_disp[i].getText();
//insert into dpp_openings values(((select MAX(opid) from dpp_openings)+1),4,
			//(select cid from dpp_managers where mid=4),
			//10000,20000,1,2,'10-Apr-12','Tester','Testing','Hyderabad');
			
			String query = "insert into dpp_openings values(((select MAX(opid) from dpp_openings)+1),";
			query+=mid+",(select cid from dpp_managers where mid="+mid+"),";
			query+=value_text[3]+","+value_text[4]+","+value_text[5]+",";
			query+=value_text[6]+",\'"+value_text[7]+"\',\'"+value_text[0]+"\',\'"+value_text[1]+"\',\'"+value_text[2]+"\')";
			
			if(sql_insert(query)==true)
			{
				this.setVisible(false);
				JOptionPane.showMessageDialog(this,"The Opening in your team has been posted","Success",JOptionPane.INFORMATION_MESSAGE);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				HomeMng page_home_mng=new HomeMng();
	        	
	        	page_home_mng.set_mid(mid);
	        	
	        	page_home_mng.display();
	        	
	        	page_home_mng.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Sorry! The Opening could be posted! Please try again after some time","Error",JOptionPane.ERROR_MESSAGE);
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
		   String check_query = "select count(*) from dpp_openings";
		   
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
