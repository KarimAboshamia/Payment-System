package application;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	
	
//	Stage primaryStage;
//	
//	public void setScene(Parent sentRoot) {
//		Parent root = sentRoot;
//		Scene scene = new Scene(root);
//		primaryStage.setTitle("Edfa3li Shokran");
//		primaryStage.setScene(scene);
//		primaryStage.show();
//	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
	
			Scene scene = new Scene(root);

	
			primaryStage.setTitle("Edfa3li Shokran");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
