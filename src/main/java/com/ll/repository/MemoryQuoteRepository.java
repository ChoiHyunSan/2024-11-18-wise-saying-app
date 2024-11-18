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
    public boolean removeQuote(long quoteId) {
        if(quoteMap.get(quoteId) != null){
            quoteMap.remove(quoteId);
            return true;
        }
        return false;
    }

    @Override
    public void updateQuote(Quote quote) {
        if(quoteMap.get(quote.getId()) == null)
            return;

        quoteMap.put(quote.getId(), quote);
    }
}
