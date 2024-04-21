package ovh.kacperwojcicki.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.exceptions.HttpStatusException;
import ovh.kacperwojcicki.controller.dto.AuthorDTO;
import ovh.kacperwojcicki.controller.dto.AuthorSaveDTO;
import ovh.kacperwojcicki.model.Author;
import ovh.kacperwojcicki.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Get
    public HttpResponse<List<AuthorDTO>> getAuthors() {
        return HttpResponse.ok(authorService.getAuthors().stream()
                .map(this::toAuthorDTO)
                .collect(Collectors.toList())
        );
    }

    @Get("/{id}")
    public HttpResponse<AuthorDTO> getAuthor(@PathVariable long id) {
        Author author = authorService.getAuthor(id).orElseThrow(() ->
                new HttpStatusException(HttpStatus.BAD_REQUEST, "Author with such id does not exist: " + id)
        );

        return HttpResponse.ok(toAuthorDTO(author));
    }

    @Post
    public HttpResponse<Author> saveAuthor(@Body AuthorSaveDTO newAuthor) {
        Author savedAuthor = authorService.saveAuthor(newAuthor);
        return HttpResponse.status(HttpStatus.CREATED).body(savedAuthor);
    }

    @Delete("/{id}")
    public HttpResponse<Void> deleteAuthor(@PathVariable long id) {
        authorService.deleteAuthor(id);
        return HttpResponse.status(HttpStatus.NO_CONTENT);
    }

    @Put("/{authorId}")
    public HttpResponse<Author> updateAuthor(@PathVariable long authorId, AuthorSaveDTO authorSaveDTO) {
        return HttpResponse.ok(authorService.updateAuthor(authorSaveDTO, authorId));
    }

    private AuthorDTO toAuthorDTO(Author author) {
        return new AuthorDTO(author.getAuthorId(), author.getFirstName(), author.getLastName());
    }
}
