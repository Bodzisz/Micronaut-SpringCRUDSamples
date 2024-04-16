package ovh.kacperwojcicki.service;

import jakarta.inject.Singleton;
import ovh.kacperwojcicki.controller.dto.BookSaveDTO;
import ovh.kacperwojcicki.model.Author;
import ovh.kacperwojcicki.model.Book;
import ovh.kacperwojcicki.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Singleton
//@RequiredArgsConstructor
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
        Author author = authorService.getAuthor(bookToSave.authorId())
                .orElseThrow(() -> new RuntimeException());

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
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        bookRepository.deleteById(bookId);
    }

    public Book updateBook(long bookId, BookSaveDTO bookSaveDTO) {
        return null;
    }
}
