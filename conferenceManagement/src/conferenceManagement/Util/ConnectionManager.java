package conferenceManagement.Util;

import java.sql. * ;
public class ConnectionManager 
{

	private static Connection conn=null;
	
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/conference_manage?useUnicode=true&characterEncoding=utf8","root","");
			System.out.println("Connection Success!");
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection(){
		if(conn!=null){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
}
