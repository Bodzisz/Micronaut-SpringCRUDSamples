package ovh.kacperwojcicki.sample.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ovh.kacperwojcicki.sample.spring.controller.dto.BookSaveDTO;
import ovh.kacperwojcicki.sample.spring.model.Author;
import ovh.kacperwojcicki.sample.spring.model.Book;
import ovh.kacperwojcicki.sample.spring.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(long bookId) {
        return bookRepository.findById(bookId);
    }

    public Book saveBook(BookSaveDTO bookToSave) {
        Author author = authorService.getAuthor(bookToSave.authorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        bookRepository.deleteById(bookId);
    }

    public Book updateBook(long bookId, BookSaveDTO bookSaveDTO) {
        return null;
    }
}
