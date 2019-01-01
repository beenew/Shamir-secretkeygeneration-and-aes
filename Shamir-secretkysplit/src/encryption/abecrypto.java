package encryption;
import shamir.*;

public class abecrypto {


	public static String enc(int share) {
			
		    String originalString = String.valueOf(share);
		    Encryptshare e=new Encryptshare();
		  
		
		    System.out.println("Original Share to encrypt - " + originalString);
		
		    String encryptedString = e.encrypt(originalString);
		
		    System.out.println("Encrypted Share - " + encryptedString);
		
		 return encryptedString;
		}
	
public static String dec(String encryptedString) {
	
	  Decryptshare d=new Decryptshare();
	  
  String decryptedString = d.decrypt(encryptedString);
		
	   
	return decryptedString;
}
	}
