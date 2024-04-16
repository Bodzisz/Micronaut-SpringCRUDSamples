package ovh.kacperwojcicki.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ovh.kacperwojcicki.model.Greeting;

@Controller
public class GreetingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

    @Get("/greeting")
    public Greeting greeting() {
        return new Greeting("Hello World!");
    }

    @Post("/greeting")
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse<Greeting> postGreeting(@Body final Greeting greeting) {
        LOGGER.info("New greeting: " + greeting.greeting());
        if(greeting.greeting().isEmpty()) {
            return HttpResponse.status(HttpStatus.BAD_REQUEST, "Greeting cannot be empty!");
        }
        return HttpResponse.ok(greeting);
    }
}
