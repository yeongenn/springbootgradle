package pack.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class Board {
    private int num, readcnt;
    private String author, title, content, bwrite;
}
