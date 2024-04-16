package ovh.kacperwojcicki.controller.dto;


import io.micronaut.serde.annotation.Serdeable;
import ovh.kacperwojcicki.model.Author;

import java.util.HashSet;

@Serdeable
public record AuthorSaveDTO(String firstName, String lastName) {
    public Author toAuthor() {
        final Author author = new Author();
        author.setAuthorId(0L);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBooks(new HashSet<>());
        return author;
    }
}
