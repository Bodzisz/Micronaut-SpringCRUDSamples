package ovh.kacperwojcicki.exception.handlers;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import ovh.kacperwojcicki.exception.ErrorMessage;
import ovh.kacperwojcicki.exception.HttpBodyMessageException;

@Produces
@Singleton
@Requires(classes = {HttpBodyMessageException.class, ExceptionHandler.class})
public class HttpBodyMessageExceptionHandler implements ExceptionHandler<HttpBodyMessageException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, HttpBodyMessageException exception) {
        return HttpResponse.status(exception.getHttpStatus()).body(new ErrorMessage(exception.getErrorMessage()));
    }
}
