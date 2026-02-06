import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtilTest {

    @Test
    @DisplayName("TestUtil.genSacnner()")
    void t1() {
        Scanner scanner = TestUtil.genScanner("""
                등록
                현재를 사랑하라.
                작자미상
                """);
        String cmd = scanner.nextLine();
        String content = scanner.nextLine();
        String author = scanner.nextLine();

        assertThat(cmd).isEqualTo("등록");
        assertThat(content).isEqualTo("현재를 사랑하라.");
        assertThat(author).isEqualTo("작자미상");
    }

    @Test
    @DisplayName("TestUtil.setOutByArray()")
    void t2() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutByArray();

        System.out.println("안녕하세요");

        String rst = byteArrayOutputStream.toString();
        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);

        System.out.println("출력 결과 : " + rst);

        assertThat(rst).contains("안녕하세요");// 문자열을 비교할떄 contiains을 많이 쓴다.
    }

//    @Test
//    @DisplayName("등록")
//    void t1() {
//        final String out = TestUtil.run("""
//                등록
//                현재를 사랑하라.
//                작자미상
//                """);
//
//        assertThat(out)
//                .contains("명언 : ")
//                .contains("명언 : ")
//    }
}
