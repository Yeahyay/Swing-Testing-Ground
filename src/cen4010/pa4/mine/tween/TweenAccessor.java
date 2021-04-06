package cen4010.pa4.mine.tween;

// a functional interface for accessing data for a tween
@FunctionalInterface
public interface TweenAccessor <T> {
	public T update(T from, T to, TweenEquation equation);
}
