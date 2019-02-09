import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   

public class DBOps{
	
	public static Connection conn;
	
	public static boolean getConnection(){
		try{
			conn = Connect.getConnection();
		}
		catch(SQLException e){
			e.getMessage();
		}
		if(conn != null){
			return true;
		}
		return false;
	}
	
	public static String getUserInfo(String name) throws SQLException{
		String library = "the";
		String sql = "SELECT libraryName FROM User WHERE userName = '" + name + "';";
		
		Statement statement = conn.createStatement();
		
		try{
			ResultSet result = statement.executeQuery(sql);
			result.next();
			library = result.getString(1);
		}
		catch(SQLException e){
			System.out.print(e.getMessage());
		}

		return library;
	}
	
	public static boolean checkUser(String name, String pass) throws SQLException{
		
		String sql = "SELECT COUNT(*) FROM User WHERE userName = '" + name + "' AND password = '" + pass + "';";
		
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		result.next();
		int a = result.getInt(1);
		
		if(a > 0){
			return true;
		}
		return false;
	}
	
	public static void selectBooks(String keyword, String attribute) throws SQLException{
		String sql = "SELECT * FROM Books WHERE " + attribute + " LIKE '%" + keyword + "%';";
		

		try{
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

		while(result.next()){
			String title = result.getString(1);
			String author = result.getString(2);
			String genre = result.getString(3);
			String libraryName = result.getString(4);
			String availability = result.getString(5);
			int transactionID = result.getInt(6);
			
			
			System.out.println(title + " - " + author + " - " + genre + " - " + libraryName + " - "
					+ availability + " - " + transactionID);
			System.out.println();
		}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}

	}
	
	public static void deleteBooks(String title){
		String sql = "DELETE FROM Books WHERE title = ?;";

		try{
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, title);
		statement.executeUpdate(); 
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		System.out.println(title + " has been deleted.");
	}
	
	public static void insertBook(String title, String author, String genre, String libraryName, String availability, int transactionID) throws SQLException{
		String sql = "INSERT INTO Books VALUES(?, ?, ?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		int rowsInserted;
		
		statement.setString(1, title);
		statement.setString(2, author);
		statement.setString(3, genre);
		statement.setString(4, libraryName);
		statement.setString(5, availability);
		statement.setInt(6, transactionID);
		
		try{
			rowsInserted = statement.executeUpdate();
			
			if (rowsInserted > 0) {
			    System.out.println(title + " was added to your collection");
			}
			}
			catch(SQLException e){
				System.out.print(e.getMessage());
			}
	}
	
	public static void insertUser(String userName, String psswrd, String libraryName) throws SQLException{
		String sql = "INSERT INTO User VALUES(?, ?, ?)";
		
		SHA sha = new SHA(psswrd);
		PreparedStatement statement = conn.prepareStatement(sql);
		int rowsInserted;
		
		statement.setString(1, userName);
		statement.setString(2, sha.getPassword());
		statement.setString(3, libraryName);

		try{
		rowsInserted = statement.executeUpdate();
		
		if (rowsInserted > 0) {
		    System.out.println("A new user was registerd");
		}
		}
		catch(SQLException e){
			System.out.print(e.getMessage());
		}

	}
	
	public static void insertLibrary(String libName, String address, int num, String gen) throws SQLException{
		String sql = "INSERT INTO Library VALUES(?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		int rowsInserted;
		
		statement.setString(1, libName);
		statement.setString(2, address);
		statement.setInt(3, num);
		statement.setString(4, gen);
		
		try{
			rowsInserted = statement.executeUpdate();
			
			if (rowsInserted > 0) {
			    System.out.println("!");
			}
			}
			catch(SQLException e){
				System.out.print(e.getMessage());
			}
	}
	
	public static void selectLibraries(){
		String sql = "SELECT * FROM Library;";
		

		try{
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

		while(result.next()){
			String libName = result.getString(1);
			String address = result.getString(2);
			String numBooks = result.getString(3);
			String genre = result.getString(4);
			
			System.out.println("NAME: " + libName + " ADDRESS: " + address + " BOOKS: " + numBooks + " POPULAR GENRE: " + genre);

			System.out.println();
		}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}

	}
	
	public static void selectLending(String keyword, String attribute){
		String sql = "SELECT * FROM Lending WHERE " + attribute + " = '" + keyword + "';";
		

		try{
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

		while(result.next()){
			String actionId = result.getString(1);
			String lib = result.getString(2);
			String dateBorrowed = result.getString(3);
			String dateDue = result.getString(4);
			String borrower = result.getString(5);
			
			System.out.println("lending ID#: " + actionId + " FROM: " + lib + " Borrowed On: " + dateBorrowed + " DUE: " + dateDue + " Borrowed by: "
					+ borrower);
			System.out.println();
		}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}

	}
	
	public static void updateBooks(String reserve, String title, String lib) throws SQLException{
		String sql = "UPDATE TABLE Books SET availability = ? WHERE title = ? AND libraryName = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		int rowsInserted;
		
		statement.setString(1, reserve);
		statement.setString(2, title);
		statement.setString(3, lib);
		
		try{
			rowsInserted = statement.executeUpdate();
			
			if (rowsInserted > 0) {
			    System.out.println("!");
			}
			}
			catch(SQLException e){
				System.out.print(e.getMessage());
			}
	}
	

	
	public static void insertLending(int trans, String libName, String dBor, String dDue, String borrower) throws SQLException{
		String sql = "INSERT INTO Lending VALUES(?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		int rowsInserted;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  
		
		statement.setInt(1, trans);
		statement.setString(2, libName);
		statement.setString(3, dBor);
		statement.setString(4, dDue);
		statement.setString(5, borrower);
		
		try{
			rowsInserted = statement.executeUpdate();
			
			if (rowsInserted > 0) {
			    System.out.println("!");
			}
			}
			catch(SQLException e){
				System.out.print(e.getMessage());
			}
	}
	
}