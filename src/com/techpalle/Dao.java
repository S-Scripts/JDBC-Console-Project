package com.techpalle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	
	public void creatTable() {
	    
	    Connection c = null;
	    Statement s = null;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_profiles", "root", "root@123");
	        
	        s = c.createStatement();
	        
	        // To check if the table already exists
	        ResultSet rs = s.executeQuery("SHOW TABLES LIKE 'employee_details'");
	        if (rs.next()) {
	            System.out.println("Note: The table 'employee_details' already exists in the database 'user_profiles'. \n");
	        } else {
	            s.executeUpdate("CREATE TABLE employee_details (emp_id INT PRIMARY KEY, emp_fname VARCHAR(40), emp_lname VARCHAR(40), emp_phno VARCHAR(20), emp_city VARCHAR(20))");
	            System.out.println("Table 'employee_details' successfully created! \n");
	        }
	    } 
	    catch (ClassNotFoundException e) {
	        System.out.println("Driver is not properly loaded..\n");
	        e.printStackTrace();
	    } 
	    catch (SQLException e) {
	        System.out.println("Something went wrong with the database \n");
	        e.printStackTrace();
	    }
	    finally {
	        if (s != null) {
	            try {
	                s.close();
	            } 
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (c != null) {
	            try {
	                c.close();
	            } 
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}


	public void createRow(int eid, String efname, String elname, String ephno, String ecity) {
	  
	    Connection c = null;
	    PreparedStatement s = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_profiles", "root", "root@123");

	        String query = "INSERT INTO employee_details VALUES (?, ?, ?, ?, ?)";
	        s = c.prepareStatement(query);

	        s.setInt(1, eid);
	        s.setString(2, efname);
	        s.setString(3, elname);
	        s.setString(4, ephno);
	        s.setString(5, ecity);

	        s.executeUpdate();
	        System.out.println("Values added successfully! \n");
	    } 
	    catch (ClassNotFoundException e) {
	        System.out.println("Driver not loaded properly \n");
	        e.printStackTrace();
	    } 
	    catch (SQLException e) {
	        System.out.println("Something went wrong with database \n");
	        e.printStackTrace();
	    } 
	    catch (Exception e) {
	        System.out.println("Something unusual happened \n");
	        e.printStackTrace();
	    } 
	    finally {
	        if (s != null) {
	            try {
	                s.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (c != null) {
	            try {
	                c.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}


	public void updateRow(int eid, String newephno) {
		
		Connection c = null;
		PreparedStatement s = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_profiles", "root", "root@123");
			
			String query = "update employee_details set emp_phno = ? where emp_id = ?";
			s = c.prepareStatement(query);
			s.setString(1, newephno);
			s.setInt(2, eid);
			
			s.executeUpdate();
			System.out.println("Table successfully updated! \n");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Driver is not loaded properly \n");
			e.printStackTrace();
		} 
		catch (SQLException e) {
			System.out.println("Something went wrong with the database \n");
			e.printStackTrace();
		}
		finally {
			if(s!=null) {
				try {
					s.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(c!=null) {
				try {
					c.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void deleteRow(int eid) {
		Connection c = null;
		PreparedStatement s = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_profiles", "root", "root@123");
			
			String query = "delete from employee_details where emp_id = ?";
			s = c.prepareStatement(query);
			s.setInt(1, eid);
			
			s.executeUpdate();
			System.out.println("Data deleted successfully! \n");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(s!=null) {
				try {
					s.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(c!=null) {
				try {
					c.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void readRows() {
		
		Connection c =null;
		Statement s = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_profiles", "root", "root@123");
			
			s = c.createStatement();
			
			ResultSet res = s.executeQuery("select * from employee_details");
			
			System.out.println("Employee table data below \n");
			System.out.println("Employee ID       First Name     Last Name      Phone Number      City \n");
			System.out.println("---------------------------------------------------------------------- \n");
			
			while(res.next()) {
				System.out.println(res.getInt(1)+ "               " + res.getString(2)+ "         " + res.getString(3)+ "            " + res.getString(4)+ "          " + res.getString(5));
			} 
			System.out.println("\n \n");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(s!=null) {
				try {
					s.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(c!=null) {
				try {
					c.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public void readRow(int eid) {
	    Connection c = null;
	    PreparedStatement s = null;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_profiles", "root", "root@123");
	        
	        String query = "SELECT * FROM employee_details WHERE emp_id = ?";
	        s = c.prepareStatement(query);
	        s.setInt(1, eid);
	        
	        ResultSet res = s.executeQuery();
	        
	        if (res.next()) {
	            System.out.println("Employee found:");
	            System.out.println("employee_id: " + res.getInt(1) + ", first_name: " + res.getString(2) + ", last_name: " + res.getString(3) + ", phone_number: " + res.getString(4) + ", city: " + res.getString(5));
	        } else {
	            System.out.println("Employee with id " + eid + " not found.");
	        }
	    } 
	    catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	    finally {
	        if (s != null) {
	            try {
	                s.close();
	            } 
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (c != null) {
	            try {
	                c.close();
	            } 
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
}
