package com.ll.repository;

import com.ll.domain.WiseSaying;

import java.util.*;

public class MemorySayingRepository implements WiseSayingRepository {

    private final Map<Long, WiseSaying> quoteMap = new HashMap<>();
    private long uniqueNum = 1;

    @Override
    public long addWiseSaying(WiseSaying quote) {
        quote.setId(uniqueNum);
        quoteMap.put(uniqueNum, quote);

        return uniqueNum++;
    }

    @Override
    public Optional<WiseSaying> searchById(long id) {
        return Optional.ofNullable(quoteMap.get(id));
    }

    @Override
    public List<WiseSaying> findAll() {
        return new ArrayList<>(quoteMap.values());
    }

    @Override
    public boolean removeWiseSaying(long quoteId) {
        if(quoteMap.get(quoteId) != null){
            quoteMap.remove(quoteId);
            return true;
        }
        return false;
    }

    @Override
    public void updateWiseSaying(WiseSaying quote) {
        if(quoteMap.get(quote.getId()) == null)
            return;

        quoteMap.put(quote.getId(), quote);
    }
}
