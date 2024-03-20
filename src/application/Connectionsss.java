package application;
import java.sql.*;
public class Connectionsss {
	public static  Connection mycon ;
	public static void Connect() {
		String connectionurl="jdbc:mysql://localhost:3306/main project";
		String user ="root";
		String password="";
		try {
			
			mycon= DriverManager.getConnection(connectionurl,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
}
