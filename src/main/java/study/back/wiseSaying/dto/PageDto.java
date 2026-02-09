package study.back.wiseSaying.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.back.wiseSaying.entity.WiseSaying;

import java.util.List;
@AllArgsConstructor
@Getter
public class PageDto {
    private  int page;
    private  int pageSize;
    private  int totalCount;
    private List<WiseSaying> content;

//    public PageDto(int page, int pageSize, int totalCount, List<WiseSaying> content){
//        this.page = page;
//        this.pageSize = pageSize;
//        this.totalCount = totalCount;
//        this.content = content;
//    }
}

