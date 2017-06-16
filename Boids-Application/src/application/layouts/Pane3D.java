package application.layouts;

import java.util.Optional;

import application.util.Xform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public final class Pane3D extends Region{

	private double CAMERA_INITIAL_DISTANCE = -1200;
    private double CAMERA_INITIAL_DISTANCE2 = 0;
    private final double CAMERA_INITIAL_X_ANGLE = -30;
    private final double CAMERA_INITIAL_Y_ANGLE = 30.0;

    private final double CONTROL_MULTIPLIER = 0.1;
    private final double SHIFT_MULTIPLIER = 10.0;
    private final double MOUSE_SPEED = 0.1;
    private final double ROTATION_SPEED = 1.0;
    private final double TRACK_SPEED = 0.3;

	private SubScene subScene;

	final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    final double cameraDistance = 600;

    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;

	 public Pane3D() {
	        super();
	        resetSubScene();
	        antialiasingProperty().addListener(observable -> resetSubScene());
	        contentProperty().addListener(observable -> {
	            final Node content = getContent();
	            final Group sceneRoot = (Group) subScene.getRoot();
	            if (content != null) {
	                sceneRoot.getChildren().setAll(content);
	            } else {
	                sceneRoot.getChildren().clear();
	            }
	        });
	    }

	@Override
    protected void layoutChildren() {
        super.layoutChildren();
        // Redimensionne la sous-scene lorsque le parent change de taille.
        if (subScene != null) {
            final double width = getWidth();
            final double height = getHeight();
            final Insets insets = getInsets();
            final double contentX = insets.getLeft();
            final double contentY = insets.getTop();
            final double contentW = width - (insets.getLeft() + insets.getRight());
            final double contentH = height - (insets.getTop() + insets.getBottom());
            subScene.relocate(contentX, contentY);
            subScene.setWidth(contentW);
            subScene.setHeight(contentH);
        }
    }


	private void resetSubScene(){
		Node content = null;
		if(subScene != null){
			final Group sceneRoot = (Group) subScene.getRoot();
			content = sceneRoot.getChildren().remove(0);
			getChildren().remove(subScene);
		}
		final Group sceneRoot = new Group();
		if(content != null){
			sceneRoot.getChildren().add(content);
		}
		subScene = new SubScene(sceneRoot, 1, 1,true,getAntialiasing());
		PerspectiveCamera camera = buildCamera();
		subScene.setCamera(camera);
		subScene.setFill(Color.LIGHTGRAY);
		setMouseEventsToScene(subScene, camera);
		getChildren().add(subScene);
		requestLayout();
	}

	private final ReadOnlyObjectWrapper<SceneAntialiasing> antialiasing = new ReadOnlyObjectWrapper<>(this, "antialiasing", SceneAntialiasing.DISABLED);

    public final SceneAntialiasing getAntialiasing() {
        return antialiasing.get();
    }

    public final void setAntialiasing(final SceneAntialiasing value) {
        final Optional<SceneAntialiasing> op = Optional.ofNullable(value);
        op.ifPresent(val -> antialiasing.set(val));
    }

    public final ReadOnlyObjectProperty<SceneAntialiasing> antialiasingProperty() {
        return antialiasing.getReadOnlyProperty();
    }

    private final ObjectProperty<Node> content = new SimpleObjectProperty<>(this, "content");

    public final Node getContent() {
        return content.get();
    }

    public final void setContent(final Node value) {
        content.set(value);
    }

    public final ObjectProperty<Node> contentProperty() {
        return content;
    }


    private PerspectiveCamera buildCamera() {

    	final Group sceneRoot = (Group) subScene.getRoot();

        sceneRoot.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-cameraDistance);
        cameraXform.ry.setAngle(320.0);
        cameraXform.rx.setAngle(40);

        return camera;
    }

    @SuppressWarnings("incomplete-switch")
	public void setMouseEventsToScene(SubScene scene, PerspectiveCamera camera) {


        scene.setOnMousePressed(me -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        });

		scene.setOnScroll(event -> {
            if (event.getDeltaY() < 0) {
                CAMERA_INITIAL_DISTANCE *= 1.1;
            } else {
                CAMERA_INITIAL_DISTANCE *= 0.9;
            }
            camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        });

        scene.setOnMouseDragged(me -> {
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX);
            mouseDeltaY = (mousePosY - mouseOldY);

            double modifier = 1.0;

            if (me.isControlDown()) {
                modifier = CONTROL_MULTIPLIER;
            }
            if (me.isShiftDown()) {
                modifier = SHIFT_MULTIPLIER;
            }
            if (me.isPrimaryButtonDown()) {
                cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX * MOUSE_SPEED * modifier * ROTATION_SPEED);
                cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY * MOUSE_SPEED * modifier * ROTATION_SPEED);
            } else if (me.isSecondaryButtonDown()) {
                double z = camera.getTranslateZ();
                double newZ = z + mouseDeltaX * MOUSE_SPEED * modifier;
                camera.setTranslateZ(newZ);
            } else if (me.isMiddleButtonDown()) {
                cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX * MOUSE_SPEED * modifier * TRACK_SPEED);
                cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY * MOUSE_SPEED * modifier * TRACK_SPEED);
            }
        });

        scene.setOnKeyPressed(me -> {
			switch (me.getCode()) {
                case UP:
                    camera.setTranslateZ(CAMERA_INITIAL_DISTANCE += 10);
                    break;
                case DOWN:
                    camera.setTranslateZ(CAMERA_INITIAL_DISTANCE -= 10);
                    break;
                case LEFT:
                    camera.setTranslateX(CAMERA_INITIAL_DISTANCE2 -= 5);
                    break;
                case RIGHT:
                    camera.setTranslateX(CAMERA_INITIAL_DISTANCE2 += 5);
                    break;
            }
        });
        scene.setOnMouseClicked(me -> {
            if (me.getButton() == MouseButton.SECONDARY) {
                CAMERA_INITIAL_DISTANCE = -1200;
                camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
                cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
            }
        });
    }
}
