package jdbcexample;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {


	public Connection connectJDBC()
	{
		Connection c = null;
		String path = System.getProperty("user.dir");
		System.out.println("The path is " + path);
		try {
			// Call Class.forName(``org.sqlite.JDBC") to dynamically 
			// load the class named ``org.sqlite.JDBC", which registers a JDBC driver
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return c;		
	}

	public boolean saveRecord(Connection connection, String FName, String LName, String email)
	{
		Connection c = connection;
		
		try {
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

			String add_address = "INSERT INTO ADDRESSBOOK (FIRSTNAME,LASTNAME,ADDRESS) " +
					"VALUES ('" + FName + "', '" + LName + "', '"+  email + "');";
			System.out.println(add_address);
			stmt.executeUpdate(add_address);

			stmt.close();
			c.commit();
			c.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return true;

	}

}
