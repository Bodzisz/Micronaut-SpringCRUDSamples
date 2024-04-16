package ovh.kacperwojcicki.controller.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AuthorDTO(long authorId, String firstName, String lastName) {
}
