
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Make sure to replace your database name and user and password!!!!!!!!
 */
public class Connect {

    /**
     * This method returns a connection to your database
     */
    public static Connection getConnection() throws SQLException {
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/library_db";
	String user = "root";
	String password = "password";

	try{
	Class.forName("com.mysql.jdbc.Driver");
	}
	catch(ClassNotFoundException e){
		System.out.println(e.getMessage());
	}
	conn =  DriverManager.getConnection(url, user, password);
	return conn;
    }

    public static void test(){
	try {
	    Connection conn = getConnection();
	    if (conn!=null)
	       System.out.println("Connection successful");
	    conn.close();
	}catch(SQLException e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	test();
    }
}

