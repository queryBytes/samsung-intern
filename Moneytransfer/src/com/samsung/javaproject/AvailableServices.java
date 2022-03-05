package com.samsung.javaproject;
import java.util.*;

public class AvailableServices {
	
	
	void availableServices(String login_mobile_number)
	{
		boolean t=false;
		do
		{
			
			int n;
			Scanner sc = new Scanner(System.in);
			CustomerInfo customerinfo = new CustomerInfo();
			AmountTransfer amounttransfer = new AmountTransfer();
			
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
				System.out.println("INVALID INPUT!!!");
				t=true;
			}
			
		}while(t);
	}
}
