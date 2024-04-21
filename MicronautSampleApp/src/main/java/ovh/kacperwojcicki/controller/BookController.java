package ovh.kacperwojcicki.controller;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import ovh.kacperwojcicki.controller.dto.AuthorDTO;
import ovh.kacperwojcicki.controller.dto.BookDTO;
import ovh.kacperwojcicki.controller.dto.BookSaveDTO;
import ovh.kacperwojcicki.exception.HttpBodyMessageException;
import ovh.kacperwojcicki.model.Author;
import ovh.kacperwojcicki.model.Book;
import ovh.kacperwojcicki.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Get
    public HttpResponse<List<BookDTO>> getAllBooks() {
        return HttpResponse.ok(bookService.getBooks().stream()
                .map(this::toBookDTO)
                .collect(Collectors.toList()));
    }

    @Get("/{bookId}")
    public HttpResponse<BookDTO> getBook(@PathVariable long bookId) {
        Book book = bookService.getBook(bookId)
                .orElseThrow(() -> new HttpBodyMessageException(HttpStatus.BAD_REQUEST,
                        "Book with such id does not exist: " + bookId));

        return HttpResponse.ok(toBookDTO(book));
    }

    @Post
    public HttpResponse<BookDTO> saveBook(@Body BookSaveDTO bookSaveDTO) {
        Book savedBook = bookService.saveBook(bookSaveDTO);
        return HttpResponse.status(HttpStatus.CREATED).body(toBookDTO(savedBook));
    }

    @Delete("/{bookId}")
    public HttpResponse<Void> deleteBook(@PathVariable long bookId) {
        bookService.deleteBook(bookId);
        return HttpResponse.status(HttpStatus.NO_CONTENT);
    }

    @Put("/{bookId}")
    public HttpResponse<BookDTO> updateBook(@PathVariable long bookId, BookSaveDTO bookSaveDTO) {
        return HttpResponse.status(HttpStatus.OK);
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
