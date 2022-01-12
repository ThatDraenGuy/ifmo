package main;

public class RocketNotReadyException extends Exception {
	public RocketNotReadyException(String message) {
		super(message);
	}
}