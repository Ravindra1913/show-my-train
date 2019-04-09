package test;
import java.sql.*;

public class DBConnection 
{
	private static Connection con;
	private DBConnection() {}
	static
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB1","root","Ravindra@38");
		}
		catch(Exception e) {e.printStackTrace();}
	}
	public static Connection getCon() {
		return con;
	}

}
