package com.crud1.CRUD.controllers;

import com.crud1.CRUD.model.Song;
import com.crud1.CRUD.services.SongService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/songs")
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @PostMapping("/song")
    public String createSong(@RequestBody Song newSong) {
        return songService.createSong(newSong);
    }
    @PutMapping("/song/{id}")
    public void updateSong(@PathVariable int id, @RequestBody Song updatedSong) {
        songService.updateSong(id, updatedSong);
    }

    @DeleteMapping("/song/{id}")
    public void deleteSong(@PathVariable int id) {
        songService.deleteSong(id);
    }
}
