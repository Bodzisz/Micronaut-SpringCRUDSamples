package ovh.kacperwojcicki.repository;


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import ovh.kacperwojcicki.model.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findAll();
}
