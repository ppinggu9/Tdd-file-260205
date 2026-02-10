package study.back.wiseSaying.repository;

import org.junit.jupiter.api.*;
import study.back.global.AppContext;
import study.back.wiseSaying.entity.WiseSaying;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingFileRepositoryTest {

    private WiseSayingFileRepository wiseSayingFileRepository;

    public WiseSayingFileRepositoryTest() {
        AppContext.init();
        wiseSayingFileRepository = AppContext.wiseSayingFileRepository;
    }
    @BeforeEach
    public void beforeClear () { // 라이브버리가 불러오는 거라서 public필요
        wiseSayingFileRepository.clear();
    }

    @AfterEach
    public void afterClear () {
        wiseSayingFileRepository.clear();
    }


    @Test
    @DisplayName("명언 저장")
    void t1() {
        WiseSaying wiseSaying = new WiseSaying(0, "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");

        wiseSayingFileRepository.save(wiseSaying);

        WiseSaying foundedWiseSaying = wiseSayingFileRepository.findByIdOrNull(1);

        assertThat(foundedWiseSaying).isEqualTo(wiseSaying);

    }

    @Test
    @DisplayName("2개 이상의 명언 저장")
    void t2() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        WiseSaying wiseSaying2 = new WiseSaying("너 자신을 알라", "소크라테스");

        wiseSayingFileRepository.save(wiseSaying1);
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying foundedWiseSaying1 = wiseSayingFileRepository.findByIdOrNull(1);
        assertThat(foundedWiseSaying1).isEqualTo(wiseSaying1);

        WiseSaying foundedWiseSaying2 = wiseSayingFileRepository.findByIdOrNull(2);
        assertThat(foundedWiseSaying2).isEqualTo(wiseSaying2);


    }

}