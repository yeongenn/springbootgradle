package pack.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoardBean {
    private int num;
    private String title, content, bwrite;
    private String searchName, searchValue;
    private int readcnt;    
    private String author;
    
//    // 필드명이랑 다르면 요청값이 바인딩 되지 X
//    private String writer;
    
    
}