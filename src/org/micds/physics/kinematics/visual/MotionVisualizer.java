package org.micds.physics.kinematics.visual;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.micds.physics.kinematics.Projectile;
import org.micds.physics.vector.Vector;

import java.util.Random;

public class MotionVisualizer extends Application {
	private Scene scene;
	private Ball ballObj;
	private Rotate rotX, rotY, rotZ;
	private double mouseX, mouseY;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("jPhysics");
		this.scene = new Scene(this.createContent());
		handleMouseEvents();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Parent createContent() {
		// Base rotations
		this.rotX = new Rotate(0, Rotate.X_AXIS);
		this.rotY = new Rotate(0, Rotate.Y_AXIS);
		this.rotZ = new Rotate(0, Rotate.Z_AXIS);

		// Ball projectile
		this.ballObj = new Ball(5, new Projectile(new double[]{0.0, 100.0, 0.0}, new Vector(10.0, 0.0, 0.0)));
		this.ballObj.getTransforms().addAll(rotX, rotY, rotZ);

		Random rand = new Random();
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()));
		material.setSpecularColor(Color.BLACK);
		this.ballObj.setMaterial(material);

		// Create and position camera
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.getTransforms().addAll(rotX, rotY, rotZ, new Translate(0, 0, -50));

		// Build the Scene Graph
		Group root = new Group();
		root.getChildren().add(camera);
		root.getChildren().add(ballObj);

		// Use a SubScene
		final Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		SubScene subScene = new SubScene(root, screen.getWidth() * .75, screen.getHeight() * .75, true, SceneAntialiasing.BALANCED);
		subScene.setLayoutX(screen.getMinX());
		subScene.setLayoutY(screen.getMinY());
		subScene.setFill(Color.TRANSPARENT);
		subScene.setCamera(camera);

		return new Group(subScene);
	}

	private void handleMouseEvents() {
		scene.setOnMousePressed(me -> {
			mouseX = me.getSceneX();
			mouseY = me.getSceneY();
		});

		scene.setOnMouseDragged(me -> {
			final double dx = (mouseX - me.getSceneX()), dy = (mouseY - me.getSceneY());

			if (me.isPrimaryButtonDown() && me.isControlDown()) {
				rotX.setAngle(rotX.getAngle() - (dy / Math.pow(ballObj.getRadius(), 2) * 360) * (Math.PI / 180));
				rotY.setAngle(rotY.getAngle() - (dx / Math.pow(ballObj.getRadius(), 2) * -360) * (Math.PI / 180));
			}

			mouseX = me.getSceneX();
			mouseY = me.getSceneY();
		});
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
