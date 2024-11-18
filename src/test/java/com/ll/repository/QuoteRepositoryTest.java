package com.ll.repository;

import com.ll.domain.Quote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuoteRepositoryTest {
    QuoteRepository quoteRepository = new MemoryQuoteRepository();

    @Test
    void 명언_추가하고_조회하기(){
        Quote quote1 = new Quote();
        quote1.setAuthor("작가1");
        quote1.setWiseSaying("명언1");

        Quote quote2 = new Quote();
        quote2.setAuthor("작가2");
        quote2.setWiseSaying("명언2");

        long id1 = quoteRepository.addQuote(quote1);
        long id2 = quoteRepository.addQuote(quote2);

        // 간단한 테스트니까 일단 get으로 바로 꺼내기
        Quote findQuote1 = quoteRepository.searchById(id1).get();
        Quote findQuote2 = quoteRepository.searchById(id2).get();

        Assertions.assertEquals(quote1, findQuote1);
        Assertions.assertEquals(quote2, findQuote2);
        Assertions.assertEquals("작가1", findQuote1.getAuthor());
        Assertions.assertEquals("작가2", findQuote2.getAuthor());
        Assertions.assertEquals("명언1", findQuote1.getWiseSaying());
        Assertions.assertEquals("명언2", findQuote2.getWiseSaying());
    }
}