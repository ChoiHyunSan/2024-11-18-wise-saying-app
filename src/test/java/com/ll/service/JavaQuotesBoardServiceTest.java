package com.ll.service;

import com.ll.domain.Quote;
import com.ll.repository.MemoryQuoteRepository;
import com.ll.repository.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JavaQuotesBoardServiceTest {

    QuoteRepository quoteRepository = new MemoryQuoteRepository();
    JavaQuotesBoardService service;

    @BeforeEach
    void before(){
        Quote quote3 = new Quote();
        quote3.setAuthor("작가3");
        quote3.setWiseSaying("명언3");

        Quote quote4 = new Quote();
        quote4.setAuthor("작가4");
        quote4.setWiseSaying("명언4");

        long id1 = quoteRepository.addQuote(quote3);
        long id2 = quoteRepository.addQuote(quote4);

        service = new JavaQuotesBoardService(quoteRepository);
    }

    @Test
    void 명언리스트_출력하기(){
        service.printQuoteList();
    }
}