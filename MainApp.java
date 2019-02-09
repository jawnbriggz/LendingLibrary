import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   


public class MainApp{
	
	public static Scanner input = new Scanner(System.in);
	public static int trans = 0;
	public static String currUserLib;
	public static String currUsername;
	
	public static void main(String[] args){
		try{
			Connection conn = Connect.getConnection();
			DBOps.conn = conn;

			boolean running = true;
			int selection;
		
			boolean valid = false;
			String nameInput;
			String passInput;
			
			while(!valid){
				System.out.println("Log in: " + "\n");
				System.out.println("username: ");
				nameInput = input.nextLine();
				System.out.println("password: ");
				passInput = input.nextLine();
				valid = DBOps.checkUser(nameInput, passInput);
				
				if(valid){
					currUserLib = DBOps.getUserInfo(nameInput);
					currUsername = nameInput;
				}
				else{
					System.out.println("That user is not registered with us");
				}
			}
			
			while(running){
				
				displayMainMenu();
				selection = input.nextInt();
				
				if(selection == 1){
					registerUser();
				}
				else if(selection == 2){
					bookMenu();
				}
				else if(selection == 3){
					myCollection();
				}
				else if(selection == 4){
					lendingRecords();
				}
				else if(selection == 5){
					libraryInfo();
				}
				else if(selection == 6){
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
				"1. Register New User" + "\n" +
				"2. Search Books" +	"\n" +
				"3. My Collection" + "\n" +
				"4. My Records" + "\n" +
				"5. View Libraries" + "\n" +
				"6. Exit");
				
		
		System.out.println("\n" + "---make-a-selection---");
	}
	
	public static void bookMenu(){
		String keyword;
		int selection = 0;
		boolean back = false;
		String attribute;
		
		while(!back){
			System.out.println("Search by: \n"
					+ "1. Title\n"
					+ "2. Author\n"
					+ "3. Genre\n"
					+ "4. Library\n"
					+ "5. Reserve a book\n"
					+ "6. Go Back");
			
			
			selection = input.nextInt();
			input.nextLine();
			
			try{
				if(selection == 1){
					attribute = "title";
					System.out.println("Enter a title: ");
						keyword = input.nextLine();
					DBOps.selectBooks(keyword, attribute);
				}
				else if(selection == 2){
					attribute = "author";
					System.out.println("Enter an author: ");
						keyword = input.nextLine();
					DBOps.selectBooks(keyword, attribute);
				}
				else if(selection == 3){
					attribute = "genre";
					System.out.println("Enter an genre: ");
						keyword = input.nextLine();
					DBOps.selectBooks(keyword, attribute);
				}
				else if(selection == 4){
					attribute = "libraryName";
					System.out.println("Enter a library name: ");
						keyword = input.nextLine();
					DBOps.selectBooks(keyword, attribute);
				}
				else if(selection == 5){
					reserve();
				}
				else if(selection == 6){
					back = true;
				}
				else{
					System.out.println("invalid entry; please make a selection");
				}
			}
			catch(SQLException e){
				System.out.print(e.getMessage());
			}
		}
	}
	
	public static void lendingRecords(){
		
		String keyword;
		int selection = 0;
		boolean back = false;
		String attribute;
		String user;
		
		while(!back){
			System.out.println("1. Books Loaned\n" + "2. Books Checked Out\n" + "3. Go Back");
			selection = input.nextInt();
			
			if(selection == 1){
				DBOps.selectLending(currUserLib, "libraryName");
			}
			else if(selection == 2){
				DBOps.selectLending(currUsername, "userName");
			}
			else if(selection == 3){
				back = true;
			}
			else{
				System.out.println("invalid entry; please make a selection");
			}
		}
		
	}
	
	public static void myCollection(){
		boolean back = false;
		int selection = 0;
		
		while(!back){
			System.out.println("1. add to my collection" + "\n2. view my collection" 
					+ "\n3. remove from my collection"+ "\n4. Go Back");
			selection = input.nextInt();
			
			if(selection == 1){
				addToCollection();
			}
			else if(selection == 2){
				try{
				DBOps.selectBooks(currUserLib, "libraryName");
				}
				catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
			else if(selection == 3){
				input.nextLine();
				System.out.print("Enter the title to delete: ");
				String deleteThis = input.nextLine();
					DBOps.deleteBooks(deleteThis);
			}
			else if(selection ==4){
				back = true;
			}
			else{
				System.out.println("Invalid entry; please make a selection");
			}
		}
		
	}
	
	public static void addToCollection(){
		String title;
		String author;
		String genre;
		int transactionID = trans + 111;
		input.nextLine();
		
		System.out.println("----Add a book----"
				+ "\n" + "Title: ");
			title = input.nextLine();
			
		System.out.println("Author: ");
			author = input.nextLine();
			
		System.out.println("Genre: ");
			genre = input.nextLine();
		
		try{
		DBOps.insertBook(title, author, genre, currUserLib, "available", transactionID);
		}
		catch(SQLException e){
			System.out.print(e.getMessage());
		}
	}
	
	public static void registerUser() throws SQLException{
		boolean back = false;
		String userName;
		String password;
		String libraryName;
		String address;
		input.nextLine();
		
		while(!back){
			System.out.println("----Register a new user----");
			
			System.out.println("enter your library's name (e.g. Joe K's library): ");
				libraryName = input.nextLine();
			
			System.out.println("Where is your library located? (address)");	
				address = input.nextLine();
				
			System.out.println("choose a username: ");
				userName = input.nextLine();
			
			System.out.println("choose a password: ");
				password = input.nextLine();
			
			DBOps.insertLibrary(libraryName, address, 0, "");
			DBOps.insertUser(userName, password, libraryName);
			back = true;
		}
	}
	
	public static void libraryInfo(){
		DBOps.selectLibraries();
	}
	
	public static void reserve(){
		boolean back = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  
		String due = dtf.format(now);
		
		while(!back){
			System.out.println("----Reserve a title----");
			System.out.println("1. Enter a title");
			System.out.println("2. Go Back");
			int selection = input.nextInt();
			
			if(selection == 1){
				input.nextLine();
				System.out.println("Enter a title: ");
					String tit = input.nextLine();
					
				System.out.println("From which library?: ");
					String lib = input.nextLine();
				try{	
				trans += 10;	
				DBOps.insertLending(trans, lib, dtf.format(now), due, currUsername);	
				DBOps.updateBooks("checked out", tit, lib);	
				}
				catch(SQLException e){
					System.out.print(e.getMessage());
				}
			}
			else if(selection == 2){
				back = false;
			}
			else{
				
			}
		}
	}
}