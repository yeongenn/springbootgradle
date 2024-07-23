package pack.controller;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoardBean {
    private int num, readcnt;
    private String author, title, content;
    private Timestamp bwrite;
    private String searchName, searchValue;
}