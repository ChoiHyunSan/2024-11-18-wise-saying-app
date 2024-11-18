package com.ll.repository;

import com.ll.domain.Quote;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryQuoteRepository implements QuoteRepository{

    private final Map<Long, Quote> quoteMap = new HashMap<>();
    private long uniqueNum = 1;

    @Override
    public long addQuote(Quote quote) {
        quoteMap.put(uniqueNum, quote);
        return uniqueNum++;
    }

    @Override
    public Optional<Quote> searchById(long id) {
        return Optional.ofNullable(quoteMap.get(id));
    }

    @Override
    public Optional<Quote> searchByAuthor(String author) {
        for(Quote quote : quoteMap.values()){
            if(quote.getAuthor().equals(author)){
                return Optional.of(quote);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Quote> searchByWiseSaying(String piece) {
        return Optional.empty();
    }

    @Override
    public void removeQuote(Quote quote) {

    }
}
