import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileManagement {

	
	@SuppressWarnings("unused")
	public static ArrayList<UserClass> GetUsers()
	{
		ArrayList<UserClass> Users = new ArrayList<UserClass>();
		
	   try
	   {
		   FileInputStream fin = new FileInputStream("users.dbml");
		   ObjectInputStream Oin = new ObjectInputStream(fin);
		   int a = -1 ;
		   
		      while(( a = fin.available()) > 0)
		      {
			   try {
				UserClass newMember = (UserClass)Oin.readObject();
				
				Users.add(newMember);
				  
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
		      }
			
		    try
			{
				if(fin != null)
				{
					fin.close();
					if(Oin != null)
					{
						Oin.close();
					}
				}
			}catch(IOException ex)
			{
				ex.printStackTrace();
			}
		   
	   }catch(IOException ex)
	   {
		   ex.printStackTrace();
	   }
		
		
		
		return Users;
		
	}
	public static String GetUserImage()
	{
		String Results = "";
		
		File nF = new File("file.dat");
		try {
			@SuppressWarnings("resource")
			Scanner scn = new Scanner(nF);
			
		    
		   
		    Results = scn.nextLine();
		    	
		   
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Results;
	}
	public static StringTokenizer GetUserFile()
	{
		StringTokenizer TokenReturn = null;
		File nF = new File("Usr.dat");
		try {
			@SuppressWarnings("resource")
			Scanner scn = new Scanner(nF);
			String Results = "";
		    
		   
		    Results = scn.nextLine();
		    	
		   
			TokenReturn = new StringTokenizer(Results," ");
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(@SuppressWarnings("hiding") IOException e)
		{
			e.printStackTrace();
		}
		
		return TokenReturn;
	}
	public static void WriteFile(String ImageAdress)
	{
		File nF = new File("file.dat");
		try {
			FileOutputStream in = new FileOutputStream(nF);
			String bind = ImageAdress;
			in.write(bind.getBytes());
			
			if(in != null)
			{
				in.close();
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}	
	}
	public static void WriteToken(String Username,String Password)
	{
		File nF = new File("Usr.dat");
		try {
			FileOutputStream in = new FileOutputStream(nF);
			String bind = Username + " " + Password;
			in.write(bind.getBytes());
			
			if(in != null)
			{
				in.close();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void WriteData(UserClass newUser)
	{
		try
		{
			 boolean exists = new File("users.dbml").exists();
			FileOutputStream fout = new FileOutputStream("users.dbml",true);
			
			 
			ObjectOutputStream dout =exists ?  new ObjectOutputStream(fout) {
                protected void writeStreamHeader() throws IOException {
                    reset();
                }
            }  : new ObjectOutputStream(fout);
			dout.writeObject(newUser);
			dout.flush();
			try
			{
				if(fout != null)
				{
					fout.close();
					if(dout != null)
					{
						dout.close();
					}
				}
			}catch(IOException ex)
			{
				ex.printStackTrace();
			}
			
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
	 	
	}
}
