import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.css.PseudoClass;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
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

public class TimeClock extends Application {
	public static void main(String[] args) throws Exception {
		getConnection();

		launch(args);
	}

	Text d = new Text(0, 60, "");
	Text stat = new Text(0, 125, "Select a Name and Job");
	DateTimeFormatter s = DateTimeFormatter.ofPattern("HH:mm:ss");

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
			statBD.setFill(Color.LIGHTBLUE);
			Rectangle clockBD = new Rectangle(50, 0, 350, 75);
			clockBD.setFill(Color.WHITE);
			clockBD.setOpacity(0.5);
			clockBD.setStrokeWidth(5);

			// Create dropdown of users
			ComboBox userDrop = new ComboBox();
			userDrop.setMinSize(270, 60);
//			userDrop.setPromptText("Select Name");

			userDrop.setVisibleRowCount(4);
			userDrop.setStyle(".combo-box .list-cell \r\n" + "{\r\n" + "    -fx-background: white;\r\n"
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

			// Add Items to User ComboBox
//			String allNames = getNames();
//			StringTokenizer seperatedNames = new StringTokenizer(allNames);
//			String curName = "";
//			userDrop.getItems().add("Select Name");
//			int N1 = seperatedNames.countTokens();
//			for (int i = 0; i < N1 / 2; i++) {
//				curName = seperatedNames.nextToken() + " " + seperatedNames.nextToken();
//				userDrop.getItems().add(curName);
//				curName = "";
//			}
			refreshUsers(userDrop);

			// Create dropdown of users
			ComboBox jobDrop = new ComboBox();
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

			// Initialize Job ComboBox value
//			userDrop.setValue("Select Name");
			jobDrop.setValue("Select Job");

			// Creating logo image
			Image logo = new Image("log.jpg");
			ImageView logoView = new ImageView(logo);
			logoView.setFitHeight(30);
			logoView.setPreserveRatio(true);
			logoView.setSmooth(true);
			logoView.setCache(true);

			// Code for punch-in button
			Button punchIn = new Button();
			punchIn.setText("Punch In (i)");
			punchIn.setStyle("-fx-base: #adebad;" + "-fx-padding: 15 15 15 15;\r\n"
					+ "    -fx-background-radius: 8;\r\n"
					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
			punchIn.setPrefSize(190, 75);

			// Animation to clear the punch in, punch out texts
			Timeline subtext = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
				stat.setText("Select a Name and Job");
			}), new KeyFrame(Duration.ZERO));

			punchIn.setOnAction(value -> {
				// add other code here
				if (userDrop.getValue() != "Select Name" && jobDrop.getValue() != "Select Job") {

					String name = (String) userDrop.getValue();
					String job = (String) jobDrop.getValue();
//					try {
//						System.out.println("NameID: " + nameToID(name) + " JobID: " + jobToID(job) + " Action: IN");
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}

					try {
						insertIntoLog(nameToID(name), "IN", jobToID(job));
					} catch (Exception e) {
						e.printStackTrace();
					}

					userDrop.getSelectionModel().clearSelection();
					jobDrop.getSelectionModel().clearSelection();
					userDrop.setValue("Select Name");
					jobDrop.setValue("Select Job");

					subtext.setCycleCount(1);
					subtext.play();

				} else {
					stat.setText("Select a Name and Job");
				}
			});
			punchIn.setFont(Font.font("Avenir Next", FontWeight.BOLD, 20));
			// Code for punch-out button
			Button punchOut = new Button();
			punchOut.setText("Punch Out (o)");
			punchOut.setPrefSize(190, 75);

			punchOut.setOnAction(value -> {
				// add rest of method here
//				if (userDrop.getValue() != "Select Name" && jobDrop.getValue() != "Select Job") {
				if (userDrop.getValue() != "Select Name") {

					String name = (String) userDrop.getValue();
					String job = (String) jobDrop.getValue();
//					try {
//						System.out.println("NameID: " + nameToID(name) + " JobID: " + jobToID(job) + " Action: OUT");
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}

					try {
						deleteFromLog(nameToID(name));
					} catch (Exception e) {
						e.printStackTrace();
					}

					userDrop.getSelectionModel().clearSelection();
					jobDrop.getSelectionModel().clearSelection();
					userDrop.setValue("Select Name");
					jobDrop.setValue("Select Job");

					subtext.setCycleCount(1);
					subtext.play();

				} else {
					stat.setText("Select a Name and Job");
				}
			});
			punchOut.setStyle("-fx-base: #ff9999;" + "-fx-padding: 15 15 15 15;\r\n"
//					+ "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\n"
					+ "    -fx-background-radius: 8;\r\n"
					+ "	   -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
			punchOut.setFont(Font.font("Avenir Next", FontWeight.BOLD, 20));

			// Edit Button
			Image editImage = new Image("edit.png");
			ImageView editView = new ImageView(editImage);
			editView.setFitHeight(30);
			editView.setPreserveRatio(true);
			editView.setSmooth(true);
			editView.setCache(true);

			// Profile Picture and Task Picture
				// Profile Picture
			String profileLink = "https://styles.redditmedia.com/t5_3ozq7/styles/communityIcon_ajhgo9f7yrj21.jpg?width=256&format=pjpg&s=41900862f306879c97e28997187da35831c08a44";
			Image profileConvert = new Image(profileLink, 60, 60, false, true);
			ImageView profileView = new ImageView(profileConvert);
			profileView.setFitWidth(60);
			profileView.setCache(true);
			
			HBox profileWrapper = new HBox();
			profileWrapper.setStyle("-fx-border-color: #8c8c8c;" + "-fx-border-width: 3;");
			profileWrapper.getChildren().add(profileView);

				// Task Picture
			String taskLink = "https://cdn1.iconfinder.com/data/icons/ios-11-glyphs/30/training-512.png";
			Image taskConvert = new Image(taskLink, 60, 60, false, true);
			ImageView taskView = new ImageView(taskConvert);
			taskView.setFitWidth(60);

			taskView.setCache(true);

			HBox taskWrapper = new HBox();
			taskWrapper.setStyle("-fx-border-color: #8c8c8c;" + "-fx-border-width: 3;");
			taskWrapper.getChildren().add(taskView);
			
			
			// Checking for null instances
			if (clockBD == null) {
				clockBD = new Rectangle();
			}
			if (statBD == null) {
				statBD = new Rectangle();
			}
			if (stat == null) {
				stat = new Text();
			}
			if (logoView == null) {
				logoView = new ImageView(logo);
			}
			if (d == null) {
				d = new Text();
			}
			if (punchOut == null) {
				punchOut = new Button();
			}
			if (punchIn == null) {
				punchIn = new Button();
			}
			
			// Page Arrangement
			Group root = new Group();

			root.getChildren().add(statBD);
			root.getChildren().add(clockBD);
			root.getChildren().add(userDrop);
			root.getChildren().add(jobDrop);

			userDrop.setLayoutY(149+28.66667);
			userDrop.setLayoutX(90+36.5);
			jobDrop.setLayoutY(209+28.66667*2);
			jobDrop.setLayoutX(90+36.5);

			root.getChildren().add(stat);
			stat.setFont(Font.font("Avenir Next", 28));
			stat.setStyle("-fx-background-color: #cfe2f3ff; ");
			stat.setWrappingWidth(450);
			stat.setTextAlignment(TextAlignment.CENTER);
			root.getChildren().add(d);
			d.setFont(Font.font("Avenir Next", 50));
			d.setWrappingWidth(450);
			d.setTextAlignment(TextAlignment.CENTER);
			root.getChildren().add(punchIn);
			punchIn.setLayoutX(20);
			punchIn.setLayoutY(355);
			root.getChildren().add(punchOut);
			punchOut.setLayoutX(235);
			punchOut.setLayoutY(355);
//			root.getChildren().add(logoView);
//			logoView.setLayoutX(0);
//			logoView.setLayoutY(420);
			root.getChildren().add(editView);
			editView.setLayoutX(405);
			editView.setLayoutY(15);
			
			root.getChildren().add(profileWrapper);
			profileWrapper.setLayoutX(17+36.5);
			profileWrapper.setLayoutY(146+28.66667);
			
			root.getChildren().add(taskWrapper);
			taskWrapper.setLayoutX(17+36.5);
			taskWrapper.setLayoutY(206+2*28.66667);
			
			
			Scene home = new Scene(root, 450, 450);
			home.setOnKeyPressed(e -> HomeKey(e));
			primaryStage.setTitle("Time Clock");
			primaryStage.setScene(home);
			primaryStage.show();

			// ******************************ACCOUNT PAGE (NOT USED)******************************
			Group rootAccount = new Group();
			Scene account = new Scene(rootAccount, 450, 450);
			Text desc = new Text("Profile Settings");
			punchOut.setFont(Font.font("Avenir Next", FontWeight.BOLD, 20));
			Line header = new Line();

			// ******************************EDIT NAVIGATION PAGE*****************************
			Group edit = new Group();
			Scene editPage = new Scene(edit, 450, 280);
			desc = new Text("Employee Editor");
			desc.setFont(Font.font("Avenir Next", FontWeight.NORMAL, 25));

			header = new Line();
			header.setStrokeWidth(2);

			// Navigation
			editView.setOnMouseClicked(value -> {
				primaryStage.setScene(editPage);
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
			Image backImage = new Image("back.png");
			ImageView back = new ImageView(backImage);
			back.setFitHeight(30);
			back.setPreserveRatio(true);
			back.setSmooth(true);
			back.setCache(true);
			back.setOnMouseClicked(value -> {
				primaryStage.setScene(home);
				// UPDATE COMBOBOX OF USERS
				try {
					refreshUsers(userDrop);
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
			Scene viewPage = new Scene(viewGroup, 450, 280);
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
			payBD.setFill(Color.LIGHTBLUE);

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
					;
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
			Scene insertPage = new Scene(insertGroup, 450, 372.5);
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

			TextField firstName = new TextField();
			firstName.setPromptText("First Name");
			firstName.setStyle("{\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "    -fx-min-width: 350;\r\n"
					+ "    -fx-min-height: 62.5;\r\n" + "	   -fx-font: 20px \"Avenir Next\";\r\n" + "}\r\n" + "\r\n"
					+ "focused {\r\n" + "    -fx-background-color: #a9a9a9 , white , white;\r\n"
					+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\r\n" + "}");

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

			insertGroup.getChildren().add(insertButton);
			insertButton.setLayoutX(150);
			insertButton.setLayoutY(50 + 60 + 187.5);

			insertButton.setOnAction(value -> {
				try {
					insertUser(firstName.getText(), lastName.getText(), dateOfBirth.getText());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			// Navigation
			insert.setOnAction(value -> {
				primaryStage.setScene(insertPage);
			});

			// ******************************UPDATE PAGE******************************
			Text desc3 = new Text("");
			Line header3 = new Line();

			Group updateGroup = new Group();
			Scene updatePage = new Scene(updateGroup, 450, 450);
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

			// ---Actions---

			// Button Pressed
			updateButton.setOnAction(value -> {
				int UserID = updateUserID;
//				System.out.println(UserID);	
				if (updateUserDrop.getValue() != "Select Name") {
					try {
						updateUser(UserID, updateFirstName.getText(), updateLastName.getText(), updateDOB.getText());

						if (success) {
							updateFirstName.clear();
							updateLastName.clear();
							updateDOB.clear();
							updateUserDrop.setValue("Select Name");

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

						Integer temp = Integer.parseInt(token.nextToken());
						setUserID(temp);

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					updateFirstName.clear();
					updateLastName.clear();
					updateDOB.clear();
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

			updateGroup.getChildren().add(updateButton);
			updateButton.setLayoutX(150);
			updateButton.setLayoutY(125 + 62.5 * 3 + 15 * 4);

			// Navigation
			update.setOnAction(value -> {
				primaryStage.setScene(updatePage);

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

	public void insertUser(String firstName, String lastName, String DOB) throws Exception {
		Connection conn = getConnection();
		ResultSet resultSet = null;
		String insertSql;

		firstName = firstName.trim();
		lastName = lastName.trim();
		DOB = DOB.trim();

		// Make sure none of the text inputs are empty and that DOB format is correct
		if (!firstName.equals("") && !lastName.equals("") && !DOB.equals("")
				&& (DOB.charAt(2) == '/' && DOB.charAt(5) == '/') && DOB.length() == 10) {
			String dateOfBirth = DOB.substring(6, 10) + "-" + DOB.substring(0, 2) + "-" + DOB.substring(3, 5);
			Statement statement = conn.createStatement();

			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to insert this user?", ButtonType.NO,
					ButtonType.YES);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				// Input User into Employees
				insertSql = String.format(
						"INSERT INTO dbo.Employees (FirstName, LastName, DateOfBirth) " + "VALUES('%s', '%s', '%s');",
						firstName, lastName, dateOfBirth);
				statement.execute(insertSql);

				// Get UserID from Employees
				String name = firstName + " " + lastName;
				int UserID = nameToID(name);

				// Input User into EmployeeTimes
				insertSql = String.format("INSERT INTO dbo.EmployeeTimes (UserID, JobID_1, JobID_2, JobID_3, JobID_4) "
						+ "VALUES(%s, 0, 0, 0, 0);", UserID);
				statement.execute(insertSql);

				Alert success = new Alert(AlertType.INFORMATION);
				success.setTitle("");
				success.setContentText(firstName + " " + lastName + " has successfully been added.");
				success.showAndWait();

//				System.out.println("Inserted");
			} else {
//				System.out.println("Not Inserted");
			}

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("");
			alert.setContentText("Please make sure your inputs are valid.");
			alert.showAndWait();
		}
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

			String output = resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " "
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

	public void updateUser(int UserID, String firstName, String lastName, String DOB) throws Exception {
		success = false;

		Connection conn = getConnection();
		String updateSql;

		firstName = firstName.trim();
		lastName = lastName.trim();
		DOB = DOB.trim();

		if (!firstName.equals("") && !lastName.equals("") && !DOB.equals("")
				&& (DOB.charAt(2) == '/' && DOB.charAt(5) == '/') && DOB.length() == 10) {
			String dateOfBirth = DOB.substring(6, 10) + "-" + DOB.substring(0, 2) + "-" + DOB.substring(3, 5);
			Statement statement = conn.createStatement();

			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to update this user?", ButtonType.NO,
					ButtonType.YES);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				updateSql = String
						.format("UPDATE dbo.Employees SET FirstName = '%s', LastName = '%s', DateOfBirth = '%s' "
								+ "WHERE ID = %s;", firstName, lastName, dateOfBirth, UserID);
				statement.execute(updateSql);
				success = true;

				Alert success = new Alert(AlertType.INFORMATION);
				success.setTitle("");
				success.setContentText(firstName + " " + lastName + " has successfully been updated.");
				success.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("");
			alert.setContentText("Please make sure your inputs are valid.");
			alert.showAndWait();
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

			// Delete From Employees
			deleteSql = String.format("DELETE FROM dbo.EmployeeTimes WHERE UserID = %s", UserID);
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