package first_page;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class RegEmpEd extends JFrame implements ActionListener
{
	RegEmpEd()
 	{
	   //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setTitle("Employee Registration : Step 2");
       setSize(400, 200);
	    
	}
	public void actionPerformed(ActionEvent ae) 
	{
		
	}
}
