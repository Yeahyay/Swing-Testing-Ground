package cen4010.pa4.mine.states;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import cen4010.pa4.mine.core.Time;
import cen4010.pa4.mine.core.Window;
import cen4010.pa4.mine.tween.Tween;

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

		window.pushState("Start");
		
		window.setVisible(true);
	}
	
	public static void main(String[] args) {
		// ensures that all Swing-related statements occur on Swing's Event Dispatch Thread
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
								// Tweens can modify Swing data, so this is probably needed
								SwingUtilities.invokeAndWait(new Runnable() {
									@Override
									public void run() {
										Tween.update(Time.getDelta());
									}
								});
								SwingUtilities.invokeAndWait(window);
								
								Time.calculateFrameTimeEnd();
								
//								System.out.println(Time.getDelta());
//								System.out.println(Time.getFPS());
								
								Thread.sleep(15);

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
