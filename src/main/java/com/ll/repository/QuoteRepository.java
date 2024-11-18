package com.ll.repository;

import com.ll.domain.Quote;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository {
    long addQuote(Quote quote);

    Optional<Quote> searchByAuthor(String author);
    Optional<Quote> searchByWiseSaying(String piece);
    Optional<Quote> searchById(long id);
    List<Quote> findAll();

    boolean removeQuote(long quoteId);
    void updateQuote(Quote quote);
}
