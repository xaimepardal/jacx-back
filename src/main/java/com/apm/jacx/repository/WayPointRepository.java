package com.apm.jacx.repository;

import com.apm.jacx.model.Playlist;
import com.apm.jacx.model.WayPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WayPointRepository extends JpaRepository<WayPoint, Long> {
}
