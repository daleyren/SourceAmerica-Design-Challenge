import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyCode;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.shape.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import com.opencsv.CSVWriter;
import java.util.Optional;
import javafx.application.Application;
import javafx.stage.Stage;


public class TimeClock extends Application {
	public static void main(String[] args) throws Exception {
		getConnection();

		launch(args);
	}

	Text d = new Text(0, 60, "");
	Text stat = new Text(0, 125, "Enter Employee ID and Job");
	DateTimeFormatter s = DateTimeFormatter.ofPattern("HH:mm:ss");
	String password = "HelloWorld";
	String passwordAttempt = "";

	
	@Override
	public void start(Stage primaryStage) {
		try {
			// ******************************CLOCK IN/OUT PAGE******************************
			primaryStage.initStyle(StageStyle.DECORATED);
			primaryStage.setResizable(false);
			// Code for punch-in clock
//			d.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
			Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
				d.setText(LocalDateTime.now().format(s));
			}), new KeyFrame(Duration.seconds(1)));
			clock.setCycleCount(Animation.INDEFINITE);
			clock.play();

			// Rectangles
			Rectangle statBD = new Rectangle(0, 75, 450, 75);
			statBD.setStroke(Color.WHITE);
			statBD.setStyle("-fx-fill: #b3ecff");
			Rectangle clockBD = new Rectangle(50, 0, 350, 75);
			clockBD.setFill(Color.WHITE);
			clockBD.setOpacity(0.5);
			clockBD.setStrokeWidth(5);

			// Create dropdown of users
//			ComboBox userDrop = new ComboBox();
//			userDrop.setMinSize(270, 60);
//
//			userDrop.setVisibleRowCount(4);
//			userDrop.setStyle(".combo-box .list-cell \r\n" + "{\r\n" + "    -fx-background: white;\r\n"
//					+ "    -fx-background-color: gainsboro;\r\n" + "    -fx-text-fill: -fx-text-base-color;\r\n"
//					+ "    -fx-cell-size: 2em; \r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
//					+ ".combo-box-popup .list-view \r\n" + "{\r\n" + "    -fx-background-color: white, white;\r\n"
//					+ "    -fx-background-insets: 0, 1;\r\n"
//					+ "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 8, 0.0 , 0 , 0 );\r\n"
//					+ "}    \r\n" + "\r\n" + ".combo-box-popup .list-view .list-cell \r\n" + "{\r\n" + "\r\n"
//					+ "    /* No alternate highlighting */\r\n" + "    -fx-background-color: white;\r\n" + "}\r\n"
//					+ "\r\n"
//					+ ".combo-box-popup .list-view .list-cell:filled:selected, .combo-box-popup .list-view .list-cell:filled:selected:hover \r\n"
//					+ "{\r\n" + "    -fx-background: -fx-accent;\r\n"
//					+ "    -fx-background-color: -fx-selection-bar;\r\n"
//					+ "    -fx-text-fill: -fx-selection-bar-text;\r\n" + "}\r\n" + "\r\n"
//					+ ".combo-box-popup .list-view .list-cell:filled:hover \r\n" + "{\r\n"
//					+ "    -fx-background-color: white;\r\n" + "    -fx-text-fill: -fx-text-inner-color;\r\n" + "}\r\n"
//					+ "\r\n" + ".combo-box-base  \r\n" + "{\r\n"
//					+ "    -fx-skin: \"com.sun.javafx.scene.control.skin.ComboBoxBaseSkin\";\r\n"
//					+ "    -fx-background-color: white, white, white, white;\r\n" + "    -fx-padding: 0;\r\n" + "}\r\n"
//					+ "\r\n" + ".combo-box-base:hover\r\n" + "{\r\n" + "    -fx-color: -fx-hover-base;\r\n" + "}\r\n"
//					+ "\r\n" + ".combo-box-base:showing \r\n" + "{\r\n" + "    -fx-color: -fx-pressed-base;\r\n"
//					+ "}\r\n" + "\r\n" + ".combo-box-base:focused {\r\n"
//					+ "    -fx-background-color: -fx-focus-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\r\n"
//					+ "}\r\n" + "\r\n" + ".combo-box-base:disabled {\r\n" + "    -fx-opacity: .4;\r\n" + "}");

			// Add Items to User ComboBox
//			refreshUsers(userDrop);
			
			// Text box for user ids
			TextField userIDBox = new TextField();
			userIDBox.setMaxSize(270, 60);
			userIDBox.setPromptText("Employee ID");
			userIDBox.styleProperty().bind(
	                Bindings
                    .when(userIDBox.focusedProperty())
                    .then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"
                    		+ "    -fx-background-color: gainsboro, #a9a9a9, gainsboro ;\r\n"
        					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" 
                    		+ "    -fx-min-width: 270;\r\n" //use to be 270
        					+ "    -fx-min-height: 60;\r\n" + "	   -fx-font: 20px \"Avenir Next\";}\r\n"
        					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
        					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n")
                    .otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"
                    		+ "    -fx-prompt-text-fill: #333333;"
                    		+ "    -fx-background-color: #a9a9a9 , gainsboro , gainsboro;\r\n"
        					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "    -fx-min-width: 270;\r\n"
        					+ "    -fx-min-height: 60;\r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
        					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
        					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n"));
			
//			userIDBox.setStyle("{\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
//					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "    -fx-min-width: 270;\r\n"
//					+ "    -fx-min-height: 60;\r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
//					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
//					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "}");

			HBox wrapper = new HBox();
			wrapper.getChildren().add(userIDBox);
			
			// Unfocus Text Field
	        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
	        userIDBox.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
	            if(newValue && firstTime.get()){
	                wrapper.requestFocus(); // Delegate the focus to container
	                firstTime.setValue(false); // Variable value changed for future references
	            }
	        });
			
			// Create dropdown of jobs
			ComboBox<String> jobDrop = new ComboBox();
			jobDrop.setMinSize(270, 60);
//			jobDrop.setPromptText("Select Job");
			jobDrop.setVisibleRowCount(4);
			jobDrop.setStyle(".combo-box .list-cell \r\n" + "{\r\n" + "    -fx-background: white;\r\n"
					+ "    -fx-background-color: gainsboro;\r\n" + "    -fx-text-fill: -fx-text-base-color;\r\n"
//					+ "    -fx-padding: 3 0 2 7;\r\n"
					+ "    -fx-cell-size: 2em; \r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
					+ ".combo-box-popup .list-view \r\n" + "{\r\n" + "    -fx-background-color: white, white;\r\n"
					+ "    -fx-background-insets: 0, 1;\r\n"
					+ "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 8, 0.0 , 0 , 0 );\r\n"
					+ "}    \r\n" + "\r\n" + ".combo-box-popup .list-view .list-cell \r\n" + "{\r\n"
//					+ "    -fx-padding: 4 0 4 5;\r\n"
					+ "\r\n" + "    /* No alternate highlighting */\r\n" + "    -fx-background-color: white;\r\n"
					+ "}\r\n" + "\r\n"
					+ ".combo-box-popup .list-view .list-cell:filled:selected, .combo-box-popup .list-view .list-cell:filled:selected:hover \r\n"
					+ "{\r\n" + "    -fx-background: -fx-accent;\r\n"
					+ "    -fx-background-color: -fx-selection-bar;\r\n"
					+ "    -fx-text-fill: -fx-selection-bar-text;\r\n" + "}\r\n" + "\r\n"
					+ ".combo-box-popup .list-view .list-cell:filled:hover \r\n" + "{\r\n"
					+ "    -fx-background-color: white;\r\n" + "    -fx-text-fill: -fx-text-inner-color;\r\n" + "}\r\n"
					+ "\r\n" + ".combo-box-base  \r\n" + "{\r\n"
					+ "    -fx-skin: \"com.sun.javafx.scene.control.skin.ComboBoxBaseSkin\";\r\n"
					+ "    -fx-background-color: white, white, white, white;\r\n"
//					+ "    -fx-background-radius: 5, 5, 4, 3;\r\n"
//					+ "    -fx-background-insets: 0 0 -1 0, 0, 1, 2;\r\n"
					+ "    -fx-padding: 0;\r\n" + "}\r\n" + "\r\n" + ".combo-box-base:hover\r\n" + "{\r\n"
					+ "    -fx-color: -fx-hover-base;\r\n" + "}\r\n" + "\r\n" + ".combo-box-base:showing \r\n" + "{\r\n"
					+ "    -fx-color: -fx-pressed-base;\r\n" + "}\r\n" + "\r\n" + ".combo-box-base:focused {\r\n"
					+ "    -fx-background-color: -fx-focus-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\r\n"
//					+ "    -fx-background-radius: 6.4, 4, 5, 3;\r\n"
//					+ "    -fx-background-insets: -1.4, 0, 1, 2;\r\n"
					+ "}\r\n" + "\r\n" + ".combo-box-base:disabled {\r\n" + "    -fx-opacity: .4;\r\n" + "}");
			
			
			// Add Items to Job ComboBox
			String allJobs = getJobs();
			StringTokenizer seperatedJobs = new StringTokenizer(allJobs);
			String curJobs = "";
			jobDrop.getItems().add("Select Job");
			int N2 = seperatedJobs.countTokens();
			for (int i = 0; i < N2; i++) {
				curJobs = seperatedJobs.nextToken();
				jobDrop.getItems().add(curJobs);
				curJobs = "";
			}
			jobDrop.setValue("Select Job");

			// Creating logo image
			Image logo = new Image("log.jpg");
			ImageView logoView = new ImageView(logo);
			logoView.setFitHeight(30);
			logoView.setPreserveRatio(true);
			logoView.setSmooth(true);
			logoView.setCache(true);
			
			// Code for punch-in button
			Button punchInOut = new Button();
			punchInOut.setText("Punch In/Punch Out");
			punchInOut.setStyle("-fx-base: #b3ecff;" + "-fx-padding: 15 15 15 15;\r\n"
					+ "    -fx-background-radius: 8;\r\n"
					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
			punchInOut.setPrefSize(300, 75);

			// Animation to clear the punch in, punch out texts
			Timeline subtext = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
				stat.setText("Enter Employee ID and Job");
			}), new KeyFrame(Duration.ZERO));

			punchInOut.setOnAction(value -> {
				try {
					if (idValid(userIDBox.getText()) && doesIDExist(userIDBox.getText()) && !jobDrop.getValue().equals("Select Job")) {
						if(!inLog(userIDBox.getText())) {	
							// Punch In
							String name = idToName(userIDBox.getText());
							String job = (String) jobDrop.getValue();
	
							try {
								insertIntoLog(nameToID(name), "IN", jobToID(job));
							} catch (Exception e) {
								e.printStackTrace();
							}
	
							userIDBox.clear();
							jobDrop.getSelectionModel().clearSelection();
							jobDrop.setValue("Select Job");
						}
						else {
							// Punch Out
							String name = idToName(userIDBox.getText());
							String job = (String) jobDrop.getValue();

							try {
								deleteFromLog(nameToID(name));
							} catch (Exception e) {
								e.printStackTrace();
							}

							userIDBox.clear();
							jobDrop.getSelectionModel().clearSelection();
							jobDrop.setValue("Select Job");
						}
					} else {
						if(!idValid(userIDBox.getText())) {
							if(jobDrop.getValue().equals("Select Job")) {
								stat.setText("Invalid Employee ID and Job");
							}
							else {
								stat.setText("Invalid Employee ID");
							}
						}
						else {
							if(doesIDExist(userIDBox.getText())) {
								stat.setText("Please Select Job");
							}
							else {
								stat.setText("Invalid Employee ID and Job");
							}
						}
					}
					subtext.setCycleCount(1);
					subtext.play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			punchInOut.setFont(Font.font("Avenir Next", FontWeight.BOLD, 20));
			
			// Code for punch-out button
//			Button punchOut = new Button();
//			punchOut.setText("Punch Out");
//			punchOut.setPrefSize(190, 75);
//
//			punchOut.setOnAction(value -> {
////				if (userDrop.getValue() != "Select Name" && jobDrop.getValue() != "Select Job") {
//				try {
//					if (idValid(userIDBox.getText()) && doesIDExist(userIDBox.getText())) {
//
//						String name = idToName(userIDBox.getText());
//						String job = (String) jobDrop.getValue();
//
//						try {
//							deleteFromLog(nameToID(name));
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//
//						userIDBox.clear();
//						jobDrop.getSelectionModel().clearSelection();
//						jobDrop.setValue("Select Job");
//					} else {
//						stat.setText("Invalid Employee ID");
//					}
//					subtext.setCycleCount(1);
//					subtext.play();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			});
//			punchOut.setStyle("-fx-base: #ff9999;" + "-fx-padding: 15 15 15 15;\r\n"
////					+ "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\n"
//					+ "    -fx-background-radius: 8;\r\n"
//					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;"
//					+ "	   -fx-text-fill: black;");
//			punchOut.setFont(Font.font("Avenir Next", FontWeight.BOLD, 20));

			// Edit Button
			Image editImage = new Image("edit.png");
			ImageView editView = new ImageView(editImage);
			editView.setFitHeight(30);
			editView.setPreserveRatio(true);
			editView.setSmooth(true);
			editView.setCache(true);

			// Profile Picture and Task Picture
				// Profile Picture
			double sideLength = 60+60+28.6667;
			Image trainingImage = new Image("training.png", sideLength, sideLength, false, true);
			Image assemblyImage = new Image("assembly.png", sideLength, sideLength, false, true);
			Image breakImage = new Image("break.png", sideLength, sideLength, false, true);
			Image managerImage = new Image("manager.png", sideLength, sideLength, false, true);
			Image jobImage = new Image("jobsearch.png", sideLength, sideLength, false, true);
			Image employeeImage = new Image("employeecard.png", sideLength, sideLength, false, true);
			Image checkImage = new Image("greencheck.png", sideLength, sideLength, false, true);
			Image crossImage = new Image("redcross.png", sideLength, sideLength, false, true);
			
			ImageView profileView = new ImageView(employeeImage);
			profileView.setFitWidth(60);
			profileView.setFitHeight(60);
			profileView.setCache(true);
			
			HBox profileWrapper = new HBox();
			profileWrapper.setStyle("-fx-border-color: transparent;" + "-fx-border-width: 3;");
			profileWrapper.getChildren().add(profileView);
			
			userIDBox.textProperty().addListener((observable, oldValue, newValue) -> {
				int size = newValue.length();
	            try {
	            	if(!newValue.equals("") && newValue.substring(newValue.length()-1,newValue.length()).toLowerCase().equals("j")) {
	            		userIDBox.setText(newValue.substring(0,newValue.length()-1));
	            		newValue = userIDBox.getText();
	            		jobDrop.requestFocus();
	            		jobDrop.show();
	            	}
	            	if(!newValue.equals("") && newValue.substring(newValue.length()-1,newValue.length()).toLowerCase().equals("p")) {
	            		userIDBox.setText(newValue.substring(0,newValue.length()-1));
	            		newValue = userIDBox.getText();
	            		punchInOut.fire();
	            	}
	            	if(!newValue.equals("")) {
						if(idValid(newValue) && doesIDExist(newValue)) {
						    profileView.setImage(checkImage);
						}
						else {
						    profileView.setImage(crossImage);
						}
	            	}
	            	else {
					    profileView.setImage(employeeImage);
	            	}
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	
				// Task Picture
			ImageView taskView = new ImageView(jobImage);
			taskView.setFitWidth(60);
			taskView.setFitHeight(60);
			taskView.setCache(true);
	
			HBox taskWrapper = new HBox();
			taskWrapper.setStyle("-fx-border-color: transparent;" + "-fx-border-width: 3;");
			taskWrapper.getChildren().add(taskView);
			
			jobDrop.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
				String job = (String) newValue;
//				System.out.println(job);

				if(job != null) {
					if(job.equals("Training")) {
						taskView.setImage(trainingImage);
					}
					else if(job.equals("Assembly")) {
						taskView.setImage(assemblyImage);
					}
					else if(job.equals("Break")) {
						taskView.setImage(breakImage);
					}
					else if(job.equals("Manager")) {
						taskView.setImage(managerImage);
					}
					else {
						taskView.setImage(jobImage);
					}
				}
				
			});
			
			jobDrop.setCellFactory(lv -> {
			    ListCell<String> cell = new ListCell<String>() {
			        @Override
			        protected void updateItem(String item, boolean empty) {
			            super.updateItem(item, empty);
			            setText(item);
			        }
			    };
			    cell.hoverProperty().addListener((obs, wasHovered, isNowHovered) -> {	
			    	String selected = getLastToken(cell.getText(), "/n");
//			    	System.out.println(selected);
			    	
			        if (selected.equals("Select Job")) {
			            taskView.setImage(jobImage);
			        } else if (selected.equals("Training")) {
			            taskView.setImage(trainingImage);
			        } else if (selected.equals("Assembly")) {
			            taskView.setImage(assemblyImage);
			        } else if (selected.equals("Break")) {
			            taskView.setImage(breakImage);
			        } else { //Manager
			        	taskView.setImage(managerImage);
			        }
			        
			        if(wasHovered == true) {
			    		if (jobDrop.getValue().equals("Select Job")) {
				            taskView.setImage(jobImage);
				        } else if (jobDrop.getValue().equals("Training")) {
				            taskView.setImage(trainingImage);
				        } else if (jobDrop.getValue().equals("Assembly")) {
				            taskView.setImage(assemblyImage);
				        } else if (jobDrop.getValue().equals("Break")) {
				            taskView.setImage(breakImage);
				        } else { //Manager
				        	taskView.setImage(managerImage);
				        }
			        }
			    });
			    return cell ;
			});
			
			// Page Arrangement
			Group mainRoot = new Group();

			mainRoot.getChildren().add(statBD);
			mainRoot.getChildren().add(clockBD);
			mainRoot.getChildren().add(wrapper);
			mainRoot.getChildren().add(jobDrop);

			
			wrapper.setLayoutX(90+36.5);
			wrapper.setLayoutY(149+28.66667);
			jobDrop.setLayoutX(90+36.5);
			jobDrop.setLayoutY(209+28.66667*2);

			
			mainRoot.getChildren().add(stat);
			stat.setFont(Font.font("Avenir Next", 28));
			stat.setStyle("-fx-background-color: #cfe2f3ff; ");
			stat.setWrappingWidth(450);
			stat.setTextAlignment(TextAlignment.CENTER);
			mainRoot.getChildren().add(d);
			d.setFont(Font.font("Avenir Next", 50));
			d.setWrappingWidth(450);
			d.setTextAlignment(TextAlignment.CENTER);
			mainRoot.getChildren().add(punchInOut);
			punchInOut.setLayoutX(75);
			punchInOut.setLayoutY(355);
//			mainRoot.getChildren().add(punchOut);
//			punchOut.setLayoutX(235);
//			punchOut.setLayoutY(355);
//			mainRoot.getChildren().add(logoView);
//			logoView.setLayoutX(0);
//			logoView.setLayoutY(420);
			mainRoot.getChildren().add(editView);
			editView.setLayoutX(405);
			editView.setLayoutY(15);
			
			mainRoot.getChildren().add(profileWrapper);
			profileWrapper.setLayoutX(17+36.5);
			profileWrapper.setLayoutY(146+28.66667);
			
			mainRoot.getChildren().add(taskWrapper);
			taskWrapper.setLayoutX(17+36.5);
			taskWrapper.setLayoutY(206+2*28.66667);

			Scene home = new Scene(mainRoot, 450, 450);
			primaryStage.setTitle("");
			primaryStage.setScene(home);
			primaryStage.show();
			
			home.setOnKeyPressed(event -> {
				if(home.getRoot().equals(mainRoot)) {
					if(event.getCode() == KeyCode.DIGIT0 
							|| event.getCode() == KeyCode.DIGIT1
							|| event.getCode() == KeyCode.DIGIT2
							|| event.getCode() == KeyCode.DIGIT3
							|| event.getCode() == KeyCode.DIGIT4
							|| event.getCode() == KeyCode.DIGIT5
							|| event.getCode() == KeyCode.DIGIT6
							|| event.getCode() == KeyCode.DIGIT7
							|| event.getCode() == KeyCode.DIGIT8
							|| event.getCode() == KeyCode.DIGIT9) {
						userIDBox.requestFocus();
					}
					if(event.getCode() == KeyCode.J) {
						jobDrop.requestFocus();
						jobDrop.show();
					}
					if(event.getCode() == KeyCode.ENTER) {
						punchInOut.requestFocus();
					}
					if(event.getCode() == KeyCode.P) {
						punchInOut.fire();
					}
				}
			});

			// ******************************SIGN IN PAGE******************************
			Group signInGroup = new Group();
			Scene signInPage = new Scene(signInGroup, 450, 250);
			Text desc5 = new Text("");
			Line header5 = new Line();
			
			desc5 = new Text("Manager Sign-In");
			desc5.setFont(Font.font("Avenir Next", FontWeight.NORMAL, 25));

			header5 = new Line();
			header5.setStrokeWidth(2);

			// Password Text Box
			PasswordField passwordBox = new PasswordField();
			passwordBox.setPromptText("Password");
			passwordBox.styleProperty().bind(
	                Bindings
                    .when(passwordBox.focusedProperty())
                    .then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"
                    		+ "    -fx-background-color: gainsboro, #a9a9a9, gainsboro ;\r\n"
        					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" 
                    		+ "    -fx-min-width: 350;\r\n"
        					+ "    -fx-min-height: 60;\r\n" + "	   -fx-font: 20px \"Avenir Next\";}\r\n"
        					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
        					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n")
                    .otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"
                    		+ "    -fx-prompt-text-fill: #333333;"
                    		+ "    -fx-background-color: #a9a9a9 , gainsboro , gainsboro;\r\n"
        					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "    -fx-min-width: 350;\r\n"
        					+ "    -fx-min-height: 60;\r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
        					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
        					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n"));

			HBox wrapper1 = new HBox();
			wrapper1.getChildren().add(passwordBox);		
	        
	        Button signInButton = new Button();
	        signInButton.setText("Enter");
	        signInButton.setStyle("-fx-base: black;" + "-fx-text-fill: white;"
					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
	        signInButton.setPrefSize(130, 40);
	        signInButton.setFont(Font.font("Avenir Next", FontWeight.BOLD, 17));
			
			// Back Button
			Image backImage = new Image("back.png");
			ImageView back5 = new ImageView(backImage);
			back5.setFitHeight(30);
			back5.setPreserveRatio(true);
			back5.setSmooth(true);
			back5.setCache(true);
			back5.setOnMouseClicked(value -> {
				primaryStage.setScene(home);
			});
			
			// Sign-In Page Arrangement
			signInGroup.getChildren().add(desc5);
			desc5.setX(123.5);
			desc5.setY(40);

			signInGroup.getChildren().add(header5);
			header5.setStartX(80);
			header5.setEndX(370);
			header5.setStartY(50);
			header5.setEndY(50);

			signInGroup.getChildren().add(back5);
			back5.setLayoutX(15);
			back5.setLayoutY(15);
			
			signInGroup.getChildren().add(wrapper1);
			wrapper1.setLayoutX(50);
			wrapper1.setLayoutY(50+40);
			
			signInGroup.getChildren().add(signInButton);
			signInButton.setLayoutX(160);
			signInButton.setLayoutY(50+60+40+25);

			// Navigation
			editView.setOnMouseClicked(value -> {
				passwordBox.clear();
				primaryStage.setScene(signInPage);
			});
			
			// ******************************EDIT NAVIGATION PAGE*****************************
			Group edit = new Group();
			Scene editPage = new Scene(edit, 450, 280);
			Text desc = new Text("Employee Editor");
			desc.setFont(Font.font("Avenir Next", FontWeight.NORMAL, 25));

			Line header = new Line();
			header.setStrokeWidth(2);

			// Navigation
			signInButton.setOnMouseClicked(value -> {
	        	passwordAttempt = passwordBox.getText();
	        	if(password.equals(passwordAttempt)) {
			    	primaryStage.setScene(editPage);
	        	}
	        	else {
	        		Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("");
					alert.setContentText("Incorrect Password.");
					alert.showAndWait();
	        	}
	        });

			// Buttons (export, back, view, insert, update, delete)
			// Export Button
			Image exportImage = new Image("download.png");
			ImageView export = new ImageView(exportImage);
			export.setFitHeight(30);
			export.setPreserveRatio(true);
			export.setSmooth(true);
			export.setCache(true);
			export.setOnMouseClicked(value -> {
				// Export data somehow
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("");
				alert.setContentText("Data has been downloaded into your the app subfiles.");
				alert.showAndWait();
				try {
					exportTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			// Back Button
			ImageView back = new ImageView(backImage);
			back.setFitHeight(30);
			back.setPreserveRatio(true);
			back.setSmooth(true);
			back.setCache(true);
			back.setOnMouseClicked(value -> {
				primaryStage.setScene(home);
				// UPDATE COMBOBOX OF USERS
				try {
//					refreshUsers(userDrop);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			// View Button
			Button view = new Button();
			view.setText("View");
			view.setStyle("-fx-base: #b3ecff;" + "    -fx-background-radius: 10;\r\n"
					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
			view.setPrefSize(180, 75);
			view.setFont(Font.font("Avenir Next", FontWeight.BOLD, 20));

			// Insert Button
			Button insert = new Button();
			insert.setText("Insert");
			insert.setStyle("-fx-base: #b3ecff;" + "    -fx-background-radius: 10;\r\n"
					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
			insert.setPrefSize(180, 75);
			insert.setFont(Font.font("Avenir Next", FontWeight.BOLD, 20));

			// Update Button
			Button update = new Button();
			update.setText("Update");
			update.setStyle("-fx-base: #b3ecff;" + "    -fx-background-radius: 10;\r\n"
					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
			update.setPrefSize(180, 75);
			update.setFont(Font.font("Avenir Next", FontWeight.BOLD, 20));

			// Delete Button
			Button delete = new Button();
			delete.setText("Delete");
			delete.setStyle("-fx-base: #b3ecff;" + "    -fx-background-radius: 10;\r\n"
					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
			delete.setPrefSize(180, 75);
			delete.setFont(Font.font("Avenir Next", FontWeight.BOLD, 20));

			// Editor Window Setup
			edit.getChildren().add(desc);
			desc.setX(125);
			desc.setY(40);

			edit.getChildren().add(header);
			header.setStartX(100);
			header.setEndX(350);
			header.setStartY(50);
			header.setEndY(50);

			// Buttons
			edit.getChildren().add(export);
			export.setLayoutX(405);
			export.setLayoutY(15);

			edit.getChildren().add(back);
			back.setLayoutX(15);
			back.setLayoutY(15);

			edit.getChildren().add(view);
			view.setLayoutX(30);
			view.setLayoutY(70);

			edit.getChildren().add(insert);
			insert.setLayoutX(240);
			insert.setLayoutY(70);

			edit.getChildren().add(update);
			update.setLayoutX(30);
			update.setLayoutY(70 + 95);

			edit.getChildren().add(delete);
			delete.setLayoutX(240);
			delete.setLayoutY(70 + 95);

			// ******************************VIEW PAGE******************************
			Text desc1 = new Text("");
			Line header1 = new Line();

			Group viewGroup = new Group();
			Scene viewPage = new Scene(viewGroup, 450, 365);
			desc1 = new Text("Employee Pay View");
			desc1.setFont(Font.font("Avenir Next", FontWeight.NORMAL, 25));

			header1 = new Line();
			header1.setStrokeWidth(2);

			// Back Button
			ImageView back1 = new ImageView(backImage);
			back1.setFitHeight(30);
			back1.setPreserveRatio(true);
			back1.setSmooth(true);
			back1.setCache(true);
			back1.setOnMouseClicked(value -> {
				primaryStage.setScene(editPage);
			});

			ComboBox<String> viewUserDrop = new ComboBox();
			viewUserDrop.setMinSize(270, 60);
//			userDrop.setPromptText("Select Name");

			viewUserDrop.setVisibleRowCount(4);
			viewUserDrop.setStyle(".combo-box .list-cell \r\n" + "{\r\n" + "    -fx-background: white;\r\n"
					+ "    -fx-background-color: gainsboro;\r\n" + "    -fx-text-fill: -fx-text-base-color;\r\n"
					+ "    -fx-cell-size: 2em; \r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
					+ ".combo-box-popup .list-view \r\n" + "{\r\n" + "    -fx-background-color: white, white;\r\n"
					+ "    -fx-background-insets: 0, 1;\r\n"
					+ "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 8, 0.0 , 0 , 0 );\r\n"
					+ "}    \r\n" + "\r\n" + ".combo-box-popup .list-view .list-cell \r\n" + "{\r\n" + "\r\n"
					+ "    /* No alternate highlighting */\r\n" + "    -fx-background-color: white;\r\n" + "}\r\n"
					+ "\r\n"
					+ ".combo-box-popup .list-view .list-cell:filled:selected, .combo-box-popup .list-view .list-cell:filled:selected:hover \r\n"
					+ "{\r\n" + "    -fx-background: -fx-accent;\r\n"
					+ "    -fx-background-color: -fx-selection-bar;\r\n"
					+ "    -fx-text-fill: -fx-selection-bar-text;\r\n" + "}\r\n" + "\r\n"
					+ ".combo-box-popup .list-view .list-cell:filled:hover \r\n" + "{\r\n"
					+ "    -fx-background-color: white;\r\n" + "    -fx-text-fill: -fx-text-inner-color;\r\n" + "}\r\n"
					+ "\r\n" + ".combo-box-base  \r\n" + "{\r\n"
					+ "    -fx-skin: \"com.sun.javafx.scene.control.skin.ComboBoxBaseSkin\";\r\n"
					+ "    -fx-background-color: white, white, white, white;\r\n" + "    -fx-padding: 0;\r\n" + "}\r\n"
					+ "\r\n" + ".combo-box-base:hover\r\n" + "{\r\n" + "    -fx-color: -fx-hover-base;\r\n" + "}\r\n"
					+ "\r\n" + ".combo-box-base:showing \r\n" + "{\r\n" + "    -fx-color: -fx-pressed-base;\r\n"
					+ "}\r\n" + "\r\n" + ".combo-box-base:focused {\r\n"
					+ "    -fx-background-color: -fx-focus-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\r\n"
					+ "}\r\n" + "\r\n" + ".combo-box-base:disabled {\r\n" + "    -fx-opacity: .4;\r\n" + "}");

			Text payText = new Text(105, 212.5, "Payment:");

			Text numberText = new Text(245, 211, "");

			Rectangle payBD = new Rectangle(0, 165, 450, 75);
			payBD.setStroke(Color.WHITE);
			payBD.setStyle("-fx-fill: #b3ecff");

			Rectangle numberBD = new Rectangle(235, 180, 125, 45);
			numberBD.setArcHeight(10);
			numberBD.setArcWidth(10);
			numberBD.setStroke(Color.WHITE);
			numberBD.setFill(Color.WHITE);

			viewGroup.getChildren().add(payBD);
			viewGroup.getChildren().add(numberBD);
			viewGroup.getChildren().add(payText);
			viewGroup.getChildren().add(numberText);

			payText.setFont(Font.font("Avenir Next", 25));
			payText.setStyle("-fx-background-color: #cfe2f3ff; ");
			payText.setWrappingWidth(450);

			numberText.setFont(Font.font("Avenir Next", 22));
			numberText.setStyle("-fx-background-color: #cfe2f3ff; ");
			numberText.setWrappingWidth(450);
//			payText.setTextAlignment(TextAlignment.CENTER);

			Button single = new Button();
			single.setText("Clear Selected");
			single.setStyle("-fx-base: #b3ecff;" + "    -fx-background-radius: 10;\r\n"
					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
			single.setPrefSize(190, 75);
			single.setFont(Font.font("Avenir Next", FontWeight.BOLD, 20));
			single.setOnAction(value -> {
				try {
					clearUserTime(viewUserDrop.getValue());
					if(!viewUserDrop.getValue().equals("Select Name")) {
						numberText.setText("0.00");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			Button all = new Button();
			all.setText("Clear All");
			all.setStyle("-fx-base: #b3ecff;" + "    -fx-background-radius: 10;\r\n"
					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
			all.setPrefSize(190, 75);
			all.setFont(Font.font("Avenir Next", FontWeight.BOLD, 20));
			all.setOnAction(value -> {
				try {
					clearAllTimes(viewUserDrop);
					if(!viewUserDrop.getValue().equals("Select Name")) {
						numberText.setText("0.00");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			// Upon Drop Box Change, Calculate/Display Pay
			viewUserDrop.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {

				String name = (String) newValue;

				if (name != null && !name.equals("Select Name")) {
					try {
						numberText.setText(viewPay(name));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					// Text is set as "empty"
					numberText.setText("");
				}
			});

			// View Page Arrangement
			viewGroup.getChildren().add(desc1);
			desc1.setX(105);
			desc1.setY(40);

			viewGroup.getChildren().add(header1);
			header1.setStartX(80);
			header1.setEndX(370);
			header1.setStartY(50);
			header1.setEndY(50);

			viewGroup.getChildren().add(back1);
			back1.setLayoutX(15);
			back1.setLayoutY(15);

			viewGroup.getChildren().add(viewUserDrop);
			viewUserDrop.setLayoutY(77.5);
			viewUserDrop.setLayoutX(90);

			viewGroup.getChildren().add(single);
			single.setLayoutX(20);
			single.setLayoutY(250 + 20);

			viewGroup.getChildren().add(all);
			all.setLayoutX(235);
			all.setLayoutY(250 + 20);

			// Next 270 + 35
			// Y Scale 240 + 35 + 60 + 20

			// Navigation
			view.setOnAction(value -> {
				primaryStage.setScene(viewPage);

				// Populate User ComboBox
				try {
					refreshUsers(viewUserDrop);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			// ******************************INSERT PAGE******************************
			Text desc2 = new Text("");
			Line header2 = new Line();

			Group insertGroup = new Group();
			Scene insertPage = new Scene(insertGroup, 450, 372.5+77.5);
			desc2 = new Text("New Employee");
			desc2.setFont(Font.font("Avenir Next", FontWeight.NORMAL, 25));

			header2 = new Line();
			header2.setStrokeWidth(2);

			// Back Button
			ImageView back2 = new ImageView(backImage);
			back2.setFitHeight(30);
			back2.setPreserveRatio(true);
			back2.setSmooth(true);
			back2.setCache(true);
			back2.setOnMouseClicked(value -> {
				primaryStage.setScene(editPage);
			});

			// TextFields (ID, FirstName, LastName, DateOfBirth)

			TextField firstNameText = new TextField();
			firstNameText.setPromptText("First Name");
			firstNameText.setStyle("{\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "    -fx-min-width: 350;\r\n"
					+ "    -fx-min-height: 62.5;\r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "}");
			HBox firstName = new HBox();
			firstName.getChildren().add(firstNameText);
			// Unfocus Text Field
	        final BooleanProperty firstTime1 = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
	        firstNameText.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
	            if(newValue && firstTime1.get()){
	                firstName.requestFocus(); // Delegate the focus to container
	                firstTime1.setValue(false); // Variable value changed for future references
	            }
	        });

			TextField lastName = new TextField();
			lastName.setPromptText("Last Name");
			lastName.setStyle("{\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "    -fx-min-width: 350;\r\n"
					+ "    -fx-min-height: 62.5;\r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "}");

			TextField dateOfBirth = new TextField();
			dateOfBirth.setPromptText("Date of Birth (mm/dd/yyyy)");
			dateOfBirth.setStyle("{\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "    -fx-min-width: 350;\r\n"
					+ "    -fx-min-height: 62.5;\r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "}");

			TextField id = new TextField();
			id.setPromptText("Employee ID (6 Digits)");
			id.setStyle("{\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "    -fx-min-width: 350;\r\n"
					+ "    -fx-min-height: 62.5;\r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "}");
			
			Button insertButton = new Button();
			insertButton.setText("Insert");
			insertButton.setStyle("-fx-base: #b3ecff;" + "    -fx-background-radius: 10;\r\n"
					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
			insertButton.setPrefSize(150, 60);
			insertButton.setFont(Font.font("Avenir Next", FontWeight.THIN, 20));

			// Insert Page Arrangement
			insertGroup.getChildren().add(desc2);
			desc2.setX(135);
			desc2.setY(40);

			insertGroup.getChildren().add(header2);
			header2.setStartX(80);
			header2.setEndX(370);
			header2.setStartY(50);
			header2.setEndY(50);

			insertGroup.getChildren().add(back2);
			back2.setLayoutX(15);
			back2.setLayoutY(15);

			insertGroup.getChildren().add(firstName);
			firstName.setLayoutX(50);
			firstName.setLayoutY(50 + 15);

			insertGroup.getChildren().add(lastName);
			lastName.setLayoutX(50);
			lastName.setLayoutY(50 + 30 + 62.5);

			insertGroup.getChildren().add(dateOfBirth);
			dateOfBirth.setLayoutX(50);
			dateOfBirth.setLayoutY(50 + 45 + 125);
			
			insertGroup.getChildren().add(id);
			id.setLayoutX(50);
			id.setLayoutY(50 + 60 + 187.5);

			insertGroup.getChildren().add(insertButton);
			insertButton.setLayoutX(150);
			insertButton.setLayoutY(50 + 60 + 187.5+77.5);

			insertButton.setOnAction(value -> {
				try {
					insertUser(firstNameText.getText(), lastName.getText(), dateOfBirth.getText(), id.getText());
					
					if(success) {
						firstNameText.clear();
						lastName.clear();
						dateOfBirth.clear();
						id.clear();
						
						success = false;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			// Navigation
			insert.setOnAction(value -> {
				primaryStage.setScene(insertPage);
				success = false;
			});

			// ******************************UPDATE PAGE******************************
			Text desc3 = new Text("");
			Line header3 = new Line();

			Group updateGroup = new Group();
			Scene updatePage = new Scene(updateGroup, 450, 450+77.5);
			desc3 = new Text("Employee Update");
			desc3.setFont(Font.font("Avenir Next", FontWeight.NORMAL, 25));

			header3 = new Line();
			header3.setStrokeWidth(2);

			// Back Button
			ImageView back3 = new ImageView(backImage);
			back3.setFitHeight(30);
			back3.setPreserveRatio(true);
			back3.setSmooth(true);
			back3.setCache(true);
			back3.setOnMouseClicked(value -> {
				primaryStage.setScene(editPage);
			});

			Button updateButton = new Button();
			updateButton.setText("Update");
			updateButton.setStyle("-fx-base: #b3ecff;" + "    -fx-background-radius: 10;\r\n"
					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
			updateButton.setPrefSize(150, 60);
			updateButton.setFont(Font.font("Avenir Next", FontWeight.THIN, 20));

			ComboBox<String> updateUserDrop = new ComboBox();
			updateUserDrop.setMinSize(270, 60);

			updateUserDrop.setVisibleRowCount(4);
			updateUserDrop.setStyle(".combo-box .list-cell \r\n" + "{\r\n" + "    -fx-background: white;\r\n"
					+ "    -fx-background-color: gainsboro;\r\n" + "    -fx-text-fill: -fx-text-base-color;\r\n"
					+ "    -fx-cell-size: 2em; \r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
					+ ".combo-box-popup .list-view \r\n" + "{\r\n" + "    -fx-background-color: white, white;\r\n"
					+ "    -fx-background-insets: 0, 1;\r\n"
					+ "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 8, 0.0 , 0 , 0 );\r\n"
					+ "}    \r\n" + "\r\n" + ".combo-box-popup .list-view .list-cell \r\n" + "{\r\n" + "\r\n"
					+ "    /* No alternate highlighting */\r\n" + "    -fx-background-color: white;\r\n" + "}\r\n"
					+ "\r\n"
					+ ".combo-box-popup .list-view .list-cell:filled:selected, .combo-box-popup .list-view .list-cell:filled:selected:hover \r\n"
					+ "{\r\n" + "    -fx-background: -fx-accent;\r\n"
					+ "    -fx-background-color: -fx-selection-bar;\r\n"
					+ "    -fx-text-fill: -fx-selection-bar-text;\r\n" + "}\r\n" + "\r\n"
					+ ".combo-box-popup .list-view .list-cell:filled:hover \r\n" + "{\r\n"
					+ "    -fx-background-color: white;\r\n" + "    -fx-text-fill: -fx-text-inner-color;\r\n" + "}\r\n"
					+ "\r\n" + ".combo-box-base  \r\n" + "{\r\n"
					+ "    -fx-skin: \"com.sun.javafx.scene.control.skin.ComboBoxBaseSkin\";\r\n"
					+ "    -fx-background-color: white, white, white, white;\r\n" + "    -fx-padding: 0;\r\n" + "}\r\n"
					+ "\r\n" + ".combo-box-base:hover\r\n" + "{\r\n" + "    -fx-color: -fx-hover-base;\r\n" + "}\r\n"
					+ "\r\n" + ".combo-box-base:showing \r\n" + "{\r\n" + "    -fx-color: -fx-pressed-base;\r\n"
					+ "}\r\n" + "\r\n" + ".combo-box-base:focused {\r\n"
					+ "    -fx-background-color: -fx-focus-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\r\n"
					+ "}\r\n" + "\r\n" + ".combo-box-base:disabled {\r\n" + "    -fx-opacity: .4;\r\n" + "}");

			TextField updateFirstName = new TextField();
			updateFirstName.setPromptText("First Name");
			updateFirstName.setStyle("{\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "    -fx-min-width: 350;\r\n"
					+ "    -fx-min-height: 62.5;\r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "}");

			TextField updateLastName = new TextField();
			updateLastName.setPromptText("Last Name");
			updateLastName.setStyle("{\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "    -fx-min-width: 350;\r\n"
					+ "    -fx-min-height: 62.5;\r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "}");

			TextField updateDOB = new TextField();
			updateDOB.setPromptText("Date of Birth (mm/dd/yyyy)");
			updateDOB.setStyle("{\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "    -fx-min-width: 350;\r\n"
					+ "    -fx-min-height: 62.5;\r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "}");

			TextField updateId = new TextField();
			updateId.setPromptText("Employee ID (6 Digits)");
			updateId.setStyle("{\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "    -fx-min-width: 350;\r\n"
					+ "    -fx-min-height: 62.5;\r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "}");
			
			// ----------Actions--------

			// Button Pressed
			updateButton.setOnAction(value -> {
				int UserID = updateUserID;
//				System.out.println(UserID);	
				if (updateUserDrop.getValue() != "Select Name"
					&& !(oldFirstName.equals(updateFirstName.getText())
							&& oldLastName.equals(updateLastName.getText())
							&& oldDOB.equals(updateDOB.getText())
							&& oldID.equals(updateId.getText()))) {
					try {
						updateUser(UserID, updateFirstName.getText(), updateLastName.getText(), updateDOB.getText(), updateId.getText());

						if (success) {
							updateFirstName.clear();
							updateLastName.clear();
							updateDOB.clear();
							updateUserDrop.setValue("Select Name");
							
							success = false;
							refreshUsers(updateUserDrop);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			// Upon Drop Box Change, Display FirstName, LastName, and DOB
			updateUserDrop.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {

				String name = (String) newValue;

				if (name != null && !name.equals("Select Name")) {
					try {
						String data = getUser(name);
						StringTokenizer token = new StringTokenizer(data);

						updateFirstName.setText(token.nextToken());
						updateLastName.setText(token.nextToken());

						String unformat = token.nextToken();
						String format = unformat.substring(5, 7) + "/" + unformat.substring(8, 10) + "/"
								+ unformat.substring(0, 4);
						updateDOB.setText(format);
						updateId.setText(token.nextToken());
						
						Integer temp = Integer.parseInt(token.nextToken());
						setUserID(temp);

						oldFirstName = updateFirstName.getText();
						oldLastName = updateLastName.getText();
						oldDOB = updateDOB.getText();
						oldID = updateId.getText();

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					updateFirstName.clear();
					updateLastName.clear();
					updateDOB.clear();
					updateId.clear();
				}
			});

			// Update Page Arrangement
			updateGroup.getChildren().add(desc3);
			desc3.setX(115);
			desc3.setY(40);

			updateGroup.getChildren().add(header3);
			header3.setStartX(80);
			header3.setEndX(370);
			header3.setStartY(50);
			header3.setEndY(50);

			updateGroup.getChildren().add(back3);
			back3.setLayoutX(15);
			back3.setLayoutY(15);

			updateGroup.getChildren().add(updateUserDrop);
			updateUserDrop.setLayoutX(90);
			updateUserDrop.setLayoutY(65);

			updateGroup.getChildren().add(updateFirstName);
			updateFirstName.setLayoutX(50);
			updateFirstName.setLayoutY(65 + 60 + 15);

			updateGroup.getChildren().add(updateLastName);
			updateLastName.setLayoutX(50);
			updateLastName.setLayoutY(125 + 62.5 + 15 * 2);

			updateGroup.getChildren().add(updateDOB);
			updateDOB.setLayoutX(50);
			updateDOB.setLayoutY(125 + 62.5 * 2 + 15 * 3);
			
			updateGroup.getChildren().add(updateId);
			updateId.setLayoutX(50);
			updateId.setLayoutY(125 + 62.5 * 3 + 15 * 4);

			updateGroup.getChildren().add(updateButton);
			updateButton.setLayoutX(150);
			updateButton.setLayoutY(125 + 62.5 * 3 + 15 * 4 + 77.5);

			// Navigation
			update.setOnAction(value -> {
				primaryStage.setScene(updatePage);
				success = false;
				// Populate User ComboBox
				try {
					refreshUsers(updateUserDrop);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			// ******************************DELETE PAGE******************************
			Text desc4 = new Text("");
			Line header4 = new Line();

			Group deleteGroup = new Group();
			Scene deletePage = new Scene(deleteGroup, 450, 260);
			desc4 = new Text("Delete Employee");
			desc4.setFont(Font.font("Avenir Next", FontWeight.NORMAL, 25));

			header4 = new Line();
			header4.setStrokeWidth(2);

			// Back Button
			ImageView back4 = new ImageView(backImage);
			back4.setFitHeight(30);
			back4.setPreserveRatio(true);
			back4.setSmooth(true);
			back4.setCache(true);
			back4.setOnMouseClicked(value -> {
				primaryStage.setScene(editPage);
			});

			ComboBox deleteUserDrop = new ComboBox();
			deleteUserDrop.setMinSize(270, 60);
//			userDrop.setPromptText("Select Name");

			deleteUserDrop.setVisibleRowCount(4);
			deleteUserDrop.setStyle(".combo-box .list-cell \r\n" + "{\r\n" + "    -fx-background: white;\r\n"
					+ "    -fx-background-color: gainsboro;\r\n" + "    -fx-text-fill: -fx-text-base-color;\r\n"
					+ "    -fx-cell-size: 2em; \r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
					+ ".combo-box-popup .list-delete \r\n" + "{\r\n" + "    -fx-background-color: white, white;\r\n"
					+ "    -fx-background-insets: 0, 1;\r\n"
					+ "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 8, 0.0 , 0 , 0 );\r\n"
					+ "}    \r\n" + "\r\n" + ".combo-box-popup .list-delete .list-cell \r\n" + "{\r\n" + "\r\n"
					+ "    /* No alternate highlighting */\r\n" + "    -fx-background-color: white;\r\n" + "}\r\n"
					+ "\r\n"
					+ ".combo-box-popup .list-delete .list-cell:filled:selected, .combo-box-popup .list-delete .list-cell:filled:selected:hover \r\n"
					+ "{\r\n" + "    -fx-background: -fx-accent;\r\n"
					+ "    -fx-background-color: -fx-selection-bar;\r\n"
					+ "    -fx-text-fill: -fx-selection-bar-text;\r\n" + "}\r\n" + "\r\n"
					+ ".combo-box-popup .list-delete .list-cell:filled:hover \r\n" + "{\r\n"
					+ "    -fx-background-color: white;\r\n" + "    -fx-text-fill: -fx-text-inner-color;\r\n" + "}\r\n"
					+ "\r\n" + ".combo-box-base  \r\n" + "{\r\n"
					+ "    -fx-skin: \"com.sun.javafx.scene.control.skin.ComboBoxBaseSkin\";\r\n"
					+ "    -fx-background-color: white, white, white, white;\r\n" + "    -fx-padding: 0;\r\n" + "}\r\n"
					+ "\r\n" + ".combo-box-base:hover\r\n" + "{\r\n" + "    -fx-color: -fx-hover-base;\r\n" + "}\r\n"
					+ "\r\n" + ".combo-box-base:showing \r\n" + "{\r\n" + "    -fx-color: -fx-pressed-base;\r\n"
					+ "}\r\n" + "\r\n" + ".combo-box-base:focused {\r\n"
					+ "    -fx-background-color: -fx-focus-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\r\n"
					+ "}\r\n" + "\r\n" + ".combo-box-base:disabled {\r\n" + "    -fx-opacity: .4;\r\n" + "}");

			Button deleteButton = new Button();
			deleteButton.setText("Delete");
			deleteButton.setStyle("-fx-base: #b3ecff;" + "    -fx-background-radius: 10;\r\n"
					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
			deleteButton.setPrefSize(150, 60);
			deleteButton.setFont(Font.font("Avenir Next", FontWeight.THIN, 20));
			deleteButton.setOnAction(value -> {
				// Delete Item From Table
				String name = (String) deleteUserDrop.getValue();

				if (name != "Select Name") {
					try {
						deleteUser(name);
					} catch (Exception e) {
						e.printStackTrace();
					}

					// Re-populate User ComboBox
					try {
						refreshUsers(deleteUserDrop);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			// Delete Page Arrangement
			deleteGroup.getChildren().add(desc4);
			desc4.setX(115);
			desc4.setY(40);

			deleteGroup.getChildren().add(header4);
			header4.setStartX(80);
			header4.setEndX(370);
			header4.setStartY(50);
			header4.setEndY(50);

			deleteGroup.getChildren().add(back4);
			back4.setLayoutX(15);
			back4.setLayoutY(15);

			deleteGroup.getChildren().add(deleteUserDrop);
			deleteUserDrop.setLayoutY(80);
			deleteUserDrop.setLayoutX(90);

			deleteGroup.getChildren().add(deleteButton);
			deleteButton.setLayoutY(80 + 60 + 30);
			deleteButton.setLayoutX(150);

			// Navigation
			delete.setOnAction(value -> {
				primaryStage.setScene(deletePage);

				// Populate User ComboBox
				try {
					refreshUsers(deleteUserDrop);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean inLog(String id) throws Exception {
		String name = idToName(id);
		int userID = nameToID(name);
		Connection conn = getConnection();
		ResultSet resultSet = null;
		
		try {
			Statement statement = conn.createStatement();
			String selectSql = String.format("SELECT * FROM dbo.TimeLog WHERE UserID=%s;", userID);
			resultSet = statement.executeQuery(selectSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(resultSet.next()) {
			return true;
		}
		else {
			return false;
		}
	}
	private static String getLastToken(String strValue, String splitter )  
	{        
	   String[] strArray = strValue.split(splitter);  
	   return strArray[strArray.length -1];            
	}     
	
	public String idToName(String id) throws Exception {
		if(!doesIDExist(id)) {
			return null;
		}
		Connection conn = getConnection();
		ResultSet resultSet = null;
		
		try {
			Statement statement = conn.createStatement();
			String selectSql = String.format("SELECT * FROM dbo.Employees WHERE PublicID=%s;", id);
			resultSet = statement.executeQuery(selectSql);
			resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet.getString("FirstName") + " " + resultSet.getString("LastName"); 
	}
	
	public boolean doesIDExist(String id) throws Exception {
		// --------------Checks if 6 Digit ID exists---------------
		if(!(idValid(id))) {
			return true;
		}
		
		Connection conn = getConnection();
		ResultSet resultSet = null;
		
		try {
			Statement statement = conn.createStatement();
			String selectSql = String.format("SELECT * FROM dbo.Employees WHERE PublicID=%s;", id);
			resultSet = statement.executeQuery(selectSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(resultSet.next()) {
			return true;
		}
		return false;
	}
	
	public String viewPay(String name) throws Exception {
		// --------------Returns the total payment---------------

		Connection conn = getConnection();
		ResultSet resultSet = null;
		String data = "";
		double total = 0;

		HashMap<Integer, Double> hoursPerJob = new HashMap<Integer, Double>();
		HashMap<Integer, Double> ratePerJob = new HashMap<Integer, Double>();

		// get UserID
		int UserID = nameToID(name);

		// get JobID
		int JobID = 0;
		try {
			Statement statement = conn.createStatement();
			String selectSql = String.format("SELECT * FROM dbo.TimeLog WHERE UserID=%s;", UserID);
			resultSet = statement.executeQuery(selectSql);
			resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Loop through jobs and times, then add to total
		Statement statement = conn.createStatement();
		String selectSql = String.format("SELECT * FROM dbo.EmployeeTimes WHERE UserID=%s;", UserID);
		resultSet = statement.executeQuery(selectSql);
		resultSet.next();
		String curJob = "";
		double curHours = 0;

		for (int i = 1; i <= 4; i++) {
			// Get the total time of Job

			JobID = i;
			try {
				curJob = String.format("JobID_%s", JobID);
				curHours = Double.parseDouble(resultSet.getString(curJob));
			} catch (SQLException e) {
				e.printStackTrace();
			}

			hoursPerJob.put(JobID, curHours);
		}

		// get rate per job and add to hashMap
		for (int i = 1; i <= 4; i++) {
			// Get the total jobs and rates
			JobID = i;

			try {
				selectSql = String.format("SELECT * FROM dbo.Jobs WHERE ID=%s;", JobID);
				resultSet = statement.executeQuery(selectSql);
				resultSet.next();

				double curRate = Double.parseDouble(resultSet.getString(3));
				ratePerJob.put(JobID, curRate);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

//		System.out.println(hoursPerJob);
//		System.out.println(ratePerJob);

		for (int i = 1; i <= 4; i++) {
			JobID = i;
			total += (hoursPerJob.get(JobID) * ratePerJob.get(JobID));
		}

		// Convert double to String in valid format
		String output = String.format("%.2f", total);
//		System.out.println(output);

		return output;
	}

	public void clearUserTime(String name) throws Exception {
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		String updateSql;
		

		if (!name.equals("Select Name")) {
			int UserID = nameToID(name);

			StringTokenizer token = new StringTokenizer(name);
			String firstName = token.nextToken();
			
			Alert alert = new Alert(AlertType.CONFIRMATION,
					"Are you sure you want to clear " + firstName + "'s time/pay?", ButtonType.NO, ButtonType.YES);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				updateSql = String
						.format("UPDATE dbo.EmployeeTimes SET JobID_1 = 0, JobID_2 = 0, JobID_3 = 0, JobID_4 = 0 "
								+ "WHERE UserID = %s;", UserID);
				statement.execute(updateSql);

				Alert success = new Alert(AlertType.INFORMATION);
				success.setTitle("");
				success.setContentText(firstName + "'s time has been cleared.");
				success.showAndWait();
			}
		}
	}

	public void clearAllTimes(ComboBox<String> userDrop) throws Exception {
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		String updateSql;
		int UserID;

		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to clear all times/pays?", ButtonType.NO,
				ButtonType.YES);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			for (String name : userDrop.getItems()) {
				if (!name.equals("Select Name")) {
					UserID = nameToID(name);
					updateSql = String
							.format("UPDATE dbo.EmployeeTimes SET JobID_1 = 0, JobID_2 = 0, JobID_3 = 0, JobID_4 = 0 "
									+ "WHERE UserID = %s;", UserID);
					statement.execute(updateSql);
				}
			}
			Alert success = new Alert(AlertType.INFORMATION);
			success.setTitle("");
			success.setContentText("All employee times have been cleared.");
			success.showAndWait();			
		}
	}

	public void insertUser(String firstName, String lastName, String DOB, String id) throws Exception {
		Connection conn = getConnection();
		ResultSet resultSet = null;
		String insertSql;

		firstName = firstName.trim();
		lastName = lastName.trim();
		DOB = DOB.trim();
		id = id.trim();
		
		// Make sure none of the text inputs are empty and that DOB format is correct
		if (!firstName.equals("") && !lastName.equals("") && !DOB.equals("")
				&& (DOB.charAt(2) == '/' && DOB.charAt(5) == '/') && DOB.length() == 10
				&& !(doesIDExist(id))) {
			String dateOfBirth = DOB.substring(6, 10) + "-" + DOB.substring(0, 2) + "-" + DOB.substring(3, 5);
			Statement statement = conn.createStatement();

			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to insert this user?", ButtonType.NO,
					ButtonType.YES);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				// Input User into Employees
				insertSql = String.format(
						"INSERT INTO dbo.Employees (FirstName, LastName, DateOfBirth, PublicID) " + "VALUES('%s', '%s', '%s', '%s');",
						firstName, lastName, dateOfBirth, id);
				statement.execute(insertSql);

				// Get UserID from Employees
				String name = firstName + " " + lastName;
				int UserID = nameToID(name);

				// Input User into EmployeeTimes
				insertSql = String.format("INSERT INTO dbo.EmployeeTimes (UserID, JobID_1, JobID_2, JobID_3, JobID_4) "
						+ "VALUES(%s, 0, 0, 0, 0);", UserID);
				statement.execute(insertSql);

				success = true;
				Alert successAlert = new Alert(AlertType.INFORMATION);
				successAlert.setTitle("");
				successAlert.setContentText(firstName + " " + lastName + " has successfully been added.");
				successAlert.showAndWait();
				
//				System.out.println("Inserted");
			} else {
//				System.out.println("Not Inserted");
			}

		} else {
			if (!firstName.equals("") && !lastName.equals("") && !DOB.equals("")
					&& (DOB.charAt(2) == '/' && DOB.charAt(5) == '/') && DOB.length() == 10
					&& !(idValid(id))) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("");
				alert.setContentText("Please enter a valid 6-digit ID.");
				alert.showAndWait();
			}
			else if (!firstName.equals("") && !lastName.equals("") && !DOB.equals("")
					&& (DOB.charAt(2) == '/' && DOB.charAt(5) == '/') && DOB.length() == 10
					&& (doesIDExist(id))) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("");
				alert.setContentText("Employee ID already exists, please try a different ID.");
				alert.showAndWait();
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("");
				alert.setContentText("Please make sure your inputs are valid.");
				alert.showAndWait();
			}
		}
	}
	
	// Check if employee ID is valid (6 digits and 
	public static boolean idValid(String id) {
		boolean valid = true;	
		// ASCII Numbers: 48-57
		for(int i = 0; i < id.length(); i++) {
			if(((int)id.charAt(i)) < 48 || ((int)id.charAt(i)) > 57) {
				valid = false;
			}
		}
		
		if(id.length() != 6) {
			valid = false;
		}
		return valid;
	}
	
	// Update User Display (FirstName, LastName, DOB)
	public String getUser(String name) throws Exception {
		Connection conn = getConnection();
		ResultSet resultSet = null;
		try {
			Statement statement = conn.createStatement();
			// Create and execute a SELECT SQL statement.

			int UserID = nameToID(name);
			String selectSql = String.format("SELECT * FROM dbo.Employees WHERE ID=%s;", UserID);
			resultSet = statement.executeQuery(selectSql);
			resultSet.next();

			String output = resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5) + " "
					+ UserID;

//			System.out.println(output);
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int updateUserID = 0;

	public void setUserID(int input) {
		updateUserID = input;
	}

	public boolean success = false;
	String oldFirstName;
	String oldLastName;
	String oldDOB;
	String oldID;
	
	public void updateUser(int UserID, String firstName, String lastName, String DOB, String id) throws Exception {
		success = false;

		Connection conn = getConnection();
		String updateSql;

		firstName = firstName.trim();
		lastName = lastName.trim();
		DOB = DOB.trim();
		id = id.trim();
		
		if (!firstName.equals("") && !lastName.equals("") && !DOB.equals("")
				&& (DOB.charAt(2) == '/' && DOB.charAt(5) == '/') && DOB.length() == 10
				&& !(doesIDExist(id))) {
			String dateOfBirth = DOB.substring(6, 10) + "-" + DOB.substring(0, 2) + "-" + DOB.substring(3, 5);
			Statement statement = conn.createStatement();

			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to update this user?", ButtonType.NO,
					ButtonType.YES);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				updateSql = String
						.format("UPDATE dbo.Employees SET FirstName = '%s', LastName = '%s', DateOfBirth = '%s', PublicID = '%s' "
								+ "WHERE ID = %s;", firstName, lastName, dateOfBirth, id, UserID);
				statement.execute(updateSql);
				success = true;

				Alert success = new Alert(AlertType.INFORMATION);
				success.setTitle("");
				success.setContentText(firstName + " " + lastName + " has successfully been updated.");
				success.showAndWait();
			}
		} else {
			if (!firstName.equals("") && !lastName.equals("") && !DOB.equals("")
					&& (DOB.charAt(2) == '/' && DOB.charAt(5) == '/') && DOB.length() == 10
					&& !(idValid(id))) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("");
				alert.setContentText("Please enter a valid 6-digit ID.");
				alert.showAndWait();
			}
			else if (!firstName.equals("") && !lastName.equals("") && !DOB.equals("")
					&& (DOB.charAt(2) == '/' && DOB.charAt(5) == '/') && DOB.length() == 10
					&& (doesIDExist(id))) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("");
				alert.setContentText("Employee ID already exists, please try a different ID.");
				alert.showAndWait();
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("");
				alert.setContentText("Please make sure your inputs are valid.");
				alert.showAndWait();
			}
		}
	}

	public void deleteUser(String name) throws Exception {
		Connection conn = getConnection();
		String deleteSql;
		Statement statement = conn.createStatement();

		int UserID = nameToID(name);
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this user?", ButtonType.NO,
				ButtonType.YES);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			// Delete From Employees
			deleteSql = String.format("DELETE FROM dbo.Employees WHERE ID = %s", UserID);
			statement.execute(deleteSql);

			// Delete From EmployeeTimes
			deleteSql = String.format("DELETE FROM dbo.EmployeeTimes WHERE UserID = %s", UserID);
			statement.execute(deleteSql);
			
			// Delete From TimeLog
			deleteSql = String.format("DELETE FROM dbo.TimeLog WHERE UserID = %s", UserID);
			statement.execute(deleteSql);

			Alert success = new Alert(AlertType.INFORMATION);

			success.setTitle("");
			success.setContentText(name + " has been deleted.");
			success.showAndWait();
		}
	}

	public void exportTable() throws Exception {
		// Employee Times
		FileWriter file1 = new FileWriter("employee-times.csv");
		CSVWriter csvWriter1 = new CSVWriter(file1);

		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		String selectSql = "SELECT * from dbo.EmployeeTimes";
		ResultSet resultSet = statement.executeQuery(selectSql);
		csvWriter1.writeAll(resultSet, true);

		csvWriter1.close();

		// Employees
		FileWriter file2 = new FileWriter("employees.csv");
		CSVWriter csvWriter2 = new CSVWriter(file2);

		selectSql = "SELECT * from dbo.Employees";
		resultSet = statement.executeQuery(selectSql);
		csvWriter2.writeAll(resultSet, true);

		csvWriter2.close();

		// Jobs
		FileWriter file3 = new FileWriter("jobs.csv");
		CSVWriter csvWriter3 = new CSVWriter(file3);

		selectSql = "SELECT * from dbo.Jobs";
		resultSet = statement.executeQuery(selectSql);
		csvWriter3.writeAll(resultSet, true);

		csvWriter3.close();
	}

	private void HomeKey(KeyEvent evt) {
		String ch = evt.getCharacter();
		switch (ch) {
		case "o":
			// Add code for punch out here
			stat.setText("You Punched Out At " + LocalDateTime.now().format(s));
			break;
		case "i":
			// Add code for punch in here
			stat.setText("You Punched In At " + LocalDateTime.now().format(s));
			break;
		}
	}

	public void refreshUsers(ComboBox userDrop) throws Exception {
		Connection conn = getConnection();
		// Delete Items from ComboBox
		userDrop.getItems().clear();

		// Add Items to User ComboBox
		String allNames = getNames();
		StringTokenizer seperatedNames = new StringTokenizer(allNames);
		String curName = "";
		userDrop.getItems().add("Select Name");
		int N1 = seperatedNames.countTokens();
		for (int i = 0; i < N1 / 2; i++) {
			curName = seperatedNames.nextToken() + " " + seperatedNames.nextToken();
			userDrop.getItems().add(curName);
			curName = "";
		}

		userDrop.setValue("Select Name");
	}

	public String getNames() throws Exception {
		Connection conn = getConnection();
		ResultSet resultSet = null;
		String data = "";

		try {
			Statement statement = conn.createStatement();
			// Create and execute a SELECT SQL statement.
			String selectSql = "SELECT * from dbo.Employees";
			resultSet = statement.executeQuery(selectSql);

			// Print results from select statement
			while (resultSet.next()) {
				data += (resultSet.getString(2) + " " + resultSet.getString(3) + "\n");
			}

			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			return data;
		}
	}

	public String getJobs() throws Exception {
		Connection conn = getConnection();
		ResultSet resultSet = null;
		String data = "";

		try {
			Statement statement = conn.createStatement();
			// Create and execute a SELECT SQL statement.
			String selectSql = "SELECT * from dbo.Jobs";
			resultSet = statement.executeQuery(selectSql);

			// Print results from select statement
			while (resultSet.next()) {
				data += (resultSet.getString(2) + " ");
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			return data;
		}
	}

	public int nameToID(String name) throws Exception {
		Connection conn = getConnection();
		ResultSet resultSet = null;

		StringTokenizer nameToken = new StringTokenizer(name);
		String firstName = nameToken.nextToken();
		String lastName = nameToken.nextToken();

//		System.out.println(firstName);
//		System.out.println(lastName);

		try {
			Statement statement = conn.createStatement();
			// Create and execute a SELECT SQL statement.
			String selectSql = String.format("SELECT ID FROM dbo.Employees WHERE FirstName='%s' and LastName='%s';",
					firstName, lastName);
			resultSet = statement.executeQuery(selectSql);
			resultSet.next();

			int id = Integer.parseInt(resultSet.getString(1));

			return id;
//			System.out.println("ID: " + id);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int jobToID(String job) throws Exception {
		Connection conn = getConnection();
		ResultSet resultSet = null;

		try {
			Statement statement = conn.createStatement();
			// Create and execute a SELECT SQL statement.
			String selectSql = String.format("SELECT ID FROM dbo.Jobs WHERE JobName='%s';", job);
			resultSet = statement.executeQuery(selectSql);
			resultSet.next();

			int id = Integer.parseInt(resultSet.getString("ID"));

			return id;
//			System.out.println("ID: " + id);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void insertIntoLog(int UserID, String UserAction, int JobID) throws Exception {
		// UserID, UserAction, JobID, TimeOfAction, DateOfAction
		Connection conn = getConnection();
		ResultSet resultSet = null;

		// insert if User isn't already punched in yet and update throw error if
		// employee is already punched in

		// SQL Query to check if user is already punched in or not
		try {
			Statement statement = conn.createStatement();
			String selectSql = String.format("SELECT * FROM dbo.TimeLog WHERE UserID='%s';", UserID);
			resultSet = statement.executeQuery(selectSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!resultSet.next()) {
			stat.setText("You Punched In At " + LocalDateTime.now().format(s));
			try {
//				System.out.println("CHECK");
				Statement statement = conn.createStatement();
				// Create and execute a SELECT SQL statement.
				String insertSql = String.format(
						"INSERT INTO dbo.TimeLog (UserID, UserAction, JobID, TimeOfAction, DateOfAction) "
								+ "VALUES(%s, '%s', %s, '%s', '%s');",
						UserID, UserAction, JobID, LocalDateTime.now(), LocalDate.now());
				statement.execute(insertSql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("");
			alert.setContentText("You have already punched in, please punch out before punching in.");
			alert.showAndWait();
		}
	}

	public void deleteFromLog(int UserID) throws Exception { // To Be Modified When Jobs Changed
		Connection conn = getConnection();
		ResultSet resultSet = null;

		// SQL Query to check if user is already punched in or not
		try {
			Statement statement = conn.createStatement();
			String selectSql = String.format("SELECT * FROM dbo.TimeLog WHERE UserID=%s;", UserID);
			resultSet = statement.executeQuery(selectSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (resultSet.next()) {
			stat.setText("You Punched Out at " + LocalDateTime.now().format(s));
			// ***** Updates EmployeeTimes *****

			// get job id and time
			int JobID = -1;
			double timeWorked = 0;

			try {
				Statement statement = conn.createStatement();
				String selectSql = String.format("SELECT * FROM dbo.TimeLog WHERE UserID=%s;", UserID);
				resultSet = statement.executeQuery(selectSql);
				resultSet.next();
				JobID = Integer.parseInt(resultSet.getString("JobID"));

				LocalDateTime startTime = LocalDateTime
						.parse(resultSet.getString("DateOfAction") + "T" + resultSet.getString("TimeOfAction"));
				LocalDateTime tempDateTime = LocalDateTime.from(startTime);
				LocalDateTime curTime = LocalDateTime.now();

				long hours = tempDateTime.until(curTime, ChronoUnit.HOURS);
				tempDateTime = tempDateTime.plusHours(hours);

				long minutes = tempDateTime.until(curTime, ChronoUnit.MINUTES);
				tempDateTime = tempDateTime.plusHours(minutes);

				timeWorked = hours + (double) minutes / 60;

				System.out.println(curTime);
				System.out.println(startTime);
				System.out.println(timeWorked);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// Get the total time of Job
			String curJob = "";
			double oldTotal = 0;
			try {
				Statement statement = conn.createStatement();
				String selectSql = String.format("SELECT * FROM dbo.EmployeeTimes WHERE UserID=%s;", UserID);
				resultSet = statement.executeQuery(selectSql);
				resultSet.next();

				curJob = String.format("JobID_%s", JobID);
				oldTotal = Double.parseDouble(resultSet.getString(curJob));

			} catch (SQLException e) {
				e.printStackTrace();
			}

			// Update Total Time of Job
			double newTotal = oldTotal + timeWorked;
			try {
				Statement statement = conn.createStatement();
				String selectSql = String.format("UPDATE dbo.EmployeeTimes SET %s = %s WHERE UserID = %s;", curJob,
						newTotal, UserID);
				statement.execute(selectSql);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			// ***** Deletes userID from Log *****
			try {
//				System.out.println("CHECK");
				Statement statement = conn.createStatement();
				// Create and execute a SELECT SQL statement.
				String deleteSql = String.format("DELETE FROM dbo.TimeLog WHERE UserID = %s", UserID);
				statement.execute(deleteSql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("");
			alert.setContentText("You have not punched in yet. Please punch in before punching out.");
			alert.showAndWait();
		}

	}

	public static Connection getConnection() throws Exception {
		String data = "";
		String connectionUrl = "jdbc:sqlserver://phs-team-2011.database.windows.net:1433;" + "database=main_db;"
				+ "user=server-admin@phs-team-2011;" + "password=4nWz)_jK\\\\qW+bTH;" + "encrypt=true;"
				+ "trustServerCertificate=false;" + "hostNameInCertificate=*.database.windows.net;"
				+ "loginTimeout=30;";

		try {
			Connection connection = DriverManager.getConnection(connectionUrl);
			Statement statement = connection.createStatement();
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}