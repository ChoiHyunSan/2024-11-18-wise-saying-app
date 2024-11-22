package com.ll.controller;

import com.ll.domain.WiseSaying;
import com.ll.repository.JsonWiseSayingRepository;
import com.ll.service.WiseSayingService;

import java.util.Optional;
import java.util.Scanner;

public class WiseSayingController {

    private final Scanner sc = new Scanner(System.in);
    private final WiseSayingService service = new WiseSayingService(new JsonWiseSayingRepository());

    public void printQuoteList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------");
        service.findAllWiseSaying().forEach(ws ->
                System.out.println(ws.getId() + " / " + ws.getAuthor() + " / " + ws.getContent()));
    }

    public void InsertQuote(){
        WiseSaying ws = new WiseSaying();

        System.out.print("명언 : ");
        ws.setContent(sc.nextLine());
        System.out.print("작가 : ");
        ws.setAuthor(sc.nextLine());

        long wiseSayingId = service.addWiseSaying(ws);
        System.out.println(wiseSayingId + "번 명언이 등록되었습니다.");
    }

    public void deleteQuote(String command){

        // TODO : command -> Param Map 으로 변환하는 메서드 추가하기
        int boundaryIndex = command.indexOf("?");
        String param = command.substring(boundaryIndex);
        long deleteId = Long.parseLong(param.split("=")[1]);

        if(service.removeWiseSaying(deleteId)){
            System.out.println(deleteId + "번 명언이 삭제되었습니다.");
        }else{
            System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
        }
    }

    public void updateQuote(String command){

        // TODO : command -> Param Map 으로 변환하는 메서드 추가하기
        int boundaryIndex = command.indexOf("?");
        String param = command.substring(boundaryIndex);
        long updateId = Long.parseLong(param.split("=")[1]);

        Optional<WiseSaying> wsOptional = service.searchById(updateId);
        if(wsOptional.isEmpty()){
            System.out.println(updateId + "번 명언은 존재하지 않습니다.");
        }

        WiseSaying updateWs = wsOptional.get();
        System.out.println("명언(기존) : " + updateWs.getContent());
        System.out.print("명언 : ");
        updateWs.setContent(sc.nextLine());

        System.out.println("작가(기존) : " + updateWs.getAuthor());
        System.out.print("작가 : ");
        updateWs.setAuthor(sc.nextLine());

        service.updateWiseSaying(updateWs);
    }
}
