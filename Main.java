/*
 * Project Name: Main.java
 * Author: Julian Fuentes
 * Date Last Updated: 24 February 2024
 * Purpose: This program views, inserts, and updates staff information inside of a database.
 */

package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application 
{
	private TextField idText = new TextField(); 
	private TextField lastNameText = new TextField();
	private TextField firstNameText = new TextField();
	private TextField miText = new TextField();
	private TextField addressText = new TextField();
	private TextField cityText = new TextField();
	private TextField stateText = new TextField();
	private TextField telephoneText = new TextField();
	private TextField emailText = new TextField();
	//These should be all the text fields.
	
	private Connection connection;
	//This should create the connection to the database.
	
	private void initializeDatabase() 
	{
		try 
		{
			String url = "http://localhost/dashboard/";
			connection = DriverManager.getConnection(url);
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	//This is where the program will connect with the database.
	
	private GridPane createdGridPane() 
	{
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20));
		grid.setHgap(10);
		grid.setVgap(10);
		//This should set the grid pane.
		
		Label idLabel = new Label("ID");
		Label lastNameLabel = new Label("Last Name");
		Label firstNameLabel = new Label("First Name");
		Label miLabel = new Label("MI");
		Label addressLabel = new Label("Address");
		Label cityLabel = new Label("City");
		Label stateLabel = new Label("State");
		Label telephoneLabel = new Label("Telephone");
		Label emailLabel = new Label("Email");
		//These should be all the labels.
		
		Button viewButton = new Button("View");
		Button insertButton = new Button("Insert");
		Button updateButton = new Button("Update");
		//These should be all the buttons.
		
		viewButton.setOnAction(e-> viewRecord());
		insertButton.setOnAction(e-> insertRecord());
		updateButton.setOnAction(e-> updateRecord());
		//These are the actions for the buttons.
		
		grid.add(idLabel, 0, 0);
		grid.add(idText, 1, 0);
		grid.add(lastNameLabel, 0, 1);
		grid.add(lastNameText, 1, 1);
		grid.add(firstNameLabel, 0, 2);
		grid.add(firstNameText, 1, 2);
		grid.add(miLabel, 0, 3);
		grid.add(miText, 1, 3);
		grid.add(addressLabel, 0, 4);
		grid.add(addressText, 1, 4);
		grid.add(cityLabel, 0, 5);
		grid.add(cityText, 1, 5);
		grid.add(stateLabel, 0, 6);
		grid.add(stateText, 1, 6);
		grid.add(telephoneLabel, 0, 7);
		grid.add(telephoneText, 1, 7);
		grid.add(emailLabel, 0, 8);
		grid.add(emailText, 1, 8);
		//These are the text fields on the grid.
		
		grid.add(viewButton, 2, 0);
		grid.add(insertButton, 2, 2);
		grid.add(updateButton, 2, 4);
		//This will add buttons to the grid.
		
		return grid;
	}
	
	private void viewRecord() 
	{
		String id = idText.getText();
		try 
		{
			String query = "Choose from Staff where ID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1,  id);
			
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) 
			{
				lastNameText.setText(resultSet.getString("lastName"));
				firstNameText.setText(resultSet.getString("firstName"));
				miText.setText(resultSet.getString("mi"));
				addressText.setText(resultSet.getString("address"));
				cityText.setText(resultSet.getString("city"));
				stateText.setText(resultSet.getString("state"));
				telephoneText.setText(resultSet.getString("telephone"));
				emailText.setText(resultSet.getString("email"));
			}
			else 
			{
				clearFields();
				
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void insertRecord() 
	{
		String id = idText.getText();
		String lastName = lastNameText.getText();
		String firstName = firstNameText.getText();
		String mi = miText.getText();
		String address = addressText.getText();
		String city = cityText.getText();
		String state = stateText.getText();
		String telephone = telephoneText.getText();
		String email = emailText.getText();
		
		try 
		{
			String query = "Please insert the 9 staff values: ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);
			statement.setString(2, lastName);
			statement.setString(3, firstName);
			statement.setString(4, mi);
			statement.setString(5, address);
			statement.setString(6, city);
			statement.setString(7, state);
			statement.setString(8, telephone);
			statement.setString(9, email);
				
				
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void updateRecord() 
	{
		String id = idText.getText();
		String lastName = lastNameText.getText();
		String firstName = firstNameText.getText();
		String mi = miText.getText();
		String address = addressText.getText();
		String city = cityText.getText();
		String state = stateText.getText();
		String telephone = telephoneText.getText();
		String email = emailText.getText();
		
		try 
		{
			String query = "Here is the Updated Staff Set: ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, lastName);
			statement.setString(2, firstName);
			statement.setString(3, mi);
			statement.setString(4, address);
			statement.setString(5, city);
			statement.setString(6, state);
			statement.setString(7, telephone);
			statement.setString(8, email);
			statement.setString(9, id);
			
		
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
			
	}
	
	
	private void clearFields() 
	{
		idText.clear();
		lastNameText.clear();
		firstNameText.clear();
		miText.clear();
		addressText.clear();
		cityText.clear();
		stateText.clear();
		telephoneText.clear();
		emailText.clear();
	}
	
	
	@Override
	public void start(Stage primaryStage) 
	{
		initializeDatabase();
		GridPane grid = createdGridPane();
		Scene scene = new Scene(grid, 400, 300);
		primaryStage.setTitle("Staff Management");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
