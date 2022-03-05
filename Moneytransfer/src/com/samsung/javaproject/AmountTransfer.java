package com.samsung.javaproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class AmountTransfer {
	boolean match=false;
	String login_mobile_number1, receiver_account_number, receiver_mobile_number, receiver_upi;
	int amt,receiver_balance, sender_balance=0;
	Scanner sc = new Scanner(System.in);
	LoginValidation loginvalidation = new LoginValidation();
	int amountTransfer(String login_mobile_number)
	{

		login_mobile_number1=login_mobile_number;
		int n;		
		System.out.println("Select transfer mode");
		System.out.println("1. By account number");
		System.out.println("2. By mobile number");
		System.out.println("3. By UPI id");
		n = sc.nextInt();
		switch(n)
		{
		case 1:
			amt= byAccountNumber(login_mobile_number);
			break;
		case 2:
			amt= byMobileNumber(login_mobile_number);
			break;
		case 3:
			amt= byUpiId(login_mobile_number);
			break;
		default:
			System.out.println("INVALID CHOICE!!\n");
			amountTransfer(login_mobile_number1);
		}
		return amt;		
	}
	
	
//By Account Number___________________________	
	int byAccountNumber(String login_mobile_number)
	{
		
		System.out.println("Enter Receiver account number");
		receiver_account_number=sc.next();
		try
		{
			Class.forName(Start.driver);
			Connection con2=DriverManager.getConnection(Start.url, Start.user, Start.password);
			Statement stmt2=con2.createStatement();
			ResultSet rs2=stmt2.executeQuery("select * from customerinfo");

			while(rs2.next())
			{	
				if(receiver_account_number.equals(rs2.getString("customer_account_number")))
				{
					match=true;
					receiver_balance=rs2.getInt("customer_balance");
					String receiver_name = rs2.getString("customer_name");	
					System.out.println("Account holder name\t:"+receiver_name);
					
					System.out.println("Do you want to continue transaction (y/n): ");
					String y = sc.next();
					if(y.equals("n") || y.equals("N"))
					{
						System.out.println("TRANSACTION CANCELED....\n");
						//TRANSACTION COMPLETED
						AvailableServices availableservices = new AvailableServices();
						availableservices.availableServices(login_mobile_number);
					}
					
				}
				if(login_mobile_number.equals(rs2.getString("customer_mobile_number")))
				{
					sender_balance=rs2.getInt("customer_balance");
				}
				
				
			}
			
			
			int flag;
			if(match)
			{
				
				do
				{
					flag=0;
					System.out.println("Enter Amount");
					amt=sc.nextInt();
					if(amt>0)
					{
						if(sender_balance-amt>=0)
						{
							receiver_balance = receiver_balance + amt;
							sender_balance = sender_balance - amt;
							String query1="update customerinfo set customer_balance="+(sender_balance)+" where customer_mobile_number="+login_mobile_number;
							String query2="update customerinfo set customer_balance="+(receiver_balance)+" where customer_account_number="+receiver_account_number;
							
							stmt2.executeUpdate(query1);
							stmt2.executeUpdate(query2);
							System.out.println("TRANSACTION SUCCESSFUL!!!!\n");
						}
						else
						{
							System.out.println("INSUFFICIENT BALANCE!!!");
							++flag;
						}
					}
					else 
					{
						System.out.println("AMOUNT INVALID!!!");
						++flag;
					}
				}while(flag==1);
				
			}
			else
			{
				System.out.println("INVALID ACCOUNT NUMBER\n");
			}


			stmt2.close();
			con2.close();
		}catch(Exception e) { System.out.println(e);}

		//TRANSACTION COMPLETED
		AvailableServices availableservices = new AvailableServices();
		availableservices.availableServices(login_mobile_number);
		
		return 0;
	}
	
	
//By Mobile Number_______________________	
	int byMobileNumber(String login_mobile_number)
	{
		
		System.out.println("Enter Receiver Mobile number");
		receiver_mobile_number=sc.next();
		try
		{
			Class.forName(Start.driver);
			Connection con2=DriverManager.getConnection(Start.url, Start.user, Start.password);
			Statement stmt2=con2.createStatement();
			ResultSet rs2=stmt2.executeQuery("select * from customerinfo");

			while(rs2.next())
			{	
				if(receiver_mobile_number.equals(rs2.getString("customer_mobile_number")))
				{
					match=true;
					receiver_balance=rs2.getInt("customer_balance");
					//System.out.println(receiver_balance);
					String receiver_name = rs2.getString("customer_name");	
					System.out.println("Account holder name\t:"+receiver_name);
					
					System.out.println("Do you want to continue transaction (y/n): ");
					String y = sc.next();
					if(y.equals("n") || y.equals("N"))
					{
						System.out.println("TRANSACTION CANCELED....\n");
						AvailableServices availableservices = new AvailableServices();
						availableservices.availableServices(login_mobile_number);

					}
					
				}
				if(login_mobile_number.equals(rs2.getString("customer_mobile_number")))
				{
					sender_balance=rs2.getInt("customer_balance");
				}	
			}
			
			if(match)
			{
				
				int flag;
				do
				{
					flag=0;
					System.out.println("Enter Amount");
					amt=sc.nextInt();
					if(amt>0)
					{
						if(sender_balance-amt>=0)
						{
							receiver_balance = receiver_balance + amt;
							//System.out.println(receiver_balance);
							sender_balance = sender_balance - amt;
							String query1="update customerinfo set customer_balance="+(sender_balance)+" where customer_mobile_number="+login_mobile_number;
							String query2="update customerinfo set customer_balance="+(receiver_balance)+" where customer_mobile_number="+receiver_mobile_number;
							
							stmt2.executeUpdate(query1);
							stmt2.executeUpdate(query2);
							System.out.println("TRANSACTION SUCCESSFUL!!!!\n");	
						}
						else
						{
							System.out.println("INSUFFICIENT BALANCE!!!");
							++flag;
						}
					}
					else 
					{
						System.out.println("AMOUNT INVALID!!!");
						++flag;
					}
				}while(flag==1);
				
			}
			else
			{
				System.out.println("INVALID MOBILE NUMBER");
			}


			stmt2.close();
			con2.close();
		}catch(Exception e) { System.out.println(e);}

		//TRANSACTION COMPLETED
		AvailableServices availableservices = new AvailableServices();
		availableservices.availableServices(login_mobile_number);
		
		return 0;
	}
	
	
//By UPI id______________	
	int byUpiId(String login_mobile_number)
	{
		System.out.println("Enter Receiver UPI id");
		receiver_upi=sc.next();
		try
		{
			Class.forName(Start.driver);
			Connection con2=DriverManager.getConnection(Start.url, Start.user, Start.password);
			Statement stmt2=con2.createStatement();
			ResultSet rs2=stmt2.executeQuery("select * from customerinfo");

			while(rs2.next())
			{	
				if(receiver_upi.equals(rs2.getString("customer_upi")))
				{
					match=true;
					receiver_balance=rs2.getInt("customer_balance");
					String receiver_name = rs2.getString("customer_name");	
					System.out.println("Account holder name\t:"+receiver_name);
					
					System.out.println("Do you want to continue transaction (y/n): ");
					String y = sc.next();
					if(y.equals("n") || y.equals("N"))
					{
						System.out.println("TRANSACTION CANCELED....\n");
						//TRANSACTION COMPLETED
						AvailableServices availableservices = new AvailableServices();
						availableservices.availableServices(login_mobile_number);
					}
					
					
				}
				if(login_mobile_number.equals(rs2.getString("customer_mobile_number")))
				{
					sender_balance=rs2.getInt("customer_balance");
				}
				
				
			}
			
			
			if(match)
			{
				
				int flag;
				do
				{
					flag=0;
					System.out.println("Enter Amount");
					amt=sc.nextInt();
					if(amt>0)
					{
						if(sender_balance-amt>=0)
						{
							receiver_balance = receiver_balance + amt;
							sender_balance = sender_balance - amt;
							String query1="update customerinfo set customer_balance="+(sender_balance)+" where customer_mobile_number="+login_mobile_number;
							String query2="update customerinfo set customer_balance="+(receiver_balance)+" where customer_upi="+"\""+receiver_upi+"\"";
							
							stmt2.executeUpdate(query1);
							stmt2.executeUpdate(query2);
							System.out.println("TRANSACTION SUCCESSFUL!!!!\n");
							

							
							
						}
						else
						{
							System.out.println("INSUFFICIENT BALANCE!!!");
							++flag;
						}
					}
					else 
					{
						System.out.println("AMOUNT INVALID!!!");
						++flag;
					}
				}while(flag==1);
				
			}
			else
			{
				System.out.println("INVALID UPI ID");
			}
			


			stmt2.close();
			con2.close();
		}catch(Exception e) { System.out.println(e);}

		//TRANSACTION COMPLETED
		AvailableServices availableservices = new AvailableServices();
		availableservices.availableServices(login_mobile_number);
		
		return 0;
	}

}
