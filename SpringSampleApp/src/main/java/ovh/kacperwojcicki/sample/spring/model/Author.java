package ovh.kacperwojcicki.sample.spring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long authorId;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Book> books;
}
