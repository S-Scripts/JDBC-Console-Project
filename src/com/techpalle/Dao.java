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
	        
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/palle", "root", "root@123");
	        
	        s = c.createStatement();
	        
	        // Check if the table already exists
	        ResultSet rs = s.executeQuery("SHOW TABLES LIKE 'employee'");
	        if (rs.next()) {
	            System.out.println("Note: The table 'employee' already exists in the database 'palle'.");
	        } else {
	            s.executeUpdate("CREATE TABLE employee (eid INT PRIMARY KEY, ename VARCHAR(40), esal INT)");
	            System.out.println("Table 'employee' successfully created!");
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


	public void createRow(int eid, String ename, int esal) {

		
		Connection c = null;
		
		PreparedStatement s = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/palle","root", "root@123");
			
			String query="insert into employee values(?,?,?)";
			s = c.prepareStatement(query);
			s.setInt(1, eid);
			s.setString(2, ename);
			s.setInt(3, esal);
			
			s.executeUpdate();
			
			System.out.println("Values added successfully! \n");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Driver not loaded properly \n");
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		catch (SQLException e) {
			System.out.println("Something went wrong with database \n");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("Something unsually thing happened \n");
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

	public void updateRow(int eid, int newsal) {
		
		Connection c = null;
		PreparedStatement s = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/palle", "root", "root@123");
			
			String query = "update employee set esal = ? where eid = ?";
			s = c.prepareStatement(query);
			s.setInt(1, newsal);
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
			
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/palle", "root", "root@123");
			
			String query = "delete from employee where eid = ?";
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
			
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/palle", "root", "root@123");
			
			s = c.createStatement();
			
			ResultSet res = s.executeQuery("select * from employee");
			
			System.out.println("Employee table data below \n");
			System.out.println("eid  ename    esal \n");
			
			while(res.next()) {
				System.out.println(res.getInt(1)+ "  " + res.getString(2)+ "  " + res.getInt(3));
			}
			System.out.println("------------------------------------");
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
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/palle", "root", "root@123");
	        
	        String query = "SELECT * FROM employee WHERE eid = ?";
	        s = c.prepareStatement(query);
	        s.setInt(1, eid);
	        
	        ResultSet res = s.executeQuery();
	        
	        if (res.next()) {
	            System.out.println("Employee found:");
	            System.out.println("eid: " + res.getInt(1) + ", ename: " + res.getString(2) + ", esal: " + res.getInt(3));
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

