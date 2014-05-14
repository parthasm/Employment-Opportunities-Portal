package first_page;



import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.driver.*;
import oracle.sql.*;
 
class LogReg extends JFrame implements ActionListener
{
 JButton BUT_LOGIN_EMP;
 JButton BUT_LOGIN_MNG;
 JButton BUT_REG_EMP;
 JButton BUT_REG_MNG;
 
 JPanel panel;
 
 JLabel label_signin_emp,label_signin_mng,label_email_emp,label_email_mng;
 
 JLabel label_pwd_emp,label_pwd_mng,label_reg_emp,label_reg_mng;
 
 JLabel label_space1, label_space2, label_space3;

  JTextField  text_email_emp,text_pwd_emp,text_email_mng,text_pwd_mng;
  
  ResultSet rset;
 
  LogReg()
	{
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
      setSize(500, 500);
	 	label_signin_emp = new JLabel();
	 	label_signin_emp.setText("Sign in as an Employee");
	 	
	 	label_email_emp = new JLabel();
	 	label_email_emp.setText("Email:"); 
	 	text_email_emp = new JTextField(20);
	 	
	 	label_pwd_emp = new JLabel();
	 	label_pwd_emp.setText("Password:");
	 	text_pwd_emp = new JPasswordField(20);
		
	    //this.setLayout(new BorderLayout());
 
	    BUT_LOGIN_EMP=new JButton("Employee Login");
	    
	    label_space1 = new JLabel();
	    label_space1.setText("                       ");
	    
	    label_signin_mng = new JLabel();
	    label_signin_mng.setText("Sign in as a Manager");
	    
	    label_email_mng = new JLabel();
	    label_email_mng.setText("Email:");
	    text_email_mng = new JTextField(20);
	    
	    label_pwd_mng = new JLabel();
	    label_pwd_mng.setText("Password:");
	    text_pwd_mng = new JPasswordField(20);
	    
	    BUT_LOGIN_MNG=new JButton("Manager Login");
	    
	    label_space2 = new JLabel();
	    label_space2.setText("                       ");
	    
	    label_reg_emp = new JLabel();
	    label_reg_emp.setText("To access Job opportunities, kindly register & create an employee account");
	    BUT_REG_EMP=new JButton("Employee Registration");
	    
	    label_space3 = new JLabel();
	    label_space3.setText("                       ");
	    
	    label_reg_mng = new JLabel();
	    label_reg_mng.setText("To post Openings in your organisation, kindly register & create a manager account");
	    BUT_REG_MNG=new JButton("Manager Registration");
		
        panel=new JPanel(new GridLayout(19,1));
		panel.add(label_signin_emp);
		panel.add(label_email_emp);
		panel.add(text_email_emp);
		panel.add(label_pwd_emp);
		panel.add(text_pwd_emp);
		panel.add(BUT_LOGIN_EMP);
		panel.add(label_space1);
		
		panel.add(label_signin_mng);
		panel.add(label_email_mng);
		panel.add(text_email_mng);
		panel.add(label_pwd_mng);
		panel.add(text_pwd_mng);
		panel.add(BUT_LOGIN_MNG);
		panel.add(label_space2);
		
		panel.add(label_reg_emp);
		panel.add(BUT_REG_EMP);
		panel.add(label_space3);
		
		panel.add(label_reg_mng);
		panel.add(BUT_REG_MNG);
	    add(panel,BorderLayout.CENTER);
	    
	    BUT_LOGIN_EMP.addActionListener(this);
	    BUT_LOGIN_MNG.addActionListener(this);
	    BUT_REG_EMP.addActionListener(this);
	    BUT_REG_MNG.addActionListener(this);
        setTitle("Login and Registration Page");
	}
   public void actionPerformed(ActionEvent ae)
	{
	   try{
		String query;
		
		
		String result;
		
		String str = ae.getActionCommand();
		
		if(str.equals("Employee Login"))
		{
			String value_email_emp=text_email_emp.getText();
			String value_pwd_emp=text_pwd_emp.getText();
			query = "select eid from dpp_employee where email=\'"+value_email_emp+"\' AND pwd=\'"+value_pwd_emp+"\'";
			result = sql_login_emp(query);
			if (!(result.equals("incorrect login"))) 
        {
        	this.setVisible(false);
        	
        	HomeEmp page_home_emp=new HomeEmp();
        	
        	page_home_emp.set_eid(result);
        	
        	page_home_emp.display();
        	
        	page_home_emp.setVisible(true);
        	
        
	}
		else 
		{
			//System.out.println("enter the valid username and password");
			JOptionPane.showMessageDialog(this,"Incorrect login or password for employee","Error",JOptionPane.ERROR_MESSAGE);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	}
        if(str.equals("Manager Login"))
        {
        String value_email_mng=text_email_mng.getText();
		String value_pwd_mng=text_pwd_mng.getText();
		query = "select mid from dpp_managers where email=\'"+value_email_mng+"\' AND pwd=\'"+value_pwd_mng+"\'";
		result = sql_login_mng(query);
		if (!(result.equals("incorrect login"))) 
        {
        	this.setVisible(false);
        	HomeMng page_home_mng=new HomeMng();
        	
        	page_home_mng.set_mid(result);
        	
        	page_home_mng.display();
        	
        	page_home_mng.setVisible(true);
        	
        	
			}
		else  
		{
			//System.out.println("enter the valid username and password");
			JOptionPane.showMessageDialog(this,"Incorrect login or password for manager","Error",JOptionPane.ERROR_MESSAGE);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
        }
		
		if(str.equals("Employee Registration"))
		{
			this.setVisible(false);
			RegEmp page_reg_emp = new RegEmp();
			
			page_reg_emp.display();
			
			page_reg_emp.setVisible(true);
		}
		
		if(str.equals("Manager Registration"))
		{
			this.setVisible(false);
			RegMng page_reg_mng = new RegMng();
			
			page_reg_mng.display();
			
			page_reg_mng.setVisible(true);
		}
	   }
	   catch (Exception e)
	   {
		   JOptionPane.showMessageDialog(null, e.getMessage());
	   }
        
}
   public String sql_login_emp(String sqlquery) throws SQLException
   {
	   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
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
		   result= "incorrect login";
	   }
	   rset.close();
	   stmt.close();
	   conn.close();
	   
	   return result;
   }
   public String sql_login_mng(String sqlquery) throws SQLException
   {
	   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
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
		   result= "incorrect login";
	   }
	   rset.close();
	   stmt.close();
	   conn.close();
	   
	   return result;
   }
   //public static String sql_fetch_emp(String emp_query, String exp_query) throws SQLException
   

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
   
}
