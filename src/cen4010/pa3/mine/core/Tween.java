package cen4010.pa3.mine.core;

import java.awt.Point;
import java.util.ArrayList;

public class Tween {
	static double t;
	
	ArrayList<Tween> tweens;
	
	public static double lerp(double a, double b, double t) {
		t = Math.max(0, Math.min(1, t));
		return a - (b - a) * t * (t - 2);
	}
	
	public static Point tweenPoint(Point a, Point b, double t) {
		return new Point((int) (lerp(a.x, b.x, t)), (int) (lerp(a.x, b.x, t)));
	}

}
