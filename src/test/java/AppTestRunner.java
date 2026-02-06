import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {

    public static String run(String input) {
        Scanner sc = TestUtil.genScanner(input + "\n종료");

        ByteArrayOutputStream outputStream = TestUtil.setOutByArray();// 배열로
        new App(sc).run(); // 실행

        return outputStream.toString(); // 결과받기

    }
}
