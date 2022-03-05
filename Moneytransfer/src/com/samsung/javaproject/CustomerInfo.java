package com.samsung.javaproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerInfo {
	String customer_name;
	String customer_mobile_number;
	String customer_email_address;
	String customer_upi;
	String customer_account_number;
	String customer_city;
	String customer_pincode;
	String customer_street_address;
	int customer_balance;
	
	void customerInfo(String login_mobile_number)
	{
		BankInfo bankinfo=new BankInfo();
		bankinfo.customerinfo = new CustomerInfo();
		try
		{
			Class.forName(Start.driver);
			Connection con=DriverManager.getConnection(Start.url, Start.user, Start.password);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from customerinfo where customer_mobile_number="+login_mobile_number);

			while(rs.next())
			{
				System.out.println("Account Holder Name\t:"+rs.getString("customer_name"));
				System.out.println("Mobile \t\t\t:"+rs.getString("customer_mobile_number"));
				System.out.println("Email \t\t\t:"+rs.getString("customer_email_address"));
				System.out.println("UPI \t\t\t:"+rs.getString("customer_upi"));
				System.out.println("Account Number \t\t:"+rs.getString("customer_account_number"));
				System.out.println("City \t\t\t:"+rs.getString("customer_city"));
				System.out.println("Pincode \t\t:"+rs.getString("customer_pincode"));
				System.out.println("Street \t\t\t:"+rs.getString("customer_street_address"));
				System.out.println("Balance\t\t\t:"+rs.getString("customer_balance")+"rs");
				System.out.println();
				
				
			}
			stmt.close();
			con.close();
		}catch(Exception e) { System.out.println(e);}
		
		//TRANSACTION COMPLETED
		AvailableServices availableservices = new AvailableServices();
		availableservices.availableServices(login_mobile_number);
	}
	
	
	
	void balanceEnquery(String login_mobile_number)
	{
		try
		{
			Class.forName(Start.driver);
			Connection con=DriverManager.getConnection(Start.url, Start.user, Start.password);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from customerinfo where customer_mobile_number="+login_mobile_number);
			
			while(rs.next())
			{
				System.out.println("Account Holder Name\t:"+rs.getString("customer_name"));
				System.out.println("Balance\t\t\t:"+rs.getString("customer_balance")+"rs");
				System.out.println();
				
			}
			
		}catch(Exception e) {System.out.println(e);}
		
		//TRANSACTION COMPLETED
		AvailableServices availableservices = new AvailableServices();
		availableservices.availableServices(login_mobile_number);
	}
}
