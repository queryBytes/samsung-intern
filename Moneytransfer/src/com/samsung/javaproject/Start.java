/* naming format
 * 
 * 
 * class name=== ClassName
 * method name===== methodName
 * variable name==== variable_name
 * database variable name==== variable_name
 * database name === same as classname
 * object name===== objectname or classname
 * 
 * 
 * Database name === MoneyTransferLogin
 * table bankinfo
 * table customerinfo
 * 
 */

package com.samsung.javaproject;
import java.sql.*;
import java.util.*;


public class Start {
	
// Database connection_____________________________
	static String driver="com.mysql.cj.jdbc.Driver";
	static String url="jdbc:mysql://localhost:3306/MoneyTransferLogin";
	static String user="root";
	static String password="chintook";
	
	
//getting bank information__________________________	
	static void bankInfo() throws Exception
	{
	
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url, user, password);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from bankinfo");

			while(rs.next())
			{
				BankInfo.bank_name=rs.getString("bank_name");
				BankInfo.bank_ifsc=rs.getString("bank_ifsc");
				BankInfo.bank_branch=rs.getString("bank_branch");
				System.out.println(rs.getString("bank_name"));		
			}
			
			stmt.close();
			con.close();

		}catch(Exception e) { System.out.println(e);}
	}
	

//getting Customer Address_____________________________
	static void customerinfo() throws Exception
	{	
		BankInfo bankinfo=new BankInfo();
		bankinfo.customerinfo = new CustomerInfo();
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url, user, password);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from customerinfo");

			while(rs.next())
			{
				bankinfo.customerinfo.customer_name=rs.getString("customer_name");
				//bankinfo.customerinfo.customer_mobile_number=rs.getString("customer_mobile_number");
				//bankinfo.customerinfo.customer_email_address=rs.getString("customer_email_address");
				//bankinfo.customerinfo.customer_upi=rs.getString("customer_upi");
				//bankinfo.customerinfo.customer_account_number=rs.getString("customer_account_number");
				//bankinfo.customerinfo.customer_city=rs.getString("customer_city");
				//bankinfo.customerinfo.customer_pincode=rs.getString("customer_pincode");
				//bankinfo.customerinfo.customer_street_address=rs.getString("customer_street_address");
				System.out.print(rs.getString("customer_name"));
				System.out.println(rs.getString("customer_email_address"));
			}
			stmt.close();
			con.close();
		}catch(Exception e) { System.out.println(e);}
	}

//=======================Main method or program start from here================
	public static void main(String[] args) throws Exception  
	{
		CustomerLogin customerlogin = new CustomerLogin();
		Scanner sc = new Scanner(System.in);
		System.out.println("=========!!!!Enter Mobile Number for login!!!!===========");
		String login_mobile_number=sc.next();
		customerlogin.customerLogin(login_mobile_number);
	}
}
