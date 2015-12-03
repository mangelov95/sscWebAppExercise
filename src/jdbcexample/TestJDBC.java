package jdbcexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			// Set Auto Commit false to group its SQL statements into transactions 
			// that are ended by a call to either the commit method
			c.setAutoCommit(false);
			Statement stmt = null;
			stmt = c.createStatement();
			String sql = "CREATE TABLE ADDRESSBOOK " +					
					"(FIRSTNAME           TEXT    NOT NULL, " + 
					" LASTNAME            TEXT     NOT NULL, " + 
					" ADDRESS        CHAR(50))" ; 
			stmt.executeUpdate(sql);

			String FName = "hello";
			String LName = "hello";
			String email = "hello";
			String add_address = "INSERT INTO ADDRESSBOOK (FIRSTNAME,LASTNAME,ADDRESS) " +
					"VALUES ('" + FName + "', '" + LName + "', '"+  email + "');";
			System.out.println(add_address);
			stmt.executeUpdate(add_address);	      
			stmt.close();
			
			// Do commit now
			c.commit();
			c.close();	      

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

}
