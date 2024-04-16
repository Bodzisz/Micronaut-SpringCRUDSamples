package ovh.kacperwojcicki.controller.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record BookSaveDTO(String title, int pages, String description, long authorId) {}
