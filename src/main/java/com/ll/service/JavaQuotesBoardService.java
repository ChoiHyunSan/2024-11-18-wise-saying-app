package com.ll.service;

import com.ll.domain.Quote;
import com.ll.repository.MemoryQuoteRepository;
import com.ll.repository.QuoteRepository;

import java.util.Scanner;

public class JavaQuotesBoardService {

    private static final String CMD_EXIT = "종료";
    private static final String CMD_INSERT = "등록";

    private QuoteRepository repository = new MemoryQuoteRepository();
    private Scanner sc = new Scanner(System.in);

    public void start() {

        while(true){
            System.out.print("명령) ");
            String command = sc.nextLine();

            if(!HandleCommand(command)){
                System.out.println("서비스 종료");
                break;
            }
        }
    }

    private boolean HandleCommand(String command){

        if(command.equals(CMD_EXIT)){
            return false;
        }else if(command.equals(CMD_INSERT)){
            InsertQuote();
        }

        return true;
    }

    private void InsertQuote(){
        Quote quote = new Quote();

        System.out.print("명언 : ");
        quote.setWiseSaying(sc.nextLine());
        System.out.print("작가 : ");
        quote.setAuthor(sc.nextLine());

        repository.addQuote(quote);
    }
}
