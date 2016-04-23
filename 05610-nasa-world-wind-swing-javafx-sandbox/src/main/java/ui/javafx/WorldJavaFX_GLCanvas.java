package ui.javafx;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// http://forum.worldwindcentral.com/showthread.php?45556-Simple-example-WorldWind-(2-0)-JavaFX-(8-0)

public class WorldJavaFX_GLCanvas extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// Root
		final StackPane root = new StackPane();
		root.getChildren().add(buildWwjSwingNode());
		
		// Stage
		final Scene scene = new Scene(root, 800, 600);
		stage.setTitle("NASA World Wind (GLCanvas) - JavaFX");
		stage.setScene(scene);
		stage.show();
	}

	private SwingNode buildWwjSwingNode() {
		SwingNode swingNode = new SwingNode();
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				WorldWindowGLCanvas wwjGLCanvas = new WorldWindowGLCanvas();
				final JPanel jPanel = new JPanel(new BorderLayout());
				jPanel.add(wwjGLCanvas, BorderLayout.CENTER);

				Model wwjModel = (Model) WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
				wwjGLCanvas.setModel(wwjModel);
				swingNode.setContent(jPanel);
				
				// Create render thread
				(new Thread() {
					public void run() {
						while (true) {
							wwjGLCanvas.repaint();
							try {
								/*
								 * Don't make loop too tight, or not enough time
								 * to process window messages properly.
								 */
								sleep(1);
							} catch (InterruptedException interruptedexception) {
								/*
								 * Application just quits on interrupt, so
								 * nothing required here.
								 */
							}
						}
					}
				}).start();
			}
		});
		
		return swingNode;
	}

	public static void main(String[] args) {
		launch(args);
	}
}