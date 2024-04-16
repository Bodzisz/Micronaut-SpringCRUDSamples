package ovh.kacperwojcicki.sample.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ovh.kacperwojcicki.sample.spring.controller.dto.AuthorDTO;
import ovh.kacperwojcicki.sample.spring.controller.dto.BookDTO;
import ovh.kacperwojcicki.sample.spring.controller.dto.BookSaveDTO;
import ovh.kacperwojcicki.sample.spring.model.Author;
import ovh.kacperwojcicki.sample.spring.model.Book;
import ovh.kacperwojcicki.sample.spring.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getBooks().stream()
                .map(this::toBookDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBook(@PathVariable long bookId) {
        Book book = bookService.getBook(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return ResponseEntity.ok(toBookDTO(book));
    }

    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookSaveDTO bookSaveDTO) {
        Book savedBook = bookService.saveBook(bookSaveDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(toBookDTO(savedBook));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable long bookId, BookSaveDTO bookSaveDTO) {
        return ResponseEntity.status(200).build();
    }

    private BookDTO toBookDTO(Book book) {
        Author author = book.getAuthor();
        AuthorDTO authorDTO = new AuthorDTO(
                author.getAuthorId(),
                author.getFirstName(),
                author.getLastName()
        );
        return new BookDTO(book.getBookId(), book.getTitle(), book.getPages(), book.getDescription(), authorDTO);
    }

    private Book fromBookSaveDto(BookSaveDTO bookSaveDTO) {
        Book book = new Book();
        book.setPages(bookSaveDTO.pages());
        book.setTitle(bookSaveDTO.title());
        book.setDescription(bookSaveDTO.description());
        return book;
    }
}
