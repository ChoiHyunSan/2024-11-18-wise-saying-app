package com.ll.service;

import com.ll.domain.WiseSaying;
import com.ll.repository.WiseSayingRepository;

import java.util.List;
import java.util.Optional;

public class WiseSayingService {

    public WiseSayingService(WiseSayingRepository repository) {
        this.repository = repository;
    }

    private final WiseSayingRepository repository;

    public List<WiseSaying> findAllWiseSaying() {
        return repository.findAll();
    }

    public long addWiseSaying(WiseSaying ws) {
        return repository.addWiseSaying(ws);
    }

    public boolean removeWiseSaying(long deleteId) {
        return repository.removeWiseSaying(deleteId);
    }

    public Optional<WiseSaying> searchById(long searchId) {
        return repository.searchById(searchId);
    }

    public void updateWiseSaying(WiseSaying updateWs) {
        repository.updateWiseSaying(updateWs);
    }
}
