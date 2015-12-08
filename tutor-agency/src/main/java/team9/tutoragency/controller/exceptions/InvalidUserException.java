package team9.tutoragency.controller.exceptions;

public class InvalidUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidUserException(String s) {
        super(s);
    }
}
