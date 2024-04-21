package ovh.kacperwojcicki.exception;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record ErrorMessage(String message) {}
