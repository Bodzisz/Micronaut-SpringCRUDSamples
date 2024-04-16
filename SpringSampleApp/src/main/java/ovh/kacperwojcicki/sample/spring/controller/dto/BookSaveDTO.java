package ovh.kacperwojcicki.sample.spring.controller.dto;

public record BookSaveDTO(String title, int pages, String description, long authorId) {}
