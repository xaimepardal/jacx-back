package com.apm.jacx.service;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.Image;
import com.apm.jacx.model.WayPoint;
import com.apm.jacx.repository.WayPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WayPointService {

    @Autowired
    WayPointRepository wayPointRepository;

    public WayPoint create(WayPoint wayPoint) {
        return wayPointRepository.saveAndFlush(wayPoint);
    }

    public WayPoint update(WayPoint wayPoint) {
        WayPoint wayPoint1 = findById(wayPoint.getId());
        if (wayPoint.getName() != null) {
            wayPoint1.setName(wayPoint.getName());
        }
        if (wayPoint.getPoint() != null) {
            wayPoint1.setPoint(wayPoint.getPoint());
        }
        if (wayPoint.getUrl() != null) {
            wayPoint1.setUrl(wayPoint.getUrl());
        }
        if (wayPoint.getColor() != null) {
            wayPoint1.setColor(wayPoint.getColor());
        }
        if (wayPoint.getOrderPosition() != null) {
            wayPoint1.setOrderPosition(wayPoint.getOrderPosition());
        }
        return wayPointRepository.saveAndFlush(wayPoint1);
    }

    public WayPoint findById(Long id) {
        return wayPointRepository.getReferenceById(id);
    }

    public void delete(WayPoint wayPoint) {
        wayPointRepository.delete(wayPoint);
    }

    public List<WayPoint> getAll() {
        return wayPointRepository.findAll();
    }
}
