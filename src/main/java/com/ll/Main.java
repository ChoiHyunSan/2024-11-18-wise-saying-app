package com.ll;

import com.ll.application.WiseSayingApp;

public class Main {
    public static void main(String[] args) {
        WiseSayingApp app = new WiseSayingApp();
        app.start();
    }
}

// 새로 생성하는 명언에 대한 번호가 이미 존재해도 1부터 시작함
// 존재하지 않는 명언에 대한 체크 (파일이 사라지지 않고있음)