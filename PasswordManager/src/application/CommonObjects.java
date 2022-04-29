package application;

import javafx.scene.layout.VBox;

public class CommonObjects {
	
	private static CommonObjects commonObjects = new CommonObjects();
	
	private VBox mainBox;
	
	private CommonObjects() {
		
	}
	
	public static CommonObjects getInstance() {
		return commonObjects;
	}

	public VBox getMainBox() {
		return mainBox;
	}

	public void setMainBox(VBox mainBox) {
		this.mainBox = mainBox;
	}
	
	
	
}
