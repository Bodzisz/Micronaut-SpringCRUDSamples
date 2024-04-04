package ovh.kacperwojcicki.sample.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ovh.kacperwojcicki.sample.spring.dto.AuthorSaveDTO;
import ovh.kacperwojcicki.sample.spring.model.Author;
import ovh.kacperwojcicki.sample.spring.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void updateAuthor(Author author) {
        if(!authorRepository.existsById(author.getAuthorId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Author authorToUpdate = getAuthor(author.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        authorToUpdate.setFirstName(author.getFirstName());
        authorToUpdate.setLastName(author.getLastName());

        authorRepository.save(authorToUpdate);
    }

}
