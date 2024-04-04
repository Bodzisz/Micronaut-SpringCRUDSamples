package ovh.kacperwojcicki.sample.spring.dto;

import ovh.kacperwojcicki.sample.spring.model.Author;

import java.util.HashSet;

public record AuthorSaveDTO(String firstName, String lastName) {
    public Author toAuthor() {
        final Author author = new Author();
        author.setAuthorId(0);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBooks(new HashSet<>());
        return author;
    }
}
