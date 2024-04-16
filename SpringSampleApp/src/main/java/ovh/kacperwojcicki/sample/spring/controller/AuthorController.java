package ovh.kacperwojcicki.sample.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ovh.kacperwojcicki.sample.spring.controller.dto.AuthorDTO;
import ovh.kacperwojcicki.sample.spring.controller.dto.AuthorSaveDTO;
import ovh.kacperwojcicki.sample.spring.model.Author;
import ovh.kacperwojcicki.sample.spring.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAuthors() {
        return ResponseEntity.ok(authorService.getAuthors().stream()
                .map(author -> new AuthorDTO(author.getAuthorId(), author.getFirstName(), author.getLastName()))
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable long authorId) {
        Author author = authorService.getAuthor(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        return ResponseEntity.ok(new AuthorDTO(author.getAuthorId(), author.getFirstName(), author.getLastName()));
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable long authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<Author> updateAuthor(@PathVariable long authorId, AuthorSaveDTO authorSaveDTO) {
        return ResponseEntity.ok(authorService.updateAuthor(authorSaveDTO, authorId));
    }

    @PostMapping
    public ResponseEntity<Author> saveAuthor(@RequestBody AuthorSaveDTO newAuthor) {
        Author savedAuthor = authorService.saveAuthor(newAuthor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
    }
}
