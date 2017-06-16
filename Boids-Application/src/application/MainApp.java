package application;

import java.io.IOException;

import com.google.inject.Module;

import agent.EnvironmentAgent;
import application.layouts.Controller;
import environment.Espace;
import io.janusproject.Boot;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainApp extends Application {

	public Espace espace;
	public Controller GUI;

	public static int WIDTH = 500;
	public static int HEIGHT = 500;
	public static int DEPTH = 500;
	public static int NBBOIDS = 100;


	@Override
	public void start(Stage primaryStage) throws Exception {

		Boot.setOffline(true);

		espace = new Espace(WIDTH,HEIGHT,DEPTH);
		GUI = this.espace.getBoidsGUI();

		Boot.startJanus(
				(Class<? extends Module>) null,
				EnvironmentAgent.class,
				espace,
				NBBOIDS);

		try{
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(MainApp.class.getResource("layouts/PersonOverview.fxml"));
			Controller controller = GUI;
			fxmlLoader.setController(controller);

			Node ui = fxmlLoader.load();
			StackPane root = new StackPane();
	        root.getChildren().add(ui);
	        Scene scene = new Scene(root, 2400, 1200);
	        primaryStage.setTitle("Test FXML 3D - 2");
	        primaryStage.setScene(scene);

		}catch(IOException e){
			e.printStackTrace();
		}

        primaryStage.show();

        Timeline loop = new Timeline(new KeyFrame(Duration.millis(2), new EventHandler<ActionEvent>(){
			public void handle(ActionEvent actionEvent){
				//System.out.println("Ca tourne");
				GUI.repaint();
			}
		}));
        loop.setCycleCount(Timeline.INDEFINITE);
		loop.play();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
