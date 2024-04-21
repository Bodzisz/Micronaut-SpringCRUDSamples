package ovh.kacperwojcicki.exception;

import io.micronaut.http.HttpStatus;

public class HttpBodyMessageException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorMessage;

    public HttpBodyMessageException(HttpStatus status, String errorMessage) {
        this.httpStatus = status;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}