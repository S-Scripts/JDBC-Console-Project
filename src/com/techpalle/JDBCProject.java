/*
 * Project Name : JDBC Console Project
 * Description : The main purpose of this project is to explore 
                 JDBC API's for performing DML operation. 
 */
package com.techpalle;

import java.util.Scanner;

public class JDBCProject {

	public static void main(String[] args) {
		Dao d = new Dao();
		Scanner sc = new Scanner(System.in);
		
		int option = 0;
		System.out.println("JDBC Console Project Welcomes You! \n");
		
		do {
            System.out.println("Choose correct option");
            System.out.println("1 : Creating a table");
            System.out.println("2 : Inserting data into table");
            System.out.println("3 : Update data");
            System.out.println("4 : Delete row in the table");
            System.out.println("5 : Read all rows");
            System.out.println("6 : Read particular row based on employee id");
            System.out.println("0 : Exit \n");
            
            option = sc.nextInt();
            sc.nextLine(); // consume the newline character

            switch(option) {
                case 0: 
                    System.out.println("Exiting..");
                    try {
                        Thread.sleep(2000);
                    } 
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Excited!");
                    return;

                case 1:
                    d.creatTable();
                    break;

                case 2:
                    System.out.println("Enter employee id:");
                    int eid = sc.nextInt();
                    sc.nextLine(); // consume the newline character

                    System.out.println("Enter employee name:");
                    String ename = sc.nextLine();

                    System.out.println("Enter employee salary:");
                    int esal = sc.nextInt();

                    d.createRow(eid, ename, esal);
                    break;

                case 3:
                    System.out.println("Enter employee id to update:");
                    int updateId = sc.nextInt();

                    System.out.println("Enter new salary:");
                    int newsal = sc.nextInt();

                    d.updateRow(updateId, newsal);
                    break;

                case 4:
                    System.out.println("Enter employee id to delete:");
                    int deleteId = sc.nextInt();

                    d.deleteRow(deleteId);
                    break;

                case 5:
                    d.readRows();
                    break;

                case 6:
                    System.out.println("Enter employee id to read:");
                    int readId = sc.nextInt();

                    d.readRow(readId);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while(option != 0);

        sc.close();

	}

}
