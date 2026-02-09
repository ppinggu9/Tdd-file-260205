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

    public int getPageCount(){
        if(totalCount == 0){
            return 0;
        }
        return  (int)Math.ceil((double)totalCount / pageSize);
    }
}

