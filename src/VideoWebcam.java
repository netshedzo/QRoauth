import java.awt.Dimension;


import com.github.sarxos.webcam.Webcam;

import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.ImageView;


public class VideoWebcam implements Runnable {
	private ImageView Webcamera;
	private Webcam eweb;
	
	public void setImageView(ImageView Webcamer)
	{
		 Webcamera = Webcamer;
			 eweb =Webcam.getDefault();
			eweb.setViewSize(new Dimension(640,480));
			
			eweb.open();
	} 
	
	@Override
	public void run() {
		
		//WebCAM AUTO TURNS OFF AFTER 1500 TURNS
		int count = 1500;
		while(true)
		{
			Webcamera.setImage( SwingFXUtils.toFXImage(eweb.getImage(), null));
			
			count--;
			System.out.println(count + " turns To Close Webcam");
			 if(count == 0)
			 {
				 break;
			 }
		
		}
		eweb.close();
		
	}

}
