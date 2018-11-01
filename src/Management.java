
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class Management {
	
	public static String EncriptPassword(String Encript)
	{
		String strData="";
	String	strKey = "Mulalo";
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted=cipher.doFinal(Encript.getBytes());
			strData=new String(encrypted);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return strData;
		
	}
	public static boolean LogUser(String Username,String Password)
	{
		ArrayList<UserClass> Users = FileManagement.GetUsers();
		boolean State = false;
		for(UserClass a : Users)
		{
			State = a.Check_User(Username, Password);
			if(State==true)
				return State;
		}
		
		
		return State;
	}
	
	public static UserClass getUserFlinq(String linq)
	{
		ArrayList<UserClass> Users = FileManagement.GetUsers();
		boolean State = false;
		for(UserClass a : Users)
		{
			State = a.Matchlinq(linq);
			if(State==true)
				return a;
		}
		
		
		return null;
	}
	
	public static UserClass getUser(String Username,String Password)
	{
		ArrayList<UserClass> Users = FileManagement.GetUsers();
		UserClass c = null;
		boolean State = false;
		for(UserClass a : Users)
		{
			State = a.Check_User(Username, Password);
			if(State)
				c= a;
		}
		
		
		return c;
	}
	public static String GetID()
	{
		String ID = "ID:";
		Random neR = new Random();
		@SuppressWarnings("unused")
		String Linq = "";
		for(int a = 0 ;a<13;a++)
		{
			ID += Integer.toString( neR.nextInt(9));
		}
		return ID;
	}

	public static String GenerateUserling()
	{
		char[] chars = {'A','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		
		int length = chars.length -1;
		
		Random neR = new Random();
		String Linq = "";
		for(int a = 0 ;a<18;a++)
		{
			Linq += chars[neR.nextInt(length)];
		}
		
		return Linq;
	    
	}
}
