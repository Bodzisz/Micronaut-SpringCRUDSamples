package ovh.kacperwojcicki.service;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpException;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.inject.Singleton;
import ovh.kacperwojcicki.controller.dto.BookSaveDTO;
import ovh.kacperwojcicki.exception.HttpBodyMessageException;
import ovh.kacperwojcicki.model.Author;
import ovh.kacperwojcicki.model.Book;
import ovh.kacperwojcicki.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Singleton
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(long bookId) {
        return bookRepository.findById(bookId);
    }

    public Book saveBook(BookSaveDTO bookToSave) {
        long authorId = bookToSave.authorId();
        Author author = authorService.getAuthor(authorId)
                .orElseThrow(() -> new HttpBodyMessageException(HttpStatus.BAD_REQUEST,
                        "Author with such id does not exist: " + authorId));

        Book book = new Book();
        book.setBookId(0);
        book.setTitle(bookToSave.title());
        book.setPages(bookToSave.pages());
        book.setDescription(bookToSave.description());
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public void deleteBook(long bookId) {
        if(!bookRepository.existsById(bookId)) {
            throw new HttpBodyMessageException(HttpStatus.BAD_REQUEST, "Book with such id does not exist: " + bookId);
        }

        bookRepository.deleteById(bookId);
    }

    public Book updateBook(long bookId, BookSaveDTO bookSaveDTO) {
        return null;
    }
}
