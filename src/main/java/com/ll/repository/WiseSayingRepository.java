package com.ll.repository;

import com.ll.domain.WiseSaying;

import java.util.List;
import java.util.Optional;

public interface WiseSayingRepository {
    long addWiseSaying(WiseSaying quote);

    Optional<WiseSaying> searchById(long id);
    List<WiseSaying> findAll();

    boolean removeWiseSaying(long quoteId);
    void updateWiseSaying(WiseSaying quote);
}
