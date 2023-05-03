package com.apm.jacx.service;

import com.apm.jacx.model.Route;
import com.apm.jacx.model.Song;
import com.apm.jacx.repository.RouteRepository;
import com.apm.jacx.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public Song create (Song song) {
        return songRepository.save(song);
    }

    public List<Song> getAll() {
        return songRepository.findAll();
    }

    public void delete (Song song) {
        songRepository.delete(song);
    }

    public Optional<Song> findById (Long id) {
        return songRepository.findById(id);
    }
}
