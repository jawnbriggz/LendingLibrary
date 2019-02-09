import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA{
	
	private String password;
	
	public SHA(String password){
		try{
			this.password = hash(password);
		}
		catch(NoSuchAlgorithmException e){
			e.getMessage();
		}
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public static String hash(String password) throws NoSuchAlgorithmException{
		String result;
		StringBuilder sb = new StringBuilder("");
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hashed = md.digest(password.getBytes());
		char fin = 'a';
		
		int i=0;
		while(i<hashed.length){
			Byte b = new Byte(hashed[i]);
			int uni = b.intValue();
			fin = (char) uni;
			sb.append(fin);
			i++;
		}
		System.out.println(sb.toString().length());
		result = sb.toString();
		return result;
	}
}