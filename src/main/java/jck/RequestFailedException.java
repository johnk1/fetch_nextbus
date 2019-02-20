package jck;

public class RequestFailedException extends RuntimeException {
    static final long serialVersionUID = 1;

    public RequestFailedException(String message, Throwable source) {
        super(message, source);
    }
}