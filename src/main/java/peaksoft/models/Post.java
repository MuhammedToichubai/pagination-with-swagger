package peaksoft.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "post")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    String description;
    @ElementCollection
    List<String> images;
    LocalDate cratedData;
    LocalDate updatedData;

    @ManyToOne
    User user;








}
