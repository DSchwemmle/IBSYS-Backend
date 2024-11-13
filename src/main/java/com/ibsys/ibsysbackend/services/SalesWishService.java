package com.ibsys.ibsysbackend.services;

import com.ibsys.ibsysbackend.repositories.VertriebswunschRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalesWishService {

    private VertriebswunschRepository vertriebswunschRepository;

    public Vertriebswunsch getVertriebswunsch() {
        return vertriebswunschRepository.findAll().get(0);
    }

    public void updateVertriebswunsch(final Vertriebswunsch vertriebswunsch) {
        vertriebswunschRepository.save(vertriebswunsch);
    }
}
