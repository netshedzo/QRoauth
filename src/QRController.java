
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

public class QRController {
	  @FXML
	  private Parent current;
  @FXML
  private Label Report;
  @FXML
  private Label finalReport;
  @FXML
  private Label finalID;
  @FXML
  private Label finalName;
  @FXML
  private Label finalEmail;
  @FXML
  private ImageView finalUserQR;
  @FXML
  private ImageView finalUserImage;
  @FXML
  private Button SaveQR;
  @FXML
  private Button CloseButton;
  @FXML
  private Button UploadButton;
  @FXML
  private ImageView UserQR;
  @FXML
  private ImageView Webcamera;
  @FXML
  private Label UserNames;
  @FXML
  private Label UserEmails;
  @FXML
  private Label USerID;
  @FXML
  private UserClass newUser;
  @FXML
  private TextField Username;
  @FXML
  private TextField Username2;
  @FXML
  private TextField Password;
  @FXML
  private TextField UserServer;
  @FXML
  private TextField PassServer;
  @FXML
  private TextField Password2;
  @FXML
  private TextField Surname;
  @FXML
  private TextField Email;
  private  Stage qrStage = null;
  private boolean Log = false;
  @SuppressWarnings("unused")
private File UserImage;
  
  private void OpenAdmin()
  {
	  Parent Pane = null;
			try {
				Pane = FXMLLoader.load(getClass().getResource("Admin.fxml"));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   Stage qrStage = new Stage();
			Scene scene = new Scene(Pane);
			scene.getStylesheets().add("Main.css");
		    qrStage.setTitle("Portal");
		    qrStage.getIcons().add(new Image("file:/src/QRoauthIco.png"));
		  
		    qrStage.setScene(scene);
		    qrStage.show();  
  }
  
  
  private void OpenProfie()
  {
	  Parent Pane = null;
		try {
			Pane = FXMLLoader.load(getClass().getResource("Profile.fxml"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   Stage qrStage = new Stage();
		Scene scene = new Scene(Pane);
		scene.getStylesheets().add("Main.css");
	    qrStage.setTitle("Profile");
	    qrStage.getIcons().add(new Image("file:/src/QRoauthIco.png"));
	  
	    qrStage.setScene(scene);
	    qrStage.show();
  }
  
  public void CaptureCurrentCamera(ActionEvent event)
  {
	  try {
			ImageIO.write( SwingFXUtils.fromFXImage(Webcamera.getImage(), null),"png", new File("Curent.png"));
			
		String Response = QRJsonClientHandler.ReadTofile("Curent.png");
		if(Response.contains("qroauth"))
		{
			
			this.newUser = Management.getUserFlinq(Response);
			this.finalEmail.setText(this.newUser.GetEmail());
			this.finalID.setText(this.newUser.getID());
			this.finalName.setText(this.newUser.getNames());
			this.finalUserQR.setImage(new Image("https://api.qrserver.com/v1/create-qr-code/?size=400x400&bgcolor=255-255-255&color=220-20-17&data="+newUser.getLinq()));
			this.finalReport.setText("Authorize "+this.newUser.getNames());
			this.finalUserImage.setImage(new Image("file:"+this.newUser.getImageAddress()));
		}else
		{
			this.finalReport.setText("Do Not Authorize");
			this.finalEmail.setText("");
			this.finalID.setText("");
			this.finalName.setText("");
			this.finalUserQR.setImage(null);
			this.finalUserImage.setImage(null);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
  public void StartVideoCamera(ActionEvent event)
  {
	  VideoWebcam vweb = new VideoWebcam();
	  vweb.setImageView(Webcamera);
	  Thread t1 = new Thread(vweb);
	  t1.start();
  }
	public void Login(ActionEvent event)
	{
	 Log  = Management.LogUser(Username.getText(), Password.getText());
	
	if(Log)
	{
		this.newUser = Management.getUser(Username.getText(), Password.getText());
		
		FileManagement.WriteToken(Username.getText(),Password.getText());
		
		Username.setText("");
		Password.setText("");
		
		 Node source = (Node) event.getSource();
		   Stage stage = (Stage) source.getScene().getWindow();
		    stage.close();
		    if(this.newUser.getAdmin() == true)
		    {
		    	this.OpenAdmin();
		    }else
		OpenProfie();
	}else
	{
    Report.setText("Invalid Login");
    Username.setText("");
	Password.setText("");
	}
	}
	public void Register(ActionEvent event)
	{
		  Node source = (Node) event.getSource();
		   Stage stage = (Stage) source.getScene().getWindow();
		    stage.close();
		
		Parent Pane = null;
		try {
			Pane = FXMLLoader.load(getClass().getResource("Register.fxml"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 qrStage = new Stage();
		Scene scene = new Scene(Pane);
		scene.getStylesheets().add("Main.css");
	    qrStage.setTitle("Register");
	    qrStage.getIcons().add(new Image("file:/src/QRoauthIco.png"));
	  
	    qrStage.setScene(scene);
	    qrStage.show();
	   
	}
	
	public void Upload(ActionEvent event)
	{
		JFileChooser JsFile = new JFileChooser();
	
	 int a = JsFile.showOpenDialog(null);
	 switch(a)
	 {
	 case JFileChooser.APPROVE_OPTION:
	 {
		 File e = JsFile.getSelectedFile();

	    	FileInputStream instream = null;
		FileOutputStream outstream = null;
	 
	    	try{
	    	    File infile =e;
	    	    File outfile =new File(e.getName());
	 
	    	    instream = new FileInputStream(infile);
	    	    outstream = new FileOutputStream(outfile);
	 
	    	    byte[] buffer = new byte[1024];
	 
	    	    int length;
	    	  
	    	    while ((length = instream.read(buffer)) > 0){
	    	    	outstream.write(buffer, 0, length);
	    	    }

	    	  
	    	    instream.close();
	    	    outstream.close();
                this.UploadButton.setDisable(true);
                this.UploadButton.setText(e.getName()+" Uploaded");
	    	     FileManagement.WriteFile(e.getName());
	 
	    	}catch(IOException ioe){
	    		ioe.printStackTrace();
	    	 }
	 }case JFileChooser.CANCEL_OPTION:
	 {
		 
	 }
	 }
	}
	public void SaveQRMethod(ActionEvent event)
	{
		try {
			
			
			JFileChooser jchoose = new JFileChooser();
			jchoose.setDialogTitle("Save Your QR Security");
			jchoose.setSelectedFile(new File("MyQR.png"));
			
			int answer = jchoose.showSaveDialog(null);
			switch(answer)
			{
			case JFileChooser.APPROVE_OPTION:
			{
			
			 File e =jchoose.getCurrentDirectory();
			 //System.out.println(e.getPath());
			 File UserQr =  new File(e.getPath()+"\\MyQR "+FileManagement.GetUserFile().nextToken()+".png");
			 ImageIO.write( SwingFXUtils.fromFXImage(this.UserQR.getImage(), null),"png",UserQr);
			
			}case JFileChooser.CANCEL_OPTION:
			{
				
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ProfileView(ActionEvent event)
	{
		this.SaveQR.setDisable(false);
		StringTokenizer tkn =FileManagement.GetUserFile();
		newUser = Management.getUser(tkn.nextToken(),tkn.nextToken());
		
		if(newUser != null)
		{
			this.UserEmails.setText(newUser.GetEmail());
			this.USerID.setText(newUser.getID());
			this.UserNames.setText(newUser.getNames());
			this.UserQR.setImage(new Image("https://api.qrserver.com/v1/create-qr-code/?size=400x400&data="+newUser.getLinq()));
			System.out.println("Image is "+newUser.getImageAddress());
		}
		
		
	}
	public void RegisterUser(ActionEvent event)
	{
		 Node source = (Node) event.getSource();
		   Stage stage = (Stage) source.getScene().getWindow();
		    stage.close();
		    
		UserClass nR = new UserClass(Username2.getText(),Password2.getText(),Surname.getText(),Email.getText(),FileManagement.GetUserImage());
		FileManagement.WriteData(nR);
		newUser = nR;
	   FileManagement.WriteToken(Username2.getText(),Password2.getText());
		Log = true;
		this.OpenProfie();
		
		
	}
	
	
}
