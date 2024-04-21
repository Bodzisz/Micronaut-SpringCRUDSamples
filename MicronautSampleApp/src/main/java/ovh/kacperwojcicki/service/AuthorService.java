package ovh.kacperwojcicki.service;

import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;
import ovh.kacperwojcicki.controller.dto.AuthorSaveDTO;
import ovh.kacperwojcicki.exception.HttpBodyMessageException;
import ovh.kacperwojcicki.model.Author;
import ovh.kacperwojcicki.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Singleton
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthor(long authorId) {
        return authorRepository.findById(authorId);
    }

    public Author saveAuthor(AuthorSaveDTO authorToSave) {
        return authorRepository.save(authorToSave.toAuthor());
    }

    public void deleteAuthor(long authorId) {
        if(!authorRepository.existsById(authorId)) {
            throw new HttpBodyMessageException(HttpStatus.BAD_REQUEST, "Author with such id does not exist:" + authorId);
        }

        authorRepository.deleteById(authorId);
    }

    public Author updateAuthor(AuthorSaveDTO author, long authorId) {
        Author authorToUpdate = authorRepository.findById(authorId).orElseThrow(() ->
                new HttpBodyMessageException(HttpStatus.BAD_REQUEST, "Author with such id does not exist:" + authorId));

        authorToUpdate.setFirstName(author.firstName());
        authorToUpdate.setLastName(author.lastName());

        return authorRepository.save(authorToUpdate);
    }

}
