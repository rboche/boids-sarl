package application.layouts;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import application.MainApp;
import environment.Espace;
import environment.Population;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import math.Vector3f;
import objects.AgentBody;
import objects.Obstacle;

public class Controller implements Initializable {

	@FXML
	private Pane3D pane3D;

	@FXML
	private Group root3D;

	@FXML
	private Label cohDist_value;
	@FXML
	private Label sepDist_value;
	@FXML
	private Label aliDist_value;
	@FXML
	private Label cohStr_value;
	@FXML
	private Label sepStr_value;
	@FXML
	private Label aliStr_value;

	@FXML
	private Slider cohDist_slider;
	@FXML
	private Slider sepDist_slider;
	@FXML
	private Slider aliDist_slider;
	@FXML
	private Slider cohStr_slider;
	@FXML
	private Slider sepStr_slider;
	@FXML
	private Slider aliStr_slider;

	@FXML
	private ToggleButton coh_toggle;
	@FXML
	private ToggleButton sep_toggle;
	@FXML
	private ToggleButton ali_toggle;

	private Map<UUID,AgentBody> boids;

	private int width;

	private int height;

	private int depth;

	private Espace espace;

	public void setBodies(Map<UUID,AgentBody> boids){
		this.setBoids(boids);
	}

	public Controller(int iwidth,int iheight, int idepth, Map<UUID,AgentBody> iboids,Espace iespace){
		setWidth(iwidth);
		setHeight(iheight);
		setDepth(idepth);
		setBoids(iboids);
		this.espace = iespace;
	}

	public void initialize(URL location, ResourceBundle resources){

		/**Gestion des sliders
		 *
		 * */
		cohDist_slider.setBlockIncrement(1);
		cohStr_slider.setBlockIncrement(0.01);

		cohDist_slider.setMin(0);
		cohDist_slider.setMax(300);
		cohStr_slider.setMin(0);
		cohStr_slider.setMax(5);
		cohDist_slider.setValue(Population.DEFAULT_COHESION_DIST);
		cohStr_slider.setValue(Population.DEFAULT_COHESION_FORCE);

		cohDist_value.setText(Double.toString(cohDist_slider.getValue()));
		cohStr_value.setText(Double.toString(cohStr_slider.getValue()));

		cohDist_slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observableValue,Number oldval,Number newVal){
				for(AgentBody body : boids.values()){
					body.getGroupe().distCohesion = newVal.doubleValue();
				}
				cohDist_value.setText(String.format("%.1f", newVal));
			}
		});
		cohStr_slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observableValue,Number oldval,Number newVal){
				for(AgentBody body : boids.values()){
					body.getGroupe().cohesionForce = newVal.doubleValue();
				}
				cohStr_value.setText(String.format("%.1f", newVal));
			}
		});


		sepDist_slider.setBlockIncrement(1);
		sepStr_slider.setBlockIncrement(0.1);

		sepDist_slider.setMin(0);
		sepDist_slider.setMax(300);
		sepStr_slider.setMin(0);
		sepStr_slider.setMax(10);

		sepDist_slider.setValue(Population.DEFAULT_SEPARATION_DIST);
		sepStr_slider.setValue(Population.DEFAULT_SEPARATION_FORCE);

		sepDist_value.setText(Double.toString(sepDist_slider.getValue()));
		sepStr_value.setText(Double.toString(sepStr_slider.getValue()));


		sepDist_slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observableValue,Number oldval,Number newVal){
				for(AgentBody body : boids.values()){
					body.getGroupe().distSeparation= newVal.doubleValue();
				}
				sepDist_value.setText(String.format("%.1f", newVal));
			}
		});

		sepStr_slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observableValue,Number oldval,Number newVal){
				for(AgentBody body : boids.values()){
					body.getGroupe().separationForce = newVal.doubleValue();
				}
				sepStr_value.setText(String.format("%.1f", newVal));
			}
		});


		aliDist_slider.setBlockIncrement(1);
		aliStr_slider.setBlockIncrement(0.1);

		aliDist_slider.setMin(0);
		aliDist_slider.setMax(300);
		aliStr_slider.setMin(0);
		aliStr_slider.setMax(10);

		aliDist_slider.setValue(Population.DEFAULT_ALIGNEMENT_DIST);
		aliStr_slider.setValue(Population.DEFAULT_ALIGNEMENT_FORCE);

		aliDist_value.setText(Double.toString(aliDist_slider.getValue()));
		aliStr_value.setText(Double.toString(aliStr_slider.getValue()));

		aliDist_slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observableValue,Number oldval,Number newVal){
				for(AgentBody body : boids.values()){
					body.getGroupe().distAlignement = newVal.doubleValue();
				}
				aliDist_value.setText(String.format("%.1f", newVal));
			}
		});

		aliStr_slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observableValue,Number oldval,Number newVal){
				for(AgentBody body : boids.values()){
					body.getGroupe().alignementForce = newVal.doubleValue();
				}
				aliStr_value.setText(String.format("%.1f", newVal));
			}
		});

		/*boids_slider.setBlockIncrement(2);
		boids_slider.setValue(MainApp.NBBOIDS);

		nbBoids.setText(Double.toString(boids_slider.getValue()));

		boids_slider.setMin(1);
		boids_slider.setMax(200);

		boids_slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observableValue,Number oldval,Number newVal){
				MainApp.NBBOIDS = newVal.intValue();
				nbBoids.setText(String.format("%.0f", newVal));
			}
		});*/


		/**Gestion des boutons
		 * */

		coh_toggle.setSelected(true);
		coh_toggle.setOnAction((ActionEvent e) -> {
			for(AgentBody body : boids.values()){
				if(coh_toggle.isSelected()){
					body.getGroupe().cohesionOn = true;
				}else{
					body.getGroupe().cohesionOn = false;
				}
			}
		});
		ali_toggle.setSelected(true);
		ali_toggle.setOnAction((ActionEvent e) -> {
			for(AgentBody body : boids.values()){
				if(ali_toggle.isSelected()){
					body.getGroupe().alignementOn = true;
				}else{
					body.getGroupe().alignementOn = false;
				}
			}
		});
		sep_toggle.setSelected(true);
		sep_toggle.setOnAction((ActionEvent e) -> {
			for(AgentBody body : boids.values()){
				if(sep_toggle.isSelected()){
					body.getGroupe().separationOn = true;
				}else{
					body.getGroupe().separationOn = false;
				}
			}
		});

		final PhongMaterial redMaterial = new PhongMaterial();
		redMaterial.setDiffuseColor(Color.DARKRED);
		redMaterial.setSpecularColor(Color.RED);

		final PhongMaterial blueMaterial = new PhongMaterial();
		blueMaterial.setDiffuseColor(Color.DARKBLUE);
		blueMaterial.setSpecularColor(Color.BLUE);

		final PhongMaterial azurMaterial = new PhongMaterial(Color.SKYBLUE);
		azurMaterial.setDiffuseColor(Color.AZURE);
		azurMaterial.setSpecularColor(Color.SKYBLUE);

		/* Création des boids au niveau visuel
		 * */
		Group boidGroup = new Group();
		for(AgentBody body : boids.values()){
			Sphere boidMesh = new Sphere(5, 2);
			if(body.getGroupe().color == Color.RED)
			{
				boidMesh.setMaterial(redMaterial);
			}else{
				boidMesh.setMaterial(blueMaterial);
			}
	        System.out.println(body.getGroupe().color);
			boidMesh.setDrawMode(DrawMode.FILL);
			boidMesh.setTranslateX(body.getPosition().getX());
			boidMesh.setTranslateY(body.getPosition().getY());
			boidMesh.setTranslateZ(body.getPosition().getZ());
			boidGroup.getChildren().add(boidMesh);
			body.setBoidMesh(boidMesh);
		}
		root3D.getChildren().addAll(boidGroup.getChildren());

		/* Création des murs
		 * */

		final Group wallGroup = new Group();

		final Obstacle rightWall = new Obstacle(width,height/2,depth/2,espace,true);
		rightWall.setMaterial(azurMaterial);
		rightWall.setHeight(height+500);
		rightWall.setDepth(depth+500);
		rightWall.setWidth(5);
		rightWall.setTranslateX(width);
		rightWall.setDrawMode(DrawMode.LINE);
		espace.addEnvObj(rightWall);
		wallGroup.getChildren().add(rightWall);

		final Obstacle leftWall = new Obstacle(0,height/2,depth/2,espace,true);
		leftWall.setMaterial(azurMaterial);
		leftWall.setHeight(height+500);
		leftWall.setDepth(depth+500);
		leftWall.setWidth(5);
		leftWall.setTranslateX(-width);
		//leftWall.setDrawMode(DrawMode.LINE);
		espace.addEnvObj(leftWall);
		wallGroup.getChildren().add(leftWall);

		final Obstacle frontWall  = new Obstacle(width/2,height,0,espace,true);
		frontWall.setMaterial(azurMaterial);
		frontWall.setHeight(height+500);
		frontWall.setDepth(5);
		frontWall.setWidth(width+500);
		frontWall.setTranslateZ(-depth);
		frontWall.setDrawMode(DrawMode.LINE);
		espace.addEnvObj(frontWall);
		wallGroup.getChildren().add(frontWall);

		final Obstacle backWall = new Obstacle(width/2,height/2,depth,espace,true);
		backWall.setMaterial(azurMaterial);
		backWall.setHeight(height+500);
		backWall.setDepth(5);
		backWall.setWidth(width+500);
		backWall.setTranslateZ(depth);
		backWall.setDrawMode(DrawMode.LINE);
		espace.addEnvObj(backWall);
		wallGroup.getChildren().add(backWall);

		final Obstacle topWall = new Obstacle(width/2,height,depth/2,espace,true);
		topWall.setMaterial(azurMaterial);
		topWall.setHeight(5);
		topWall.setDepth(depth+500);
		topWall.setWidth(width+500);
		topWall.setTranslateY(height);
		topWall.setDrawMode(DrawMode.LINE);
		espace.addEnvObj(topWall);
		wallGroup.getChildren().add(topWall);


		final Obstacle botWall = new Obstacle(width/2,0,depth/2,espace,true);
		botWall.setMaterial(azurMaterial);
		botWall.setHeight(5);
		botWall.setDepth(depth+500);
		botWall.setWidth(width+500);
		botWall.setTranslateY(-height);

		botWall.setDrawMode(DrawMode.LINE);
		espace.addEnvObj(botWall);
		wallGroup.getChildren().add(botWall);

		/* Création des obstacles
		 * */

		final Obstacle avoidWall = new Obstacle(0,0,0,espace,true);
		avoidWall.setMaterial(blueMaterial);
		avoidWall.setHeight(400);
		avoidWall.setWidth(200);
		avoidWall.setDepth(200);
		avoidWall.setTranslateX(300);
		espace.addEnvObj(avoidWall);

		wallGroup.getChildren().add(avoidWall);

		root3D.getChildren().add(wallGroup);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Map<UUID,AgentBody> getBoids() {
		return boids;
	}

	public void setBoids(Map<UUID,AgentBody> boids) {
		this.boids = boids;
	}

	public void repaint(){
		for(AgentBody body : boids.values()){
			body.getBoidMesh().setTranslateX(body.getPosition().getX());
			body.getBoidMesh().setTranslateY(body.getPosition().getY());
			body.getBoidMesh().setTranslateZ(body.getPosition().getZ());
		}
	}
}
