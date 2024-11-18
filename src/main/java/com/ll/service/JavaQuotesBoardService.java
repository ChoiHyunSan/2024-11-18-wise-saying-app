package com.ll.service;

import com.ll.domain.Quote;
import com.ll.repository.MemoryQuoteRepository;
import com.ll.repository.QuoteRepository;

import java.util.Optional;
import java.util.Scanner;

public class JavaQuotesBoardService {

    private static final String CMD_EXIT    = "종료";
    private static final String CMD_INSERT  = "등록";
    private static final String CMD_LIST    = "목록";
    private static final String CMD_DELETE  = "삭제";
    private static final String CMD_UPDATE  = "수정";

    private QuoteRepository repository;
    private Scanner sc = new Scanner(System.in);

    public JavaQuotesBoardService() {
        repository = new MemoryQuoteRepository();
    }

    public JavaQuotesBoardService(QuoteRepository repository) {
        this.repository = repository;
    }

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
        }else if(command.equals(CMD_LIST)){
            printQuoteList();
        }else if(command.startsWith(CMD_DELETE)){
            deleteQuote(command);
        }else if(command.startsWith(CMD_UPDATE)){
            UpdateQuote(command);
        }

        return true;
    }

    public void printQuoteList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------");
        repository.findAll().forEach(quote ->
                        System.out.println(quote.getId() + " / " + quote.getAuthor() + " / " + quote.getContent()));
    }

    private void InsertQuote(){
        Quote quote = new Quote();

        System.out.print("명언 : ");
        quote.setContent(sc.nextLine());
        System.out.print("작가 : ");
        quote.setAuthor(sc.nextLine());

        long quoteId = repository.addQuote(quote);
        System.out.println(quoteId + "번 명언이 등록되었습니다.");
    }

    public void deleteQuote(String command){

        // TODO : command -> Param Map 으로 변환하는 메서드 추가하기
        int boundaryIndex = command.indexOf("?");
        String param = command.substring(boundaryIndex);
        long deleteId = Long.parseLong(param.split("=")[1]);

        if(repository.removeQuote(deleteId)){
            System.out.println(deleteId + "번 명언이 삭제되었습니다.");
        }else{
            System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
        }
    }

    public void UpdateQuote(String command){

        // TODO : command -> Param Map 으로 변환하는 메서드 추가하기
        int boundaryIndex = command.indexOf("?");
        String param = command.substring(boundaryIndex);
        long updateId = Long.parseLong(param.split("=")[1]);

        Optional<Quote> quoteOptional = repository.searchById(updateId);
        if(quoteOptional.isEmpty()){
            System.out.println(updateId + "번 명언은 존재하지 않습니다.");
        }

        Quote updateQuote = quoteOptional.get();
        System.out.println("명언(기존) : " + updateQuote.getContent());
        System.out.print("명언 : ");
        updateQuote.setContent(sc.nextLine());

        System.out.println("작가(기존) : " + updateQuote.getContent());
        System.out.print("작가 : ");
        updateQuote.setAuthor(sc.nextLine());

        repository.updateQuote(updateQuote);
    }
}
