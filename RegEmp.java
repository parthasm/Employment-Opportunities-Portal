package first_page;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.driver.*;
import oracle.sql.*;

 
class RegEmp extends JFrame implements ActionListener
{
JButton BUT_STEP_TWO;
	
	JPanel panel;
	
	JLabel label_head;
	JLabel label_space1;
	JLabel label_fn;
	JLabel label_ln;
	JLabel label_email;
	JLabel label_pwd;
	JLabel label_confirm_pwd;
	JLabel label_dob;
	JLabel label_gender;
	JLabel label_phone;
	JLabel label_city;
	JLabel label_space2;
	
	JLabel label_head1;
	JLabel label_desig;
	JLabel label_jdesc;
	JLabel label_company;
	JLabel label_date;
	JLabel label_skill;
	JLabel label_space3;
	
	JTextField text_fn;
	JTextField text_ln;
	JTextField text_email;
	JTextField text_pwd;
	JTextField text_confirm_pwd;
	JTextField text_dob;
	JTextField text_gender;
	JTextField text_phone;
	JTextField text_city;
	
	JTextField text_desig;
	JTextField text_jdesc;
	JTextField text_company;
	JTextField text_date;
	JTextField text_skill;
	
	
	
	RegEmp()
 	{
	   //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setTitle("Employee Registration : Step 1");
       setSize(400, 700);
       
      
	    
	}
	public void display()
	{
		 label_head = new JLabel();
	       label_head.setText("Kindly provide the following details");
	       
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
	       
	       label_dob = new JLabel();
	       label_dob.setText("Date of Birth (for example, 19-Jun-80):");
	       text_dob = new JTextField(20);
	       
	       label_gender = new JLabel();
	       label_gender.setText("Gender (M/F):");
	       text_gender = new JTextField(20);
	       
	       label_phone = new JLabel();
	       label_phone.setText("Phone Number (10 digit number excluding leading zero) :");
	       text_phone = new JTextField(20);
	       
	       label_city = new JLabel();
	       label_city.setText("City:");
	       text_city = new JTextField(20);
	       
	       label_space2 = new JLabel();
	       label_space2.setText("                         ");
	       
	       
	       label_head1 = new JLabel();
	       label_head1.setText("Current Job and Skills ");
	       
	       label_desig = new JLabel();
	       label_desig.setText("Your Current Designation:");
	       text_desig = new JTextField(20);
	       
	       label_jdesc = new JLabel();
	       label_jdesc.setText("Main Responsibilities (separated by \'and\') :");
	       text_jdesc = new JTextField(100);
	     
	       label_company = new JLabel();
	       label_company.setText("Name of Company you are working for:");
	       text_company = new JTextField(20);
	       
	       label_date = new JLabel();
	       label_date.setText("Starting Date in present post( for example: 21-Jun-11 ) :");
	       text_date = new JTextField(20);
	       
	       label_skill = new JLabel();
	       label_skill.setText("List of Skills(separated by a space eg:C C++ Java):");
	       text_skill = new JTextField(20);
	       
	       
	       
	       label_space3 = new JLabel();
	       label_space3.setText("                         ");
		    
	       BUT_STEP_TWO = new JButton("Save and Create Account");
	       
	       panel=new JPanel(new GridLayout(34,1));
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
	       panel.add(label_dob);
	       panel.add(text_dob);
	       panel.add(label_gender);
	       panel.add(text_gender);
	       panel.add(label_phone);
	       panel.add(text_phone);
	       panel.add(label_city);
	       panel.add(text_city);
	       panel.add(label_space2);
	       
	       panel.add(label_head1);
	       panel.add(label_desig);
	       panel.add(text_desig);
	       panel.add(label_jdesc);
	       panel.add(text_jdesc);
	       panel.add(label_company);
	       panel.add(text_company);
	       panel.add(label_date);
	       panel.add(text_date);
	       panel.add(label_skill);
	       panel.add(text_skill);
	   
	       
	       panel.add(label_space3);
	       
	       
	       panel.add(BUT_STEP_TWO);
	       
	       this.add(panel,BorderLayout.CENTER);
	       
	       BUT_STEP_TWO.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			String query,query1,query_exp,query_skill,result,result_emp,company_id = null,emp_id;
			String str = ae.getActionCommand();
			boolean account_create_success=false;
			if(str.equals("Save and Create Account"));
			{
				String value_fn = text_fn.getText();
				String value_ln = text_ln.getText();
				String value_email = text_email.getText();
				String value_pwd = text_pwd.getText();
				String value_confirm_pwd = text_confirm_pwd.getText();
				String value_dob = text_dob.getText();
				String value_gender = text_gender.getText();
				String value_phone = text_phone.getText();
				String value_city = text_city.getText();
				
				String value_desig = text_desig.getText();
				String value_jdesc = text_jdesc.getText();
				String value_company = text_company.getText().toUpperCase();
				String value_date = text_date.getText();
				String value_skill = text_skill.getText();
				
				
				query1 = "select cid from dpp_companies where cname=\'"+value_company+"\'"; 
				query = "insert into dpp_employee values(((select MAX(e1.eid) from dpp_employee e1)+1),";
				
				query_exp = "insert into dpp_experience values (";
				query_skill = "insert into dpp_skills values (((select MAX(s1.sid) from dpp_skills s1)+1),";
				//validating phone number
				int i=0,j=1;
				char c;
				boolean validate_phone=true;
				
				String[] value_skill_a;
				
				int i1=0,j2=1,k3;
				for(i1=0;i1<value_skill.length();i1++)
				{
					if(value_skill.charAt(i1)==' ')
						j2++;
				}
				value_skill_a = new String[j2];
				i1=0;
				while(value_skill.indexOf(" ")!=-1)
				{
					value_skill_a[i1++]= value_skill.substring(0, value_skill.indexOf(" ")).toUpperCase();
					
					value_skill = value_skill.substring((value_skill.indexOf(" ")+1));
					
				}
				value_skill_a[i1]=value_skill.toUpperCase();
				if(value_phone.length()!=10)
				{
					validate_phone = false;
				}
				else
				{
				for(i=0; i<value_phone.length(); i++)
				{
					c = value_phone.charAt(i);
					if(c<48 || c>57)
					{
						validate_phone = false;
						break;
					}
				}
				}
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
				
				else if(value_dob.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Kindly provide your date of birth in correct format","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					//area of improvement
				}
				
				else if((!(value_gender.equals("M"))) && (!(value_gender.equals("F"))))
				{
					JOptionPane.showMessageDialog(this,"Kindly type M for male or F for Female","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				else if(validate_phone==false)
				{
					JOptionPane.showMessageDialog(this,"Kindly enter a valid phone number","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else if(value_city.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Please provide the name of the city where you operate","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else if(value_desig.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Please provide your valid current Job Designation","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				else if(value_jdesc.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Please describe your current responsibilities","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else if(RegMng.sql_company(query1).equals("incorrect"))
				{
					JOptionPane.showMessageDialog(this,"Company not present in database","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else if(value_date.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Please enter the valid starting date in your present post","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else if(value_skill.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Please enter your primary skill","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else
				{
					
					query = query+"\'"+value_fn    +"\',";
					query = query+"\'"+value_ln    +"\',";
					query = query+"\'"+value_email +"\',";
					query = query+"\'"+value_pwd   +"\',";
					query = query+"\'"+value_gender+"\',";
					query = query+"\'"+value_dob   +"\',";
					query = query+"\'"+value_phone +"\',";
					query = query+"\'"+value_city  +"\')";
					
					company_id = RegMng.sql_company(query1);
					result_emp = sql_insert_emp(query);
					if(!(result_emp.equals("incorrect")))
					{	
						emp_id = result_emp;
						
						query_exp = query_exp+emp_id+",";
						query_exp = query_exp+company_id+",";
						query_exp = query_exp+"\'"+value_desig+"\',";
						query_exp = query_exp+"\'"+value_jdesc+"\',";
						query_exp = query_exp+"\'"+value_city+"\',";
						query_exp = query_exp+"\'Y\',";
						query_exp = query_exp+"\'"+value_date+"\',null,((select MAX(ex1.expid) from dpp_experience ex1)+1))";
						
						if(!(sql_insert_exp(query_exp).equals("incorrect")))
						{
							query_skill = query_skill+emp_id+",";
							query_skill = query_skill+"\'"+value_skill+"\')";
							
							if(sql_insert_skill(value_skill_a)==true)
							{
								account_create_success = true;
					this.setVisible(false);
					JOptionPane.showMessageDialog(this,"Your account has been created","Success",JOptionPane.INFORMATION_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					LogReg page=new LogReg();
		        	page.setVisible(true);
		        	
							}
							else
							
								account_create_success = false;
							
						}
							else
								account_create_success = false;
						}
					else
						account_create_success = false;
				}
				if(account_create_success==false)
				{
					JOptionPane.showMessageDialog(this,"Sorry, Account creation failed!","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		}
		
		catch(Exception e)
		{
			
			JOptionPane.showMessageDialog(this,"Sorry, Account creation failed!","Error",JOptionPane.ERROR_MESSAGE);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			e.printStackTrace();
		}
	}
	
	public String sql_insert_emp(String emp_query) throws SQLException
	   {
		 	boolean b,b1;
		 	int check=0,check1=0;
		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   OracleDataSource ods=new OracleDataSource();
		   Connection conn = null;
		   Statement stmt = null, stmt_check;
		   ResultSet rset = null,rset_check;
		   
		   String check_query = "select count(*) from dpp_employee", result;
		   
		   
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		  //checking for insert success START
		   stmt_check = conn.createStatement();
		   rset_check = stmt_check.executeQuery(check_query);
		   if(rset_check.next())
			   check = rset_check.getInt(1);
		   rset_check.close();
		   stmt_check.close();
		 //checking for insert success END
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery(emp_query);
		   if(rset.next())
		   {
			   
		   }
		   /*rset.close();
			  stmt.close();*/
			  
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery("commit");
//		   if(rset.next())
//		   {
//			   
//		   }
		   rset.close();
			  stmt.close();
		 
		 //checking for insert success START
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
		 //checking for insert success END
		  
		  rset.close();
		  stmt.close();
		  
		  if(b==true)
		  {
			  stmt = conn.createStatement();
			  rset = stmt.executeQuery("select MAX(e1.eid) from dpp_employee e1");
			  if(rset.next())
			  {
			  result = rset.getString(1);
			  }
			  else
				  result = "incorrect";
			  rset.close();
			  stmt.close();
		  }
		  else
			  result = "incorrect";
		  
		   conn.close();
		   
		   return result;
	   } 
	public String sql_insert_exp(String exp_query) throws SQLException
	   {
		 	boolean b,b1;
		 	int check=0,check1=0;
		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   OracleDataSource ods=new OracleDataSource();
		   Connection conn = null;
		   Statement stmt = null, stmt_check;
		   ResultSet rset = null,rset_check;
		   
		   String check_query = "select count(*) from dpp_experience", result;
		   
		   
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		  //checking for insert success START
		   stmt_check = conn.createStatement();
		   rset_check = stmt_check.executeQuery(check_query);
		   if(rset_check.next())
			   check = rset_check.getInt(1);
		   rset_check.close();
		   stmt_check.close();
		 //checking for insert success END
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery(exp_query);
		   if(rset.next())
		   {
			   
		   }
		   /*rset.close();
			  stmt.close();*/
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery("commit");
//		   if(rset.next())
//		   {
//			   
//		   }
		   rset.close();
			  stmt.close();
		 
		 //checking for insert success START
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
		 //checking for insert success END
		  
		  rset.close();
		  stmt.close();
		  
		  if(b==true)
		  {
			  stmt = conn.createStatement();
			  rset = stmt.executeQuery("select MAX(ex1.eid) from dpp_experience ex1");
			  if(rset.next())
			  {
			  result = rset.getString(1);
			  }
			  else
				  result = "incorrect";
			  rset.close();
			  stmt.close();
		  }
		  else
			  result = "incorrect";
		  
		   conn.close();
		   
		   return result;
	   } 
	public boolean sql_insert_skill(String[] skills) throws SQLException
	   {
//insert into dpp_user_skills values((select MAX(eid) FROM dpp_employee),(select sid from dpp_skills where sname='C'));		
		 	boolean b,b1;
		 	int check=0,check1=0;
		 	String query,check_query;
		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   Connection conn = null;
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		   
		   check_query = "select count(*) from dpp_user_skills";
		   
		   Statement stmt_check;
		   ResultSet rset_check;
		   
		   stmt_check = conn.createStatement();
		   rset_check = stmt_check.executeQuery(check_query);
		   if(rset_check.next())
			   check = rset_check.getInt(1);
		   rset_check.close();
		   stmt_check.close();
		   
		   for(int i=0;i<skills.length;i++)
		   {
			   
			   Statement stmt = null;
			   ResultSet rset = null;
			   
			   stmt = conn.createStatement();
			   query = "insert into dpp_user_skills values((select MAX(eid) FROM dpp_employee),(select sid from dpp_skills where sname=\'"+skills[i]+"\'))";
			   rset = stmt.executeQuery(query);
			   if(rset.next())
			   {
				   
			   }
			   stmt = conn.createStatement();
			   rset = stmt.executeQuery("commit");
			   rset.close();
				  stmt.close();
				  
		   }
		   stmt_check = conn.createStatement();
		   rset_check = stmt_check.executeQuery(check_query);
		   if(rset_check.next())
			   check1 = rset_check.getInt(1);
		   rset_check.close();
		   stmt_check.close();
		   
		   if (check1 == (check+skills.length))
			   b= true;
		   else
			   b = false;
	
		   
		   conn.close();
		   
		   return b;
	   } 
 }

 
