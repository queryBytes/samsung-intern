package com.samsung.javaproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginValidation {
	public
	int sender_balance;
	
	boolean loginValidation(String login_mobile_number)
	{
		
		try
		{
			Class.forName(Start.driver);
			Connection con1=DriverManager.getConnection(Start.url, Start.user, Start.password);
			Statement stmt1=con1.createStatement();
			ResultSet rs1=stmt1.executeQuery("select * from customerinfo");
			
			while(rs1.next())
			{
				if(login_mobile_number.equals(rs1.getString("customer_mobile_number")))
				{
					sender_balance=rs1.getInt("customer_balance");
					return true;
				}
			}
			
			con1.close();
			stmt1.close();
			
			
		}catch(Exception e) {System.out.println(e);}
		
		return false;
	}

}
