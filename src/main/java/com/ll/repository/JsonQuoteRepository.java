package com.ll.repository;

import com.ll.domain.Quote;

import java.util.List;
import java.util.Optional;

public class JsonQuoteRepository implements QuoteRepository{
    @Override
    public long addQuote(Quote quote) {
        return 0;
    }

    @Override
    public void updateQuote(Quote quote) {

    }

    @Override
    public Optional<Quote> searchByAuthor(String author) {
        return Optional.empty();
    }

    @Override
    public Optional<Quote> searchByWiseSaying(String piece) {
        return Optional.empty();
    }

    @Override
    public Optional<Quote> searchById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Quote> findAll() {
        return List.of();
    }

    @Override
    public boolean removeQuote(long quoteId) {
        return false;
    }
}
