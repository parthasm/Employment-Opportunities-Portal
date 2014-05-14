package first_page;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.driver.*;
import oracle.sql.*;
 
class HomeEmp extends JFrame implements ActionListener 
{
	
	private String eid;
	private String desig;
	private String comp;
	private String value_srch_sk;
	private String value_srch_comp;
	
	JPanel panel;
	
	JLabel label_wlc = new JLabel();
	JLabel label_name = new JLabel();
	JLabel label_desig = new JLabel();
	JLabel label_comp = new JLabel();
	JButton BUT_EXP = new JButton("Add your past experience");
	JLabel label_space1 = new JLabel();
	JLabel label_srch_sk = new JLabel();
	JTextField text_srch_sk = new JTextField(20);
	JButton BUT_SRCH_SK=new JButton("Search by skill");
	JLabel label_space2 = new JLabel();
	JLabel label_srch_comp = new JLabel();
	JTextField text_srch_comp = new JTextField(20);
	JButton BUT_SRCH_COMP=new JButton("Search in companies");
	JLabel label_space3 = new JLabel();
	JLabel label_posapl = new JLabel();

	JLabel[] label_posapl_qry;

	JLabel label_space4 = new JLabel();
	JButton BUT_LOGOUT = new JButton("Logout");
	HomeEmp()
 	{
	   //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setTitle("Employee Home Page");
       
	    
	}
	public void set_eid(String str)
	{
		eid = str;
	}
	
	public void set_srch_sk(String str)
	{
		value_srch_sk = str;
	}
	public void set_srch_comp(String str)
	{
		value_srch_comp = str;
	}
	public void display()
	{
		try
		{
		
    	
			
    	label_wlc.setText("Welcome");
    	
    	String query_emp_fetch = "select * from dpp_employee where eid="+eid;
    	String query_exp_fetch = "select title from dpp_experience where eid="+eid+" and ecurrent=\'Y\' and (to_date is null)";
    	String query_comp_fetch = "select cname from dpp_companies c1,dpp_experience ex1,dpp_employee e1 where e1.eid="+eid+" and e1.eid=ex1.eid and ex1.cid=c1.cid and ex1.ecurrent=\'Y\' and (ex1.to_date is null)";
    	String query_posapl_count = "select count(*) from dpp_openings op,dpp_applications ap,dpp_companies cp where ap.eid="+eid+" and ap.opid=op.opid and op.cid=cp.cid";
    	String query_posapl = "select op.title,op.city,cp.cname from dpp_openings op,dpp_applications ap,dpp_companies cp where ap.eid="+eid+" and ap.opid=op.opid and op.cid=cp.cid";
    	
	String result_emp = sql_fetch_emp(query_emp_fetch);
    	String result_exp = sql_fetch_exp(query_exp_fetch);
    	String result_comp = sql_fetch_comp(query_comp_fetch);
	int result_posapl_count = sql_count_srch(query_posapl_count);
	
	this.setSize(500, (400+(80*result_posapl_count)));
    	if (!(result_emp.equals("failure in retrieving from employee table"))) 
    	label_name.setText(result_emp);
    	else
    		label_name.setText("Our database is down at the moment. The inconvenience caused is deeply regretted");
    	
    	if (!(result_exp.equals("failure in retrieving from experience table"))) 
    		label_desig.setText(result_exp);
        	else
        		label_name.setText("Our database is down at the moment. The inconvenience caused is deeply regretted");
    	
    	if (!(result_comp.equals("failure in retrieving company name")))
    			label_comp.setText("at "+result_comp);
    	else
    		label_comp.setText("Our database is down at the moment. The inconvenience caused is deeply regretted");
	
    	label_space1.setText("            ");
    	
    	label_srch_sk.setText("Search for openings based on skill set");
    	
    	label_space2.setText("            ");
    	label_space3.setText("            ");
    	label_space4.setText("            ");
    	label_srch_comp.setText("Search for openings in companies");
    	
    	label_posapl.setText("Positions applied for:");
    	//creation of JComponents END
    	
    	panel=new JPanel(new GridLayout((15+(2*(result_posapl_count+1))),1));
        panel.add(label_wlc);
        panel.add(label_name);
        panel.add(label_desig);
        panel.add(label_comp);
        panel.add(BUT_EXP);
        panel.add(label_space1);
        panel.add(label_srch_sk);
        panel.add(text_srch_sk);
        panel.add(BUT_SRCH_SK);
        panel.add(label_space2);
        panel.add(label_srch_comp);
        panel.add(text_srch_comp);
        panel.add(BUT_SRCH_COMP);
        panel.add(label_space3);
        
	
	if(result_posapl_count!=0)
	{
		panel.add(label_posapl);
        panel.add(label_space4);
		String result_posapl[] = sql_fetch_posapl(result_posapl_count,query_posapl);
		label_posapl_qry = new JLabel[(2*result_posapl_count)];
		for(int i_he=0; i_he < result_posapl_count; i_he++)
		{
			label_posapl_qry[(2*i_he)] = new JLabel();
			label_posapl_qry[((2*i_he)+1)] = new JLabel();
			
			label_posapl_qry[(2*i_he)].setText(result_posapl[i_he]);
			label_posapl_qry[((2*i_he)+1)].setText("            ");
			
			panel.add(label_posapl_qry[(2*i_he)]);
			panel.add(label_posapl_qry[((2*i_he)+1)]);
		}
	}

        panel.add(BUT_LOGOUT);
        this.add(panel);
        
        BUT_SRCH_SK.addActionListener(this);
        BUT_SRCH_COMP.addActionListener(this);
        BUT_LOGOUT.addActionListener(this);
        BUT_EXP.addActionListener(this);
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
			 
			 if(str.equals("Search by skill"))
			 {
				 String value_srch_sk = text_srch_sk.getText().toUpperCase();
				 String query_srch_sk_count = "select count(*) from dpp_openings jb,dpp_companies cmp where jb.cid = cmp.cid and jb.title like \'%"+value_srch_sk+"%\'";

				 String query_srch_sk = "select jb.opid,jb.title,jb.city,cmp.cname from dpp_openings jb,dpp_companies cmp where jb.cid = cmp.cid and jb.title like \'%"+value_srch_sk+"%\'";
				 int count_sk = sql_count_srch(query_srch_sk_count);
				 if (count_sk==0)
				 {
					 JOptionPane.showMessageDialog(this,"No openings match your search","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 }
				 else
				 {
					 ViewJobs page_vj = new ViewJobs();
					 page_vj.set_counter(count_sk);
					 page_vj.set_eid(eid);
					 
					 String result_srch[] = sql_srch(count_sk,query_srch_sk);
					 page_vj.set_qry_rs(result_srch);
					 
					 page_vj.display();
					 page_vj.setVisible(true);
				 }
			 }
			 
			 else if(str.equals("Search in companies"))
			 {
				 String value_srch_comp = text_srch_comp.getText().toUpperCase();
				 String query_srch_comp_count = "select count(*) from dpp_openings jb,dpp_companies cmp where jb.cid = cmp.cid and cmp.cname like \'%"+value_srch_comp+"%\'";
				 //jb.title,jb.city,cmp.cname
				 String query_srch_comp = "select jb.opid,jb.title,jb.city,cmp.cname from dpp_openings jb,dpp_companies cmp where jb.cid = cmp.cid and cmp.cname like \'%"+value_srch_comp+"%\'";

				 int count_comp = sql_count_srch(query_srch_comp_count);
				 if (count_comp==0)
				 {
					 JOptionPane.showMessageDialog(this,"No openings match your search","Error",JOptionPane.ERROR_MESSAGE);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 }
				 else
				 {
					 ViewJobs page_vj = new ViewJobs();
					 page_vj.set_counter(count_comp);
					 page_vj.set_eid(eid);
					 
					 String result_srch[] = sql_srch(count_comp,query_srch_comp);
					 page_vj.set_qry_rs(result_srch);
					 
					 page_vj.display();
					 page_vj.setVisible(true);
				 }
			 }
			 else if(str.equals("Logout"))
			 {
				 this.setVisible(false);
				 LogReg page=new LogReg();
		        	page.setVisible(true);
			 }
			 else if(str.equals("Add your past experience"))
			 {
				 this.setVisible(false);
				 Exper obj = new Exper();
				 obj.set_eid(eid);
				 obj.display();
				 obj.setVisible(true);
			 }
		 }
		 catch(Exception e)
		 {
			 JOptionPane.showMessageDialog(null, e.getMessage());
		 }
	 }
	 public static String sql_fetch_emp(String emp_query) throws SQLException
	   {
		   //String[] result = new String[2];
		   String result=null;
		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rset = null;
		   
		   
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		   
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery(emp_query);
		   if(rset.next())
		   {
			   if(rset.getString(6).equalsIgnoreCase("M"))
				   result = "Mr. ";
			   else if(rset.getString(6).equalsIgnoreCase("F"))
			   result = "Ms. ";
			   
			   result = result + rset.getString(2)+" "+rset.getString(3);
		   }
		   else
			   result = "failure in retrieving from employee table";
	   
		   rset.close();
		   stmt.close();
		   conn.close();
		   
		   return result;
	   }
	   
	   public static String sql_fetch_exp(String exp_query) throws SQLException
	   {
		   //String[] result = new String[2];
		   String result=null;
		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rset = null;
		   
		   
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		   
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery(exp_query);
		   if(rset.next())
		   {
			   result = rset.getString(1);
			   
		   }
		   else
			   result = "failure in retrieving from experience table";
	   
		   rset.close();
		   stmt.close();
		   conn.close();
		   
		   return result;
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
	   public static int sql_count_srch(String count_query) throws SQLException
	   {
		   
		   int result=0;
		   
		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rset = null;
		   
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery(count_query);
		   if(rset.next())
		   {
			   result=Integer.parseInt(rset.getString(1));
		   }
		   else
			result=0;	
		   rset.close();
		   stmt.close();
		   conn.close();
		   
		   return result;
	   }
	   public static String[] sql_fetch_posapl(int count,String query) throws SQLException
	   {
		   String[] result = new String[count];
		   //String result=null;
		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rset = null;
		   
		   
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		   
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery(query);
		   int i=0;
		   while(rset.next())
		   {
			  result[i++] = rset.getString(1)+" at "+ rset.getString(3)+" in "+rset.getString(2);
			   
		   }
		   
	   
		   rset.close();
		   stmt.close();
		   conn.close();
		   
		   return result;
	   }
	   public static String[] sql_srch(int count,String query) throws SQLException
	   {
		   String[] result = new String[count];
		   //String result=null;
		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rset = null;
		   
		   
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@172.24.2.102:1521:orcl","f2008431","IwillRISE666");
		   
		   stmt = conn.createStatement();
		   rset = stmt.executeQuery(query);
		   int i=0;
		   while(rset.next())
		   {
			   result[i++] = rset.getString(1)+"_"+rset.getString(2)+" at "+ rset.getString(4)+" in "+rset.getString(3);
			   
		   }
		   
	   
		   rset.close();
		   stmt.close();
		   conn.close();
		   
		   return result;
	   }
 }