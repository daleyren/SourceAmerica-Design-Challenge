/* Program Name: timeClock.java
 * Contributors: Dale Ren, Alan Wang, Johann Kuruvilla
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Button;

public class timeClock extends Application{
	public static void main(String[] args) throws Exception {
		getConnection();
		launch(args);
	}
	
	//Establishes a Connection with the SQL Server
	public static Connection getConnection() throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = ""; //Depends on SQL Server
		String username = ""; //Depends on SQL Server
		String password = ""; //Depends on SQL Server
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);

			System.out.println("Connected to SQL Server!");
			return conn;
		} catch (Exception e) {
			System.out.println("Connection Error: " + e);
		}

		return null;
	}

	//Create Table of For User Information
	public static void createTable() throws Exception {
		try {
			Connection conn = getConnection();
			PreparedStatement directory = conn.prepareStatement(); //Inside Prepared Statement Add SQL to create Table
			directory.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Create Table Error" + ex);
		} finally {
			System.out.println("Table Created");
		}
	}
	
	//Visuals and Front End Interface
	public void start(Stage primaryStage) {
		
	}
}
