package application;

import java.util.Random;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CommonObjects {
	
	private static CommonObjects commonObjects = new CommonObjects();
	
	private VBox mainBox;
	private Random generator;
	
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

	public Random getGenerator() {
		return generator;
	}

	public void setGenerator(Random generator) {
		this.generator = generator;
	}
	
	
	
}
