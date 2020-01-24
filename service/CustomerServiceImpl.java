package com.robomq.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.robomq.dao.DBConnection;
import com.robomq.pojo.Customer;

public class CustomerServiceImpl implements CustomerService {
	Connection con;
	PreparedStatement pre,pre1;
	int ra;
	ResultSet res, res1;
	boolean flag=false;
	int update=0;
	Customer c;
	private String newemail, newmobile, newaddress;

	
	Scanner sc;
	public CustomerServiceImpl()
	{	c = new Customer();
		con=DBConnection.getConnection();
		sc = new Scanner(System.in);
	}

	public boolean createCustomer(Customer c)
	{
		try {
			
			pre=con.prepareStatement("insert into customer11 values(?,?,?,?,?)");
			pre.setInt(1,c.getId());
			pre.setString(2,c.getName());
			pre.setString(3,c.getEmail());
			pre.setString(4,c.getAddress());
			pre.setString(5,c.getMobileno());
			
			ra=pre.executeUpdate();
			if(ra>0) //checking query is executed or not
					flag=true;
			else
				flag=false;
			} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public boolean loginCustomer(int cid, String cmobile)
	{
		try {
			pre=con.prepareStatement("select * from customer11 where customer_id=? and customer_mobile=?");
			pre.setInt(1,cid);
			pre.setString(2, cmobile);
			res=pre.executeQuery();
			if(res.next()) {
				flag = true;
				}
			else
				flag = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return flag;
	}
	
	public ResultSet displayCustomer(int cid)
	{
		try
		{
			pre=con.prepareStatement("select * from customer11 where customer_id=?");
			pre.setInt(1,cid);
			res1=pre.executeQuery();
			if(res1.next())
			{	
			System.out.println("Customer ID :"+res1.getInt("customer_id"));
			System.out.println("Customer Name :"+res1.getString("customer_name"));
			System.out.println("Customer Email :"+res1.getString("customer_email"));
			System.out.println("Customer Address :"+res1.getString("customer_address"));
			System.out.println("Customer Mobile :"+res1.getString("customer_mobile"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return res1;
	}

	public void deletePreStatement(int cid)
	{
		try
		{
			pre=con.prepareStatement("delete from customer11 where customer_id=?");
			pre.setInt(1,cid);
			
			int ra=pre.executeUpdate();
			if(ra>0)
				System.out.println("Record Deleted whose "+cid);
			else              
				System.out.println("Record has not Deleted...");
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public ResultSet updateCustomer(int cid) {
		try
		{
			pre=con.prepareStatement("select * from customer11 where customer_id=?");
			pre.setInt(1,cid);
			res=pre.executeQuery();
			if(res.next())
			{
			newemail = res.getString("customer_email");
			newaddress = res.getString("customer_address");
 			newmobile = res.getString("customer_mobile");
 			System.out.println("\n Do you want to update: Press 1 for YES and 2 for No ");
			update = sc.nextInt();
			if(update==1)
			{
				System.out.println("Do you want to update email :Press 1 for YES and 2 for No  ");
				if (sc.nextInt()==1) 
				{
					System.out.println("Enter Customer email.");
					newemail = sc.next();
				}
				System.out.println("Do you want to update address :Press 1 for YES and 2 for No  ");
				if (sc.nextInt()==1)
				{
					System.out.println("Enter Customer address.");
					newaddress = sc.next();
				}
				System.out.println("Do you want to update mobile :Press 1 for YES and 2 for No  ");
				if (sc.nextInt()==1)
				{
					System.out.println("Enter Customer mobile.");
					newmobile = sc.next();
				}
				pre1=con.prepareStatement("update customer11 set customer_email=? , customer_address=?, customer_mobile=? where customer_id=?");
				pre1.setString(1,newemail);
				pre1.setString(2,newaddress);
				pre1.setString(3,newmobile);
				pre1.setInt(4,res.getInt("customer_id"));
					
				int ra=pre1.executeUpdate();
				if(ra>0)
				System.out.println("Record updated for id = "+res.getInt("customer_id"));
				else
				System.out.println("Record is not updated..");
				pre1.close();
				
				
			}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
		return res;
	}
}
