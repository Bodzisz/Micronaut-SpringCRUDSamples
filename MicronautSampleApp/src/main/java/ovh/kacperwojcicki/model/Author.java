package ovh.kacperwojcicki.model;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "authors")
@Serdeable
//@NoArgsConstructor
//@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long authorId;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Book> books;

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
