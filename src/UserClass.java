import java.io.Serializable;


public class UserClass implements Serializable{
	private static final long serialVersionUID = 1L;

private String Username;
private String Surname;
private String Email;
private String Password;
private String UserLinq;
private String State;
private String UserID;
private String ImageAddress;

public String getImageAddress()
{
	return ImageAddress;
}
public String getNames()
{
	String word = Username +" "+ Surname;
	return word;
}

public String getLinq()
{
	return UserLinq;
}
public String GetEmail()
{
return Email;	
}
public boolean Matchlinq(String Linq)
{
	if(Linq.equals(UserLinq))
		return true;
	
	return false;
}
public String getID()
{
	return this.UserID;
}

public UserClass(String user,String Pass,String Surn,String Emai,String ImageAddres)
{
	Password = Management.EncriptPassword(Pass);
	Username = user;
	UserLinq ="http://qroauth.co.za/" +Management.GenerateUserling();
	State = "User";
	Email=Emai;
	Surname = Surn;
	UserID = Management.GetID();
	ImageAddress = ImageAddres;
}
public void AddAdmin()
{
	State = "Admin";
}

public boolean getAdmin()
{
	System.out.println(State);
	if(State.startsWith("Admin"))
	{
		return true;
	}
	
	return false;
}

public  boolean Check_User(String Usernamen , String Passwordn)
{
	if(Usernamen.equals(Username) && Management.EncriptPassword(Passwordn).equals(Password))
	{
		return true;
	}
	return false;
}

public void PrintUser()
{

	System.out.println("Linq :"+UserLinq);	
}
}
