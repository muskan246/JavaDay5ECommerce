package com.robomq.ecomUI;

import java.util.Scanner;

import com.robomq.pojo.Customer;
import com.robomq.service.CustomerService;
import com.robomq.service.CustomerServiceImpl;

public class EcommerceUI {
	boolean login;
	Customer c;
	Scanner sc;
	CustomerService service;
	private int cid,update;
	private String cmobile;
	
	public EcommerceUI()
	{
		sc=new Scanner(System.in);
		c=new Customer();
		service=new CustomerServiceImpl();
	}
	public void registerCustomer()
	{
		System.out.println("Enter Customer id.");
		c.setId(sc.nextInt());
		System.out.println("Enter Customer Name.");
		c.setName(sc.next());
		System.out.println("Enter Customer email.");
		c.setEmail(sc.next());
		System.out.println("Enter Customer address.");
		c.setAddress(sc.next());
		System.out.println("Enter Customer Mobileno.");
		c.setMobileno(sc.next());
		if(service.createCustomer(c))
			System.out.println("Customer registered successfully...");
		else
			System.out.println("Customer Not registered ...");
		
	}
	public void loginCustomer()
	{
		System.out.println("Enter Customer id.");
		cid = sc.nextInt();
		
		System.out.println("Enter Customer Mobileno.");
		cmobile = sc.next();
		
		login = service.loginCustomer(cid, cmobile);
		if (login) 
		{
			System.out.println("Login successful");
			System.out.println("Your details are:");
			service.displayCustomer(cid);
			service.updateCustomer(cid);
		}
			else
				System.out.println("Invalid details ....");			
		}
	public void deleteCustomer()
	{
		System.out.println("Enter Customer id.");
		cid = sc.nextInt();
		service.deletePreStatement(cid);
	}
		
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String ch=null;
		EcommerceUI e=new EcommerceUI();
			System.out.println("Enter Your Choice");
			System.out.println("1. Registring New Customer");
			System.out.println("2. Login as Existing Customer");
			System.out.println("3. Remove Existing Customer");
			System.out.println("4. Exit");
			ch=sc.next();
			switch(ch)
			{
				case "1":
				{
					e.registerCustomer();
					break;
				}
				case "2":
				{
					e.loginCustomer();
					break;
				}
				case "3":
				{
					e.deleteCustomer();
					break;
				}
				
				
				case "4":
	 			{	
					System.exit(0);
					break; 
				}
				default:
					System.out.println("Provide valid choices \n");
			}
		}

}
