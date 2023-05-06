package com.apm.jacx.service;

import com.apm.jacx.model.Playlist;
import com.apm.jacx.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public Playlist create (Playlist playlist) {
        return songRepository.save(playlist);
    }

    public List<Playlist> getAll() {
        return songRepository.findAll();
    }

    public void delete (Playlist playlist) {
        songRepository.delete(playlist);
    }

    public Optional<Playlist> findById (Long id) {
        return songRepository.findById(id);
    }

    public Playlist update(Playlist playlist) { return songRepository.save(playlist); }
}
