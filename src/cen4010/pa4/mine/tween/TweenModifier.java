package cen4010.pa4.mine.tween;

// a functional interface for modifying the object a tween acts upon
public interface TweenModifier <T> {
	public void update(T value);
}
