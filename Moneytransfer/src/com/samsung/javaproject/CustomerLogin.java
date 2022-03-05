package com.samsung.javaproject;

import java.util.Scanner;

public class CustomerLogin {
	
	void customerLogin(String login_mobile_number) throws Exception
	{

		String[] s=new String[1];
		int n;
		boolean login=false;
		Scanner sc = new Scanner(System.in);
		LoginValidation loginvalidation = new LoginValidation();
		CustomerInfo customerinfo = new CustomerInfo();
		AmountTransfer amounttransfer = new AmountTransfer();
		

		if(loginvalidation.loginValidation(login_mobile_number))
		{
			
			System.out.println("login successful!!!!");
			login=true;

		}
		else
		{
			System.out.println("WARNING!!!.....\nMobile number incorrect...Try Again");
			Start.main(s);
			
		}
		
		if(login)
		{
			boolean t=false;
			do
			{
				
				System.out.println("Select option");
				System.out.println("1. Account detail");
				System.out.println("2. Balance enquery");
				System.out.println("3. Money transfer");
				System.out.println("4. LOGOUT");
				n=sc.nextInt();
				
				switch(n)
				{
				case 1:
					customerinfo.customerInfo(login_mobile_number);
					break;
				case 2:
					customerinfo.balanceEnquery(login_mobile_number);
					break;
				case 3:
					amounttransfer.amountTransfer(login_mobile_number);
					break;
				case 4:
					System.out.println("LOGOUT SUCCESSFULLY!!!");
					System.exit(0);
				default:
					System.out.println("Wrong choice....\n");
					t=true;
				}
				
			}while(t);
		}
	}

}
