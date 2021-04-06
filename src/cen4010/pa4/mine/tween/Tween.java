package cen4010.pa4.mine.tween;

import java.util.ArrayList;
import java.util.ArrayDeque;

import cen4010.pa4.mine.core.Time;

// class that tweens stuff
public class Tween <T> {
	// TWEEN CLASS

	// addresses concurrency issues with event based systems
	private static ArrayList<Tween<?>> tweens = new ArrayList<Tween<?>>();
	private static ArrayDeque<Tween<?>> addList = new ArrayDeque<Tween<?>>();
	private static ArrayDeque<Tween<?>> removeList = new ArrayDeque<Tween<?>>();
	
	public static <T> Tween<T> to(T from, T to, TweenAccessor<T> accessor, TweenModifier<T> setter) {
		Tween<T> tween = new Tween<T>(from, to, accessor, setter);
		addList.add(tween);
		return tween;
	}
	
	private static void kill(Tween<?> tween) {
		tweens.remove(tween);
	}
	
	public static void update(double dt) {
		while(!addList.isEmpty()) {
			tweens.add(addList.pop());
		}
		for (Tween<?> tween : tweens) {
			if (tween.updateTween(Time.getTime()) == null) {
				removeList.add(tween);
			}
		}
		while(!removeList.isEmpty()) {
			Tween.kill(removeList.pop());
		}
	}

	// TWEEN OBJECT

	private double startTime;
	private double duration;
	private double tweenTime;
	private boolean dead;

	private T from;
	private T to;
	private T delta;
	private TweenAccessor<T> accessor;
	private TweenModifier<T> setter;
	private TweenEquation equation;

	public Tween(T from, T to, TweenAccessor<T> accessor, TweenModifier<T> setter) {
		duration = 1;
		tweenTime = 0;
		dead = false;

		this.accessor = accessor;
		this.setter = setter;
		this.from = from;
		this.to = to;
		this.equation = new TweenEquation() {
			// quadratic out easing; currently hardcoded in here for testing
			@Override
			public double math(double a, double b) {
				double t = Math.max(0, Math.min(1, tweenTime));
				return a - (b - a) * t * (t - 2);
			}
		};
	}
	
	// sets the tween's duration
	public Tween<T> duration(double time) {
		duration = time;
		return this;
	}
	
	// starts the tween
	public void start() {
		startTime = Time.getTime();
	}
	
	// updates the tween
	public Tween<T> updateTween(double time) {
		if (dead) return null;
		tweenTime = (time - startTime) / duration;

		delta = accessor.update(from, to, equation);
		setter.update(delta);

		if (tweenTime > 1.0) {
			System.out.println("Tween done");
			dead = true;
		}
		return this;
	}
}
