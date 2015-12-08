package team9.tutoragency.controller.exceptions;

public class UsernameDuplicateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsernameDuplicateException(String s) {
        super(s);
    }
}
