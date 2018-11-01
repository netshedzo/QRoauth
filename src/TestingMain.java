

public class TestingMain {

	public static void main(String[] args){
		   //Adding a new Admin
			UserClass myClass = new UserClass("Davis","12345","Ralinala","mualalo022@gmail.com","mulalo.jpg");
			myClass.AddAdmin();
			FileManagement.WriteData(myClass);
			
	
		}

}
