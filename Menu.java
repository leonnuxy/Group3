import java.util.Random;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/*
 * Make a simple menu with a start and quit button for the user to use.
 */
public class Menu extends Application{
	
	//A new stage for the game
	//private Stage aPrimaryStage = new Stage();
	
	/*
	 * Start the menu as a pane
	 */
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Python");
		Pane menuPane = new Pane();
		Scene menuScene = new Scene(menuPane, 800, 800);
		
		Pane settingsPane = new Pane();
		Scene settingsScene = new Scene(settingsPane, 800, 800);
		
		//create the buttons for menu
		Button start = new Button("START");
		Button quit = new Button("QUIT");
		Label welcomeCaption = new Label("Welcome to Python!");
		
		//set size and position for the start button
		start.setLayoutX(225);
		start.setLayoutY(400);
		start.setPrefSize(350, 75); 
		
		//set size and position for the quit button
		quit.setLayoutX(225);
		quit.setLayoutY(500);
		quit.setPrefSize(350, 75);
		
		//set size and position for the label
		welcomeCaption.setLayoutX(75);
		welcomeCaption.setLayoutY(200);
		welcomeCaption.setFont(new Font(70));
		
		//set font size and colour for buttons in menu
        start.setStyle("-fx-font-size: 4em;-fx-background-color: #4682b4 ");
		quit.setStyle("-fx-font-size: 4em;-fx-background-color: #ffff00  ");

		menuPane.getChildren().add(start);
		menuPane.getChildren().add(quit);
		//menuPane.getChildren().add(settings);
		menuPane.getChildren().add(welcomeCaption);
		
		primaryStage.setScene(menuScene);
		primaryStage.show();
		
		//////////////////
		
		//create buttons and label for settings
		Label chooseDiff = new Label("Choose Difficulty");
		Button easyDiff = new Button("Easy");
		Button mediumDiff = new Button("Medium");
		Button hardDiff = new Button("Hard");
		Button backtoMenu = new Button("Back");
		
		//set size and position for the easyDiff button
		easyDiff.setLayoutX(225);
		easyDiff.setLayoutY(400);
		easyDiff.setPrefSize(350, 75); 
		
		//set size and position for the mediumDiff button
		mediumDiff.setLayoutX(225);
		mediumDiff.setLayoutY(500);
		mediumDiff.setPrefSize(350, 75);
		
		//set size and position for the hardDiff button
		hardDiff.setLayoutX(225);
		hardDiff.setLayoutY(600);
		hardDiff.setPrefSize(350, 75);
		
		//set size and position for the chooseDiff label
		chooseDiff.setLayoutX(125);
		chooseDiff.setLayoutY(200);
		chooseDiff.setFont(new Font(70));
		
		//set size and position for the chooseDiff label
		backtoMenu.setLayoutX(650);
		backtoMenu.setLayoutY(50);
		backtoMenu.setPrefSize(125, 75);
		
		//add buttons and label to settings 
		settingsPane.getChildren().add(easyDiff);
		settingsPane.getChildren().add(mediumDiff);
		settingsPane.getChildren().add(hardDiff);
		settingsPane.getChildren().add(chooseDiff);
		settingsPane.getChildren().add(backtoMenu);
		
		//set font size and colour for buttons in settings
        easyDiff.setStyle("-fx-font-size: 4em;-fx-background-color: #4682b4 ");
		mediumDiff.setStyle("-fx-font-size: 4em;-fx-background-color: #4682b4  ");
		hardDiff.setStyle("-fx-font-size: 4em;-fx-background-color: #4682b4 ");
		backtoMenu.setStyle("-fx-font-size: 4em;-fx-background-color: #ffff00 ");
		
		//actions for the quit button
		quit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			System.exit(0);
			}
		}
		);
		
		//actions fot the start button, if the start button is clicked, start the game.
		start.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
			primaryStage.setScene(settingsScene);
		

			}
		}
		);
		
		
		backtoMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			primaryStage.setScene(menuScene);
	

			}
		}
		);
		
		easyDiff.setOnAction(new EventHandler<ActionEvent>() {
			int RanLevel = rando.nextInt(2);
			@Override
			public void handle(ActionEvent event) {
				
			if (RanLevel == 0) {
				Level game = new Level();
				game.start(primaryStage);
				//game.start(primaryStage);
			}
			else if (RanLevel == 1) {
				LevelTwoPlayers level2 = new LevelTwoPlayers();
				level2.start(primaryStage);
		
		}
		);

	}

}
