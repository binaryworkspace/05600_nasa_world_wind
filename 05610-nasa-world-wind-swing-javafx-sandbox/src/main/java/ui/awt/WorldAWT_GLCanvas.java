package ui.awt;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

/**
 * Renders a basic color triangle using JOGL in a AWT Frame.
 * <p>
 * <b>Notes:</b>
 * <ul>
 * <li>This is a modified code example provided by Wade Walker (see links
 * below).
 * <li>Modifications include changing of import statements for JOGL 2.1.5
 * support, minor code formatting and minor comment changes.
 * </ul>
 * 
 * @author Chris Ludka
 *         <p>
 * @see <a href=
 *      "https://jogamp.org/wiki/index.php/Using_JOGL_in_AWT_SWT_and_Swing">
 *      https://jogamp.org/wiki/index.php/Using_JOGL_in_AWT_SWT_and_Swing</a>
 */
public class WorldAWT_GLCanvas {

	public static void main(String[] args) {
		// World Wind
		WorldWindowGLCanvas wwjGLCanvas = new WorldWindowGLCanvas();

		// AWT Frame
		final Frame frame = new Frame("NASA World Wind - AWT");
		frame.setSize(640, 480);
		
		/*
		 * Avoid calling glOrtho before the view dimensions have been
		 * initialized.
		 * 
		 * http://forum.jogamp.org/JOGL-Printing-Error-on-Boot-td4031154.html
		 */
		Model wwjModel = (Model) WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
		wwjGLCanvas.setModel(wwjModel);
		frame.add(wwjGLCanvas);
		
		// Configure frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowevent) {
				frame.remove(wwjGLCanvas);
				frame.dispose();
				System.exit(0);
			}
		});
		
		// Visible
		frame.setVisible(true);
	}
}
