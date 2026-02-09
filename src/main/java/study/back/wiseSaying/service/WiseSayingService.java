package study.back.wiseSaying.service;

import study.back.global.AppContext;
import study.back.wiseSaying.entity.WiseSaying;
import study.back.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

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

    public List<WiseSaying> findListDesc(String kw, String  kwt) {
        return  switch (kwt){
            case "content" -> wiseSayingRepository.findByContentKeywordOrderByDesc(kw);
            case "author" -> wiseSayingRepository.findByAuthorKeywordOrderByDesc(kw);
            default -> wiseSayingRepository.findListDesc(); // or throw으로 예외처리
        };
//        return wiseSayingRepository.findListDesc();
//        return wiseSayingRepository.findByKeywordOrderByDesc(kw);
    }

    public WiseSaying findByIdOrNull(int id) { // findByIdOrNull은 이름으로 Null이 나올 수 있다는것
        // 자바에는 Optional이 있으니까 쓰자
        return wiseSayingRepository.findByIdOrNull(id);
    }
}
