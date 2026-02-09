package app;

import study.back.App;
import study.back.global.AppContext;
import testutil.TestUtil;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {

    public static String run(String input) {
        Scanner sc = TestUtil.genScanner(input + "\n종료");

        ByteArrayOutputStream outputStream = TestUtil.setOutByArray();// 배열로

        AppContext.init(sc);
        new App().run(); // 실행
        try{
            TestUtil.clearSetOutToByteArray(outputStream); // 다시 모니터로 돌려라
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return outputStream.toString(); // 결과받기

    }
}
