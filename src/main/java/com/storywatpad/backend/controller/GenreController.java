package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.Genre;
import com.storywatpad.backend.model.User;
import com.storywatpad.backend.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
    @GetMapping("/{id}")
    public Genre getUserById(@PathVariable Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public Genre createGenre(@RequestBody Genre genre) {
        return genreRepository.save(genre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable Long id, @RequestBody Genre updatedGenre) {
        return genreRepository.findById(id).map(genre -> {
            genre.setName(updatedGenre.getName());
            genre.setDescription(updatedGenre.getDescription());
            return genreRepository.save(genre);
        }).orElseThrow(() -> new RuntimeException("Genre not found"));
    }

}
