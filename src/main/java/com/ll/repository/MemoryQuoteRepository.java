package com.ll.repository;

import com.ll.domain.Quote;

import java.util.*;

public class MemoryQuoteRepository implements QuoteRepository{

    private final Map<Long, Quote> quoteMap = new HashMap<>();
    private long uniqueNum = 1;

    @Override
    public long addQuote(Quote quote) {
        quote.setId(uniqueNum);
        quoteMap.put(uniqueNum, quote);

        return uniqueNum++;
    }

    @Override
    public Optional<Quote> searchById(long id) {
        return Optional.ofNullable(quoteMap.get(id));
    }

    @Override
    public List<Quote> findAll() {
        return new ArrayList<>(quoteMap.values());
    }

    @Override
    public Optional<Quote> searchByAuthor(String author) {
        return quoteMap.values().stream()
                .filter(quote -> quote.getAuthor().equals(author))
                .findFirst();
    }

    @Override
    public Optional<Quote> searchByWiseSaying(String piece) {
        return quoteMap.values().stream()
                .filter(quote -> quote.getWiseSaying().contains(piece))
                .findFirst();
    }

    @Override
    public void removeQuote(Quote quote) {

    }
}
