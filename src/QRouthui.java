
import javafx.application.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class QRouthui extends Application {
	public static void main(String[] args){
		
		   launch(args);
		
		}

	@Override
	public void start(Stage qrStage) throws Exception {
		
		Parent Pane = FXMLLoader.load(getClass().getResource("Main.fxml"));
	   
		Scene scene = new Scene(Pane);
		scene.getStylesheets().add("Main.css");
	    qrStage.setTitle("Home");
	    qrStage.getIcons().add(new Image("file:/src/QRoauthIco.png"));
	  
	    qrStage.setScene(scene);
	    qrStage.show();
	    
		
		
	}

}
