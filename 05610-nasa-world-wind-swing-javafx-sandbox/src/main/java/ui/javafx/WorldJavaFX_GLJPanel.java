package ui.javafx;

import javax.swing.SwingUtilities;

import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLJPanel;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// http://forum.worldwindcentral.com/showthread.php?45556-Simple-example-WorldWind-(2-0)-JavaFX-(8-0)

public class WorldJavaFX_GLJPanel extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		StackPane root = new StackPane();
		root.getChildren().add(buildWW());
		stage.setScene(new Scene(root, 800, 600));
		stage.show();
	}

	private SwingNode buildWW() {
		SwingNode swingNode = new SwingNode();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				WorldWindowGLJPanel wwjGLJPanel = new WorldWindowGLJPanel();
				Model wwjModel = (Model) WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
				wwjGLJPanel.setModel(wwjModel);
				swingNode.setContent(wwjGLJPanel);
			}
		});

		return swingNode;
	}

	public static void main(String[] args) {
		launch(args);
	}
}