package scenes;

import java.io.IOException;
import java.util.Vector;

import application.User;
import communication.DataCommunicator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import services.Service;
import services.ServiceManager;

public class ServicesViewController {	
	@FXML
	private GridPane grid;
	@FXML
	TextField searching;
	@FXML
	ImageView backImage;
	
	Vector<Service> systemServices;
	
	User user;
	ServiceManager serviceManager = new ServiceManager();
	ChangeScenes scener = new ChangeScenes();

	DataCommunicator communicator;

	public ServicesViewController() {
		communicator = DataCommunicator.getCommunicator();
		user = (User) communicator.getUser();
	}
	
	@FXML
	public void initialize() {
		systemServices = serviceManager.CreateSystemServices();
		displaySearch(systemServices);
		backImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			try {
				scener.changeSceneWithMouse(event, "MainPageUser.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}

	     });
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
			btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				try {
					for(int j = 0; j < displayServices.size(); j++) {
						if(btn.getText().equals(displayServices.get(j).getName())) {
							communicator.setService(displayServices.get(j));
						}
						
					}
					scener.changeSceneWithMouse(event, "ServiceDetails.fxml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			btn.setMinHeight(100);
			grid.add(btn, i, 0);
		}
		
	}

}
