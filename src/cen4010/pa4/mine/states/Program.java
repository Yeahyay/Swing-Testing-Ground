package cen4010.pa4.mine.states;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import cen4010.pa4.mine.core.Time;
import cen4010.pa4.mine.core.Window;

public class Program {
	static Window window;
	
	private static void start() {
		
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
		
		AffineTransform affineTransform = graphicsConfiguration.getDefaultTransform();
		double scaleX = affineTransform.getScaleX();
		double scaleY = affineTransform.getScaleY();
		System.out.printf("%f, %f\n", scaleX, scaleY);
		
		window = new Window(1280, 720, graphicsConfiguration);
		window.addState(new Game(window));
		window.addState(new StartMenu(window));
		window.addState(new OptionsMenu(window));

		window.pushState("Main");
		
		window.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				start();
				
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						while(window.isVisible()) {
							try {
								
								Time.calculateTimer();
								Time.calculateFrameTimeStart();
								SwingUtilities.invokeAndWait(window);
								
								Time.calculateFrameTimeEnd();
								
								Thread.sleep(1);

							} catch (InvocationTargetException | InterruptedException e) {
								e.printStackTrace();
							}	
						}
					}
					
				});
				thread.start();
			}
		});
	}
}
