package ovh.kacperwojcicki.repository;


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import ovh.kacperwojcicki.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
