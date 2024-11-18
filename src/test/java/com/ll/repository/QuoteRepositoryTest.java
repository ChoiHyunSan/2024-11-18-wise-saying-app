package com.ll.repository;

import com.ll.domain.Quote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuoteRepositoryTest {
    QuoteRepository quoteRepository = new MemoryQuoteRepository();

    @BeforeEach
    void before(){
        Quote quote3 = new Quote();
        quote3.setAuthor("작가3");
        quote3.setContent("명언3");

        Quote quote4 = new Quote();
        quote4.setAuthor("작가4");
        quote4.setContent("명언4");

        long id1 = quoteRepository.addQuote(quote3);
        long id2 = quoteRepository.addQuote(quote4);
    }

    @Test
    void 명언_추가하고_조회하기_약식테스트(){
        Quote quote1 = new Quote();
        quote1.setAuthor("작가1");
        quote1.setContent("명언1");

        Quote quote2 = new Quote();
        quote2.setAuthor("작가2");
        quote2.setContent("명언2");

        long id1 = quoteRepository.addQuote(quote1);
        long id2 = quoteRepository.addQuote(quote2);

        // 간단한 테스트니까 일단 get으로 바로 꺼내기
        Quote findQuote1 = quoteRepository.searchById(id1).get();
        Quote findQuote2 = quoteRepository.searchById(id2).get();

        Assertions.assertEquals(quote1, findQuote1);
        Assertions.assertEquals(quote2, findQuote2);
        Assertions.assertEquals("작가1", findQuote1.getAuthor());
        Assertions.assertEquals("작가2", findQuote2.getAuthor());
        Assertions.assertEquals("명언1", findQuote1.getContent());
        Assertions.assertEquals("명언2", findQuote2.getContent());
    }

    @Test
    void 명언리스트_출력(){
        quoteRepository.findAll();

    }
}