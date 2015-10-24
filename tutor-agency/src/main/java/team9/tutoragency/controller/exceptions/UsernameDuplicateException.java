package team9.tutoragency.controller.exceptions;

public class UsernameDuplicateException extends RuntimeException {

    public UsernameDuplicateException(String s) {
        super(s);
    }
}
