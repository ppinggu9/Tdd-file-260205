package study.back;

import study.back.global.AppConfig;
import study.back.global.AppContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        AppConfig.setDevMode();
        AppContext.init();
        new App().run();
    }
}
