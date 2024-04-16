package ovh.kacperwojcicki.controller.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record BookDTO(long bookId, String title, int pages, String description, AuthorDTO author) {
}
