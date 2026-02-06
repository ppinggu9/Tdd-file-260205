package study.back.wiseSaying.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WiseSaying {

    private int id;
    private String saying;
    private String author;

    public WiseSaying(int id, String saying, String author) {
        this.id = id;
        this.saying = saying;
        this.author = author;
    }
}