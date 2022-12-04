package scenes;

import java.io.IOException;
import java.util.Vector;

import communication.DataCommunicator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import providers.Provider;
import services.Service;

public class ServiceDetailsController {
	
	//Choose provider then go to the form of that provider
	DataCommunicator communicator;
	Service service;
	ChangeScenes scener = new ChangeScenes();
	
	@FXML
	Label serviceName;
	@FXML
	GridPane grid;
	@FXML
	ImageView backImage;
	
	public ServiceDetailsController() {
		communicator = DataCommunicator.getCommunicator();
		service = communicator.getService();
	}
	
	@FXML 
	public void initialize() {
		serviceName.setText(service.getName());
		displaySearch(service.getProviders());
		backImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			try {
				scener.changeSceneWithMouse(event, "ServicesView.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}

	     });
		
	}
	
	public void displaySearch(Vector<Provider> displayProvider) {
		for(int i = 0; i < displayProvider.size(); i++) {
			Button btn = new Button(displayProvider.get(i).getProviderName());
			btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				try {
					for(int j = 0; j < displayProvider.size(); j++) {
						if(btn.getText().equals(displayProvider.get(j).getProviderName())) {
							communicator.setProvider(displayProvider.get(j));
						}
						
					}
					scener.changeSceneWithMouse(event, "ProviderForm.fxml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			btn.setMinHeight(100);
			btn.setMinWidth(100);
			grid.add(btn, i, 0);
		}
		
	}

}
