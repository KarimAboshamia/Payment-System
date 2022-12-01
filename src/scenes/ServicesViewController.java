package scenes;

import java.util.Vector;

import application.User;
import communication.DataCommunicator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import services.Service;
import services.ServiceManager;

public class ServicesViewController {	
	@FXML
	private GridPane grid;
	@FXML
	TextField searching;
	
	Vector<Service> systemServices;
	
	User user;
	ServiceManager serviceManager = new ServiceManager();


	public ServicesViewController() {
		DataCommunicator communicator = DataCommunicator.getCommunicator();
		user = (User) communicator.getUser();
	}
	
	@FXML
	public void initialize() {
		systemServices = serviceManager.CreateSystemServices();
		displaySearch(systemServices);
		
	}
	
	@FXML
	public void search(ActionEvent event) {
		Vector<Service> tmpServices = serviceManager.Search(systemServices, searching.getText());
		grid.getChildren().clear();
		displaySearch(tmpServices);
		
	
	}
	
	
	public void displaySearch(Vector<Service> displayServices) {
		for(int i = 0; i < displayServices.size(); i++) {
			Button btn = new Button(displayServices.get(i).getName());
			btn.setMinHeight(100);
			grid.add(btn, i, 0);
		}
		
	}

}
