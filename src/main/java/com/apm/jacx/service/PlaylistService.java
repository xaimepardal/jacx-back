package com.apm.jacx.service;

import com.apm.jacx.model.Playlist;
import com.apm.jacx.model.Route;
import com.apm.jacx.model.dtos.RouteModel;
import com.apm.jacx.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    public Playlist create (Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public List<Playlist> getAll() {
        return playlistRepository.findAll();
    }

    public void delete (Playlist playlist) {
        playlistRepository.delete(playlist);
    }

    public Playlist getBySpotifyId(String spotifyId) {
        return playlistRepository.findBySpotifyId(spotifyId);
    }
}
