package ovh.kacperwojcicki.service;

import jakarta.inject.Singleton;
import ovh.kacperwojcicki.controller.dto.AuthorSaveDTO;
import ovh.kacperwojcicki.model.Author;
import ovh.kacperwojcicki.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Singleton
//@RequiredArgsConstructor
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
        if(authorRepository.existsById(authorId)) {
            authorRepository.deleteById(authorId);
        } else {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public Author updateAuthor(AuthorSaveDTO author, long authorId) {
        if(!authorRepository.existsById(authorId)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Author authorToUpdate = getAuthor(authorId)
                .orElseThrow(() -> new RuntimeException());

        authorToUpdate.setFirstName(author.firstName());
        authorToUpdate.setLastName(author.lastName());

        return authorRepository.save(authorToUpdate);
    }

}
