package com.apm.jacx.repository;

import com.apm.jacx.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Playlist, Long> {
}
