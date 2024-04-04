package ovh.kacperwojcicki.sample.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ovh.kacperwojcicki.sample.spring.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
