package first_page;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.driver.*;
import oracle.sql.*;

class RegMng extends JFrame implements ActionListener
{
	JButton BUT_CRT_ACT;
	
	JPanel panel;
	
	JLabel label_head;
	JLabel label_space1;
	JLabel label_fn;
	JLabel label_ln;
	JLabel label_desig;
	JLabel label_team;
	JLabel label_city;
	JLabel label_company;
	JLabel label_company_code;
	JLabel label_email;
	JLabel label_pwd;
	JLabel label_confirm_pwd;
	JLabel label_space2;
	
	JTextField text_fn;
	JTextField text_ln;
	JTextField text_desig;
	JTextField text_team;
	JTextField text_city;
	JTextField text_company;
	JTextField text_company_code;
	JTextField text_email;
	JTextField text_pwd;
	JTextField text_confirm_pwd;
	
	RegMng()
 	{
	   //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setTitle("Manager Registration Page");
       setSize(500, 700);
       
       
	}
	
	public void display()
	{
		label_head = new JLabel();
	       label_head.setText("Kindly fill in the following details to create your account");
	       
	       label_space1 = new JLabel();
	       label_space1.setText("                         ");
	       
	       label_fn = new JLabel();
	       label_fn.setText("First Name:");
	       text_fn = new JTextField(20);
	       
	       label_ln = new JLabel();
	       label_ln.setText("Surname:");
	       text_ln = new JTextField(20);
	       
	       label_email = new JLabel();
	       label_email.setText("Email ID:");
	       text_email = new JTextField(20);
	       
	       label_pwd = new JLabel();
	       label_pwd.setText("Password:");
	       text_pwd = new JPasswordField(20);
	       
	       label_confirm_pwd = new JLabel();
	       label_confirm_pwd.setText(" Confirm Password:");
	       text_confirm_pwd = new JPasswordField(20);
	       
	       label_desig = new JLabel();
	       label_desig.setText("Designation:");
	       text_desig = new JTextField(20);
	       
	       label_team = new JLabel();
	       label_team.setText("Team:");
	       text_team = new JTextField(20);
	       
	       label_city = new JLabel();
	       label_city.setText("City:");
	       text_city = new JTextField(20);
	       
	       label_company = new JLabel();
	       label_company.setText("Company:");
	       text_company = new JTextField(20);
	       
	       label_company_code = new JLabel();
	       label_company_code.setText("Company Code:");
	       text_company_code = new JPasswordField(20);
	       
	       label_space2 = new JLabel();
	       label_space2.setText("                         ");
		    
	       BUT_CRT_ACT = new JButton("Create Account");
	       
	       panel=new JPanel(new GridLayout(27,1));
	       panel.add(label_head);
	       panel.add(label_space1);
	       panel.add(label_fn);
	       panel.add(text_fn);
	       panel.add(label_ln);
	       panel.add(text_ln);
	       panel.add(label_email);
	       panel.add(text_email);
	       panel.add(label_pwd);
	       panel.add(text_pwd);
	       panel.add(label_confirm_pwd);
	       panel.add(text_confirm_pwd);
	       panel.add(label_desig);
	       panel.add(text_desig);
	       panel.add(label_team);
	       panel.add(text_team);
	       panel.add(label_city);
	       panel.add(text_city);
	       panel.add(label_company);
	       panel.add(text_company);
	       panel.add(label_company_code);
	       panel.add(text_company_code);
	       panel.add(label_space2);
	       panel.add(BUT_CRT_ACT);
	       
	       this.add(panel,BorderLayout.CENTER);
	       
	       BUT_CRT_ACT.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			String query1, query2, query3, result;
			String str = ae.getActionCommand();
			if(str.equals("Create Account"));
			{
				String value_fn = text_fn.getText();
				String value_ln = text_ln.getText();
				String value_email = text_email.getText();
				String value_pwd = text_pwd.getText();
				String value_confirm_pwd = text_confirm_pwd.getText();
				String value_desig = text_desig.getText();
				String value_team = text_team.getText();
				String value_city = text_city.getText();
				String value_company = text_company.getText().toUpperCase();
				String value_company_code = text_company_code.getText();
				
				query1 = "select cid from dpp_companies where cname=\'"+value_company+"\'";
				
				query2 = "select cid from dpp_companies where cname=\'"+value_company+"\' and ccode=\'"+value_company_code+"\'";
				
				query3 = "insert into dpp_managers values(((select MAX(m1.mid) from dpp_managers m1)+1),";
				if(value_fn.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Kindly fill first name","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else if(value_ln.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Kindly fill last name","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else if(value_email.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Kindly provide your official email ID","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				else if(value_pwd.length()<6)
				{
					JOptionPane.showMessageDialog(this,"Kindly use a password of minimum 6 characters ","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				else if(value_confirm_pwd.equals("") || (!value_confirm_pwd.equals(value_pwd)))
				{
					JOptionPane.showMessageDialog(this,"Kindly confirm your password","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else if(value_desig.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Kindly provide your designation ","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else if(value_team.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Kindly provide the name of the team or group which you head","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else if(value_city.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Please provide the name of the city where you operate","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else if(sql_company(query1).equals("incorrect"))
				{
					JOptionPane.showMessageDialog(this,"Company not present in database","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else if(sql_company(query2).equals("incorrect"))
				{
					JOptionPane.showMessageDialog(this,"Kindly provide correct company code to authenticate your account","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else
				{
					result = sql_company(query2);
					query3 = query3+result+",\'"+value_fn+"\',";
					query3 = query3+"\'"+value_ln+"\',";
					query3 = query3+"\'"+value_team+"\',";
					query3 = query3+"\'"+value_desig+"\',";
					query3 = query3+"\'"+value_city+"\',";
					query3 = query3+"\'"+value_email+"\',";
					query3 = query3+"\'"+value_pwd+"\')";
					
					if(sql_insert(query3)== true)
					{		
					this.setVisible(false);
					JOptionPane.showMessageDialog(this,"Your Account has been created Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					LogReg page=new LogReg();
		        	page.setVisible(true);
					}
					
					else
					{
						{
							JOptionPane.showMessageDialog(this,"Sorry, Account creation failed!","Error",JOptionPane.ERROR_MESSAGE);
							setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						}
					}
						
				}
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	 public static String sql_company(String sqlquery) throws SQLException
	   {
		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   OracleDataSource ods=new OracleDataSource();
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rset = null;
		   
		   
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		   
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery(sqlquery);
		   String result;
		   if(rset.next())
		   {
		   result = rset.getString(1); 
		   }
		   else {
			   result= "incorrect";
		   }
		   rset.close();
		   stmt.close();
		   conn.close();
		   
		   return result;
	   }
	 public boolean sql_insert(String sqlquery) throws SQLException
	   {
		 	boolean b;
		 	int check=0,check1=0;
		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   OracleDataSource ods=new OracleDataSource();
		   Connection conn = null;
		   Statement stmt = null, stmt_check;
		   ResultSet rset = null,rset_check;
		   
		   String check_query = "select count(*) from dpp_managers";
		   
		   
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		  //checking for insert success start
		   stmt_check = conn.createStatement();
		   rset_check = stmt_check.executeQuery(check_query);
		   if(rset_check.next())
			   check = rset_check.getInt(1);
		   rset_check.close();
		   stmt_check.close();
		 //checking for insert success end
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery(sqlquery);
		   
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
		   
		   /*String result;
		   if(rset.next())
		   {
		   result = rset.getString(1); 
		   }
		   else {
			   result= "incorrect";
		   }*/
		   

		   rset.close();
		   
		   
		   
		   stmt.close();
		   conn.close();
		   
		   return b;
	   } 
 }
