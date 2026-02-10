package study.back.wiseSaying.repository;

import study.back.wiseSaying.dto.PageDto;
import study.back.wiseSaying.entity.WiseSaying;

import java.util.Optional;

public interface WiseSayingRepository {

    WiseSaying save(WiseSaying wiseSaying);
    Optional<WiseSaying> findById(int id);
    PageDto findAll(int page, int pageSize);
    boolean delete(WiseSaying wiseSaying1);
    PageDto findByContentContainingDesc(String kw, int page, int pageSize);
    PageDto findByAuthorContainingDesc(String kw, int page, int pageSize);


}
