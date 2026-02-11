package study.back.global;

import lombok.Getter;
import study.back.App;

public class AppConfig {

    @Getter
    private static String mode;

    public  static  void setTestMode(){
        AppConfig.mode = "test";
    }

    public static void setDevMode(){
        AppConfig.mode = "dev";
    }
}
