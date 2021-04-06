package cen4010.pa3.mine.core;

public class Time {
    private static long startTime = System.nanoTime();
    private static long lastTime = 0;
    private static long deltaTime = 0;
    private static double fps = 0;

    private static long timerNS = 0;
    private static double timer = 0;
   
    public static double getFPS() {
    	return fps;
    }
    public static double getTime() {
    	return timer;
    }
	public static void calculateTimer() {
		timerNS += deltaTime;
		timer = timerNS / 1000000000.0;
	}
	public static void calculateFrameTimeStart() {
		deltaTime = (System.nanoTime() - startTime) - lastTime;

		fps = 1000000000.0 / (double)deltaTime;

		lastTime = System.nanoTime() - startTime;
	}
	public static void calculateFrameTimeEnd() {

		fps = 1000000000.0 / (double)deltaTime;

		lastTime = System.nanoTime() - startTime;
	}
}
