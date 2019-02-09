import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Library{
	
	private Scanner input;
	
	public Library(){
		
	}
	
	public Library(Scanner in){
		this.input = in;
	}
	
	public void runLibrary(Connection conn){
		boolean running = true;
		int selection;
		
		while(running){	
			displayMenu();
			selection = input.nextInt();
			
			if(selection == 1){
				System.out.println("Well fuck");
			}
			else if(selection == 2){
				try{
					displayAllLibraries(conn);
				}
				catch(SQLException e){
					e.getMessage();
				}
			}
			else if(selection == 3){
				running = false;
			}
		}
	}
	
	
	private static void displayMenu(){
		System.out.println("----What do you want to do?----" + "\n\n" +
				"1. View myLibrary" + "\n" +
				"2. View All Libraries" + "\n" +
				"3. Go Back");
		
		System.out.print("----make a selection----");
	}
	
	public static void displayAllLibraries(Connection conn) throws SQLException{
		String sql = "SELECT * FROM Library";
		
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery(sql);
		String title, author, libraryName, availability, genre, transactionID;
		
		while(result.next()){
			title = result.getString(1);
			author = result.getString(2);
			libraryName = result.getString(3);
			availability = result.getString(4);
			genre = result.getString(5);
			transactionID = result.getString(6);
			
			System.out.println(title + " \n" +
					author + " \n" +
					libraryName + " \n" +
					availability + " \n" +
					genre + " \n" +
					transactionID + " \n");
		}
	}
}