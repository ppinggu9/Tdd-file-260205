package study.back.global;

import study.back.system.controller.SystemController;
import study.back.wiseSaying.controller.WiseSayingController;
import study.back.wiseSaying.repository.WiseSayingFileRepository;
import study.back.wiseSaying.repository.WiseSayingRepository;
import study.back.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

public class AppContext {

    public static Scanner sc;
    public static SystemController systemController;
    public static WiseSayingController wiseSayingController;
    public static WiseSayingService wiseSayingService;
    public static WiseSayingRepository wiseSayingRepository;
    public static WiseSayingFileRepository wiseSayingFileRepository;

    public static void init(Scanner _sc) {
        AppContext.sc = _sc;
        AppContext.wiseSayingFileRepository = new WiseSayingFileRepository();
        AppContext.wiseSayingService = new WiseSayingService();
        AppContext.wiseSayingRepository = new WiseSayingRepository();
        AppContext.wiseSayingController = new WiseSayingController(sc);
        AppContext.systemController = new SystemController();
    }
    public static void init() {
        init(new Scanner(System.in));
    }
}