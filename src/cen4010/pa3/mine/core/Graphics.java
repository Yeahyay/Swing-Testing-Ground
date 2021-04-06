package cen4010.pa3.mine.core;

import java.util.ArrayDeque;
import java.awt.Canvas;

public class Graphics extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum DrawType {
		rectangle,
		circle,
	}
	
	private class DrawObject {
		public int positionX;
		public int positionY;
		public int sizeX;
		public int sizeY;
		public int radius;
		@SuppressWarnings("unused")
		public double angle;
		public DrawType shape = DrawType.rectangle;
		public DrawObject(DrawType shape, int x, int y, int sizeX, int sizeY, double angle) {
			this.shape = shape;
			positionX = x;
			positionY = y;
			this.sizeX = sizeX;
			this.sizeY = sizeY;
			this.angle = angle;
		}
		public DrawObject(DrawType shape, int x, int y, int radius) {
			this.shape = shape;
			positionX = x;
			positionY = y;
			this.radius = radius;
		}
	}
	
	ArrayDeque<DrawObject> drawQueue = new ArrayDeque<DrawObject>();
	public void queueDraw(DrawType shape, int x, int y, int sizeX, int sizeY) {
		drawQueue.add(new DrawObject(shape, x, y, sizeX, sizeY, 0));
	}
	public void queueDraw(DrawType shape, int x, int y, int radius) {
		drawQueue.add(new DrawObject(shape, x, y, radius));
	}
	
	@Override
	public void paint(java.awt.Graphics g) {
		drawQueue.forEach((DrawObject object) -> {
			switch (object.shape) {
			case rectangle:
				g.drawOval(object.positionX, object.positionY, object.sizeX, object.sizeY);
				break;
			case circle:
				g.drawOval(object.positionX, object.positionY, object.radius, object.radius);
				break;
			}
		});
	}
}
