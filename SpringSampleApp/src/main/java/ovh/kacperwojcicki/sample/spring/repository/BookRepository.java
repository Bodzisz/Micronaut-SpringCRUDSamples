package ovh.kacperwojcicki.sample.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ovh.kacperwojcicki.sample.spring.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
