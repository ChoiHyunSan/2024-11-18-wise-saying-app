package com.ll.application;

import com.ll.controller.WiseSayingController;

import java.util.Scanner;

public class WiseSayingApp {

    private static final String CMD_EXIT    = "종료";
    private static final String CMD_INSERT  = "등록";
    private static final String CMD_LIST    = "목록";
    private static final String CMD_DELETE  = "삭제";
    private static final String CMD_UPDATE  = "수정";

    private final Scanner sc = new Scanner(System.in);
    private final WiseSayingController controller = new WiseSayingController();

    public void start() {
        loop();
    }

    private void loop() {
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
        if(command.equals(CMD_EXIT)) {
            return false;
        }
        else if(command.equals(CMD_INSERT)){
            controller.InsertQuote();
        }else if(command.equals(CMD_LIST)){
            controller.printQuoteList();
        }
        else if(command.startsWith(CMD_DELETE)){
            controller.deleteQuote(command);
        }else if(command.startsWith(CMD_UPDATE)){
            controller.updateQuote(command);
        }

        return true;
    }
}
