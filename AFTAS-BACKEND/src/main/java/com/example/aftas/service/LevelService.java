package com.example.aftas.service;

import com.example.aftas.domain.Level;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LevelService {

    Level save(Level level);
    List<Level> findAll();
    Optional<Level> findById(Long id);
    Level update(Level levelUpdated, Long id);
    void delete(Long id);

}
