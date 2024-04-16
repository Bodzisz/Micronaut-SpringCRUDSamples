package ovh.kacperwojcicki.sample.spring.controller.dto;

public record BookDTO(long bookId, String title, int pages, String description, AuthorDTO author) {
}
