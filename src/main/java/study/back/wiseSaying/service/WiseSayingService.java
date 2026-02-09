package study.back.wiseSaying.service;

import study.back.global.AppContext;
import study.back.wiseSaying.dto.PageDto;
import study.back.wiseSaying.entity.WiseSaying;
import study.back.wiseSaying.repository.WiseSayingRepository;

public class WiseSayingService {

    private WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = AppContext.wiseSayingRepository;
    }

    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(0,content, author);
        wiseSayingRepository.save(wiseSaying);

        return wiseSaying;
    }

    public boolean delete(int id) {
        return wiseSayingRepository.delete(id);
    }

    public void modify(WiseSaying wiseSaying, String newSaying, String newAuthor) {

        wiseSaying.setSaying(newSaying);
        wiseSaying.setAuthor(newAuthor);

        wiseSayingRepository.save(wiseSaying);
    }

    public PageDto findListDesc(String kw, String  kwt, int page, int pageSize) {
        return  switch (kwt){
            case "content" -> wiseSayingRepository.findByContentKeywordOrderByDesc(kw,page, pageSize);
            case "author" -> wiseSayingRepository.findByAuthorKeywordOrderByDesc(kw, page, pageSize);
            default -> wiseSayingRepository.findListDesc(page,pageSize); // or throw으로 예외처리
        };
    }

    public WiseSaying findByIdOrNull(int id) { // findByIdOrNull은 이름으로 Null이 나올 수 있다는것
        // 자바에는 Optional이 있으니까 쓰자
        return wiseSayingRepository.findByIdOrNull(id);
    }
}
