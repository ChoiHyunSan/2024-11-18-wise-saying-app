package com.ll.repository;

import com.ll.domain.Quote;

import java.util.Optional;

public interface QuoteRepository {
    long addQuote(Quote quote);
    Optional<Quote> searchByAuthor(String author);
    Optional<Quote> searchByWiseSaying(String piece);
    Optional<Quote> searchById(long id);
    void removeQuote(Quote quote);
}
