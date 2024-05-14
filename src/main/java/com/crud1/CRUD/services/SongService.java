package com.crud1.CRUD.services;

import com.crud1.CRUD.model.Song;
import com.crud1.CRUD.repositories.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getAllSongs() {
        return songRepository.getAllSongs();
    }

    public String createSong(Song newSong) {
        return songRepository.createSong(newSong);
    }
    public void updateSong(int id, Song updatedSong) {
        songRepository.updateSong(id, updatedSong);
    }

    public void deleteSong(int id) {
        songRepository.deleteSong(id);
    }
}
