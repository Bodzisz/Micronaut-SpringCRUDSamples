package ovh.kacperwojcicki.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import ovh.kacperwojcicki.controller.dto.AuthorDTO;
import ovh.kacperwojcicki.controller.dto.AuthorSaveDTO;
import ovh.kacperwojcicki.model.Author;
import ovh.kacperwojcicki.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/authors")
//@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Get
    public HttpResponse<List<AuthorDTO>> getAuthors() {
        return HttpResponse.ok(authorService.getAuthors().stream()
                .map(author -> new AuthorDTO(author.getAuthorId(), author.getFirstName(), author.getLastName()))
                .collect(Collectors.toList())
        );
    }

    @Post
    public HttpResponse<Author> saveAuthor(@Body AuthorSaveDTO newAuthor) {
        Author savedAuthor = authorService.saveAuthor(newAuthor);
        return HttpResponse.status(HttpStatus.CREATED).body(savedAuthor);
    }
}
