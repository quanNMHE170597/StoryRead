package com.storywatpad.backend.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "Genre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GenreId")
    private Long genreId;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @Column(name = "Description")
    private String description;
}
