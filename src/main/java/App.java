import java.util.Scanner;

public class App {

    private Scanner sc;
    private WiseSayingController wiseSayingController;
    private SystemController systemController;

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {

        wiseSayingController = new WiseSayingController(sc); // 테스트에서 주입되는 sc을 사용하기 위해서
        systemController = new SystemController();

        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.println("명령) ");
            String cmd = sc.nextLine();

            switch (cmd) {
                case "등록" -> wiseSayingController.actionAdd();
                case "목록" -> wiseSayingController.actionList();
                case "종료" -> {
                    systemController.actionExit();
                    return;
                }
            }
        }
    }
}
