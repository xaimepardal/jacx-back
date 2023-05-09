package com.apm.jacx.repository;

import com.apm.jacx.model.Playlist;
import com.apm.jacx.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Playlist findBySpotifyId(String spotifyId);
}
