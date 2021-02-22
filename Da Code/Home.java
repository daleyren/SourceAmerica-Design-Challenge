package timeClock;

import javafx.application.Application;
import javafx.scene.layout.VBox; 
import javafx.scene.layout.HBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline; 
import java.awt.Font; 
import java.util.Date; 
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 

public class Home extends Application {
	Label d;
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		try {
			primaryStage.initStyle(StageStyle.DECORATED);
			//Code for timer
			Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
		        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
		        d.setText(LocalDateTime.now().format(f));
		    }), new KeyFrame(Duration.seconds(1)));
		    clock.setCycleCount(Animation.INDEFINITE);
		    clock.play();
			
		    //Creating logo image
		    Image logo = new Image(new FileInputStream
		    		("C:\\ProgramData\\Corel\\Messages\\540111125_707000\\EN\\MessageCache1\\Workflow\\Shallow Depth of Field"));
		    ImageView logoView = new ImageView(logo);
		    
		    //code for creating an application window
			primaryStage.setTitle("Time Clock");
			
			//code for punch-in button
			Button punchIn = new Button(); 
			punchIn.setText("Punch In");
			punchIn.setStyle("-fx-background-color: #b6d7a8ff; ");
			punchIn.setOnAction(value -> { 
				punchIn.setText("Clicked!");
				//add other code here
			});
			punchIn.setStyle("-fx-font-size: 14px;"); 
			punchIn.setStyle("-fx-font-family: \"Alegreya\";");
			//Code for punch-out button
			Button punchOut = new Button(); 
			punchOut.setText("Punch Out");
			punchOut.setOnAction(value -> { 
				punchOut.setText("Clicked!"); 
				//add rest of method here
			});
			punchOut.setStyle("-fx-background-color: #ea9999ff; ");
			punchOut.setStyle("-fx-font-family: \"Alegreya\";");
			//Code for account button
			Button account = new Button(); 
			account.setText("Edit Account Settings");
			account.setOnAction(value -> { 
				//code for moving to the account settings page
			});
			account.setStyle("-fx-background-color: #cfe2f3ff; ");
			account.setStyle("-fx-font-family: \"Alegreya\";");
			//Code for adding elements to the application window
			Group root = new Group(); 
			Scene h = new Scene(root, 450, 300);
			root.getChildren().add(punchIn); 
			root.getChildren().add(punchOut);
			root.getChildren().add(logoView); 
			root.getChildren().add(d);
			primaryStage.setScene(h);
			primaryStage.show(); 
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
