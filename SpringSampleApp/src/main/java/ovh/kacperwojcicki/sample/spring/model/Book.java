package ovh.kacperwojcicki.sample.spring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    private String title;
    private int pages;
    private String description;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
