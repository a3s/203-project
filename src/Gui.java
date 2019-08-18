import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * guiDriver for town Graph program
 * @author benth
 */
public class Gui extends Application 
{
	//start Panel innerclass
	/**
	 * inter panel class for gui
	 * @author benth
	 */
	public class Panel extends VBox
	{
		private TitledPane addTownPane,addRaodPane,findConnectionPane;

		private HBox addTownHBox = new HBox();
		private HBox addRoadHBox = new HBox(), 
				addRoadHBox2 = new HBox(),
				buttonHBox = new HBox();
		private VBox addRoadVBox = new VBox();
		private HBox findConnectionHBox = new HBox();
		private VBox findConnectionVBox = new VBox();
		
		private Label townLabel = new Label("Town Name: "), 
				roadLabel = new Label("Road Name: "), 
				distanceLabel = new Label("Distance: "),
				findConnectionLabel = new Label("Find Connection from: ");
		
		private TextField townField = new TextField(""),
				roadField = new TextField(""),
				distanceField = new TextField("");
		private TextArea connectionArea = new TextArea("");
		
		private Button addTownButton = new Button("Add Town"),
				addRoadButton = new Button("Add Road"),
				findButton = new Button("Find Connection"),
				readFileButton = new Button("Read File"),
				exitButton = new Button("Exit");
					
		private ComboBox<String> addRoadTown1Box = new ComboBox<String>(), addRoadTown2Box = new ComboBox<String>(),
				findConnectionTown1Box = new ComboBox<String>(), findConnectionTown2Box = new ComboBox<String>(); 
		
		private File file;
		
		private TownGraphManager manager = new TownGraphManager();

		public Panel()
		{
			
			addTownHBox.getChildren().addAll(townLabel,townField,addTownButton);
			addTownHBox.setSpacing(20);
			addTownHBox.setAlignment(Pos.CENTER);
			
			addTownPane = new TitledPane("Add Town",addTownHBox);
			addTownPane.setPadding(new Insets(20,20,20,20));
			addTownPane.setCollapsible(false);
			addTownPane.setAlignment(Pos.CENTER);
		
			
			
			addRoadHBox.getChildren().addAll(roadLabel, roadField);
			addRoadHBox.setSpacing(20);
			addRoadHBox.setAlignment(Pos.CENTER);
			
			addRoadHBox2.getChildren().addAll(addRoadTown1Box, addRoadTown2Box, distanceLabel, distanceField, addRoadButton);
			addRoadHBox2.setSpacing(20);
			addRoadHBox2.setAlignment(Pos.CENTER);
			
			addRoadVBox.getChildren().addAll(addRoadHBox, new Label("Select Towns for Road"), addRoadHBox2);
			addRoadVBox.setAlignment(Pos.CENTER);
			
			addRaodPane = new TitledPane("AddRoad",addRoadVBox);
			addRaodPane.setPadding(new Insets(20,20,20,20));
			addRaodPane.setCollapsible(false);
			addRaodPane.setAlignment(Pos.CENTER);
			
			
			
			findConnectionHBox.getChildren().addAll(findConnectionLabel,findConnectionTown1Box, new Label(" to "), findConnectionTown2Box, findButton);
			findConnectionHBox.setSpacing(20);
			findConnectionHBox.setAlignment(Pos.CENTER);
			
			findConnectionVBox.getChildren().addAll(findConnectionHBox,connectionArea);
			findConnectionVBox.setPadding(new Insets(20, 20, 10,20));
			findConnectionVBox.setSpacing(20);
			
			connectionArea.setEditable(false);
			connectionArea.prefHeightProperty().bind(findConnectionVBox.heightProperty());//binds textArea height with the VBox that it will be placed in
			connectionArea.prefWidthProperty().bind(findConnectionVBox.widthProperty());//binds textArea width with the VBox that it will be placed in
			
			findConnectionPane = new TitledPane("Find Connection", findConnectionVBox);
			findConnectionPane.prefHeightProperty().bind(this.heightProperty()); //binds TitledPane height with the the Stage that it will be placed in
			findConnectionPane.prefWidthProperty().bind(this.widthProperty());	//binds TitledPane width with the the Stage that it will be placed in
			findConnectionPane.setPadding(new Insets(20,20,20,20));
			findConnectionPane.setCollapsible(false);
			findConnectionPane.setAlignment(Pos.CENTER);
			
			
			
			buttonHBox.getChildren().addAll(readFileButton,exitButton);
			buttonHBox.setPadding(new Insets(0, 0, 30,0));
			buttonHBox.setAlignment(Pos.CENTER);
			
			
			getChildren().addAll(addTownPane, addRaodPane, findConnectionPane, buttonHBox);
			
			addTownButton.setOnAction(Event ->
			{
				if(!townField.getText().equals("")) 
				{
					manager.addTown(townField.getText());
					
					//updates drop down comboBoxes with newly added town
					addRoadTown1Box.getItems().setAll(manager.allTowns());
					addRoadTown2Box.getItems().setAll(manager.allTowns());
					findConnectionTown1Box.getItems().setAll(manager.allTowns());
					findConnectionTown2Box.getItems().setAll(manager.allTowns());
				}
				else
					JOptionPane.showMessageDialog(null, "cant leave the town name blank!");
			});
			
			addRoadButton.setOnAction(Event ->
			{
				try
				{
					if(!roadField.getText().equalsIgnoreCase("") || !distanceField.getText().equalsIgnoreCase("")) 
					{
						if(manager.containsRoadConnection(addRoadTown1Box.getValue(), addRoadTown2Box.getValue()))
						{
							JOptionPane.showMessageDialog(null, "A Road between the two towns already exists!");
						}
						else 
						{
							manager.addRoad(addRoadTown1Box.getValue(), addRoadTown2Box.getValue(), Integer.parseInt(distanceField.getText()), roadField.getText());
						}
					}
					else if(roadField.getText().equalsIgnoreCase("")) 
					{
							JOptionPane.showMessageDialog(null, "Cant Leave road name blank!");
					}
					else if (roadField.getText().equalsIgnoreCase("")) 
					{ 
							JOptionPane.showMessageDialog(null, "Cant Leave road distance blank!");
					}
				}
				catch(NullPointerException e) 
				{
					JOptionPane.showMessageDialog(null, "Cant leave Town selection blank!");
				}
			});
			
			findButton.setOnAction(Event ->
			{
				
				try 
				{
					
					connectionArea.setText("");
					if(manager.allTowns().isEmpty())
					{	
						JOptionPane.showMessageDialog(null, "The graph is empty! Try adding some towns and roads");
					}
					else
					{
						ArrayList<String> path = manager.getPath(findConnectionTown1Box.getValue(),findConnectionTown2Box.getValue());
						
						if (findConnectionTown1Box.getValue().equals(findConnectionTown2Box.getValue())) //if the two selected towns are the same
						{
							connectionArea.setText("cant find a path between a town and itseld choose two diffrent towns.");
						}
						else if(path.size() == 0) //if the getPath method couldnt find a path it returns an empty list
						{
							connectionArea.setText("There is no route between the two towns");
						} 
						else 
						{		
							for(int i = 0; i <path.size(); i++) 
							{
								connectionArea.appendText(path.get(i) + "\n");	
							}
						}
					}
				}
				catch(NullPointerException e) 
				{
					JOptionPane.showMessageDialog(null, "Cant leave Town selection blank!");
				}
				
			});
			
			readFileButton.setOnAction(Event-> 
			{
				FileChooser chooser = new FileChooser();
				file = chooser.showOpenDialog(this.getScene().getWindow());
				try 
				{
					Scanner fileReader = new Scanner(file);

					while(file != null && fileReader.hasNextLine())
					{
						String[] split = fileReader.nextLine().split(";");
						manager.addTown(split[1]);
						manager.addTown(split[2]);
						manager.addRoad(split[1], split[2], Integer.parseInt(split[0].substring(split[0].indexOf(",")+1)), split[0].substring(0, split[0].indexOf(",")));
					}
					//updates drop down comboBoxes with newly added towns
					addRoadTown1Box.getItems().addAll(manager.allTowns());
					addRoadTown2Box.getItems().addAll(manager.allTowns());
					findConnectionTown1Box.getItems().addAll(manager.allTowns());
					findConnectionTown2Box.getItems().addAll(manager.allTowns());
					
				} 
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			exitButton.setOnAction(Event-> 
			{
				System.exit(0);
			});
		}
	}//end of Panel inner class
	
	public void start(Stage stage) throws IOException 
	{
		stage.setTitle("Graph Program");
		Panel root = new Panel();
		stage.setScene(new Scene(root,700,620));
		stage.setMinWidth(700);
		stage.show();

	}

	public static void main(String[] args) 
	{
		   launch(args);
	}

}
