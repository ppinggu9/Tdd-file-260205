package study.back;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        lab2();

    }
    public  static  void lab2() {
        PrintStream ORIGINAL_OUT = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();// 비어있는 배열로
        // 모니터에서 배열로 출력방향을 바꿀거다. 여기서 통로는 stream이라고 한다.
        PrintStream printStream = new PrintStream(byteArrayOutputStream);

        System.setOut(printStream); // 설정

        System.out.println("hihi"); // 모니터 출력 X  byteArrayOutputStream에 쌓인다
        System.out.println("byebye ");
        System.out.println("ok");

        System.setOut(ORIGINAL_OUT); // 변경
        String result = byteArrayOutputStream.toString();
        System.out.println(result);

    }
    public  static  void lab1() {
        Scanner sc = new Scanner("""
                등록
                과거에 집착하리 마라.
                작가 : 미상 
                """); // """ 알아서 즐바꿈을 해준다

        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        String str3 = sc.nextLine();


        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);

    }
}
