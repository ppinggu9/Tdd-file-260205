package study.back.wiseSaying.controller;

import study.back.global.AppContext;
import study.back.global.Rq;
import study.back.wiseSaying.dto.PageDto;
import study.back.wiseSaying.entity.WiseSaying;
import study.back.wiseSaying.service.WiseSayingService;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WiseSayingController {

    private Scanner sc;
    private WiseSayingService wiseSayingService;

    public WiseSayingController() {
        this.sc = AppContext.sc;
        this.wiseSayingService = AppContext.wiseSayingService;
    }

    public void actionAdd() {
        System.out.print("명언 : ");
        String saying = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying = wiseSayingService.write(saying, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    public void actionList(Rq rq) {

        String kwt = rq.getParam("keywordType", "");
        String kw = rq.getParam("keyword", "");
        int page = rq.getParamAsInt("page", 1);
        int pageSize = rq.getParamAsInt("pageSize", 5);

        if (kw.isBlank()) {
            System.out.println("--------------------------------");
            System.out.println("검색타입 : %s".formatted(kwt));
            System.out.println("검색어 : %s".formatted(kw));
            System.out.println("--------------------------------");
        }

        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        PageDto pageDto = wiseSayingService.findListDesc(kw, kwt, page, pageSize);

        pageDto.getContent()
                .stream()
                .forEach(wiseSaying -> System.out.printf("%d / %s / %s%n",
                        wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getSaying()));

        System.out.print("페이지 : ");
       String pageMenuStr = IntStream
                .rangeClosed(1, pageDto.getPageCount())
                .mapToObj((num) -> num == page ? "[" + num + "]" : String.valueOf(num))
                .collect(Collectors.joining(" / "));
//                .forEach((num) -> {
//                    if(num == page){
//                        System.out.print("[" + num + "]");
//                    }else {
//                        System.out.print(num);
//                    }
//
//                    System.out.print(" / ");
//                });
    }

    public void actionDelete(Rq rq) {

        int id = rq.getParamAsInt("id", -1);
        boolean deleted = wiseSayingService.delete(id);

        if (!deleted) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    public void actionModify(Rq rq) {

        int id = rq.getParamAsInt("id", -1);

        WiseSaying wiseSaying = wiseSayingService.findByIdOrNull(id);

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("명언(기존) : %s".formatted(wiseSaying.getSaying()));
        String newSaying = sc.nextLine();
        System.out.println("작가(기존) : %s".formatted(wiseSaying.getAuthor()));
        String newAuthor = sc.nextLine();

        wiseSayingService.modify(wiseSaying, newSaying, newAuthor);
    }
}