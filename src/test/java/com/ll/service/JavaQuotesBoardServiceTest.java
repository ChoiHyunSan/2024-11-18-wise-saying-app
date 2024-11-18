package com.ll.service;

import com.ll.domain.Quote;
import com.ll.repository.JsonQuoteRepository;
import com.ll.repository.QuoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class JavaQuotesBoardServiceTest {

    QuoteRepository quoteRepository = new JsonQuoteRepository();
    JavaQuotesBoardService service;

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

        service = new JavaQuotesBoardService(quoteRepository);
    }

    @Test
    void 명언리스트_출력하기(){
        service.printQuoteList();
    }

    @Test
    void 명언삭제하고_확인하기(){
        // command와 parameter의 구조는 우선 알맞게 들어온다고 판단.

        // happy case
        service.deleteQuote("삭제?id=1");
        Optional<Quote> quote = quoteRepository.searchById(1);
        Assertions.assertTrue(quote.isEmpty());

        // unhappy case
        service.deleteQuote("삭제?id=3");
    }
}