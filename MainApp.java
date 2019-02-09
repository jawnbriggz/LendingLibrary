import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Connection;

public class MainApp{
	
	public static void main(String[] args){
		try{
			Connection conn = Connect.getConnection();
		
			Scanner input = new Scanner(System.in);
			
			boolean running = true;
			int selection;
			
			while(running){
				displayMainMenu();
				selection = input.nextInt();
				
				if(selection == 0){
					
				}
				else if(selection == 1){
					Library libView = new Library(input);
					libView.runLibrary(conn);
				}
				else if(selection == 2){
					
				}
				else if(selection == 3){
					
				}
				else if(selection == 4){
					running = false;
					System.out.print("Goodbye!");
				}
			}
			
			
			
			
			conn.close();
		}
		catch(SQLException e){
			e.getMessage();
		}
	}
	
	public static void displayMainMenu(){
		System.out.println("-----MENU-----" + "\n");
		System.out.println("" +
				"0. Register New User" + "\n" +
				"1. View Libraries" +	"\n" +
				"2. Search Books" + "\n" +
				"3. myLending Records" + "\n" +
				"4. Exit");
		
		System.out.println("\n" + "---make-a-selection---");
	}
	
	
}