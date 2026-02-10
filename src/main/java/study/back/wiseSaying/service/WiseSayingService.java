package study.back.wiseSaying.service;

import study.back.global.AppContext;
import study.back.wiseSaying.dto.PageDto;
import study.back.wiseSaying.entity.WiseSaying;
import study.back.wiseSaying.repository.WiseSayingFileRepository;
import study.back.wiseSaying.repository.WiseSayingRepository;

import java.util.Optional;

public class WiseSayingService {

    private WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = AppContext.wiseSayingFileRepository;
    }

    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(0, content, author);
        wiseSayingRepository.save(wiseSaying);

        return wiseSaying;
    }

    public boolean delete(int id) {
        Optional<WiseSaying> wiseSayingOp = wiseSayingRepository.findById(id);
        if (wiseSayingOp.isEmpty()) {
            return false;
        }
        return wiseSayingRepository.delete(wiseSayingOp.get());
    }

    public void modify(WiseSaying wiseSaying, String newSaying, String newAuthor) {

        wiseSaying.setSaying(newSaying);
        wiseSaying.setAuthor(newAuthor);

        wiseSayingRepository.save(wiseSaying);
    }

    public PageDto findListDesc(String kw, String kwt, int page, int pageSize) {
        return switch (kwt) {
            case "content" -> wiseSayingRepository.findByContentContainingDesc(kw, page, pageSize);
            case "author" -> wiseSayingRepository.findByAuthorContainingDesc(kw, page, pageSize);
            default -> wiseSayingRepository.findAll(page, pageSize); // or throw으로 예외처리
        };
    }

    public WiseSaying findByIdOrNull(int id) { // findByIdOrNull은 이름으로 Null이 나올 수 있다는것
        return wiseSayingRepository.findById(id).orElse(null);
    }
}
