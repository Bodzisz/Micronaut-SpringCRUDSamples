package ovh.kacperwojcicki.model;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Greeting(String greeting){};
