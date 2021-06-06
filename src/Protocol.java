/*
프로토콜 관리자
*/
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Protocol {
    public enum statusCode{
        Hi(100),
        CurrentTime(130),
        ConnectionTime(150),
        ClientList(200),
        Quit(250),
        Error(300),
        Message(500);

        private int code;
        statusCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    };
    Protocol(){

    }
    public statusCode reqCheck(String message){
        for (statusCode i:statusCode.values()){
            if (i.toString().equals(message)){
                return i;
            }
        }
        return statusCode.Error;
    }

    public static statusCode codeParser (int code){
        for (statusCode i:statusCode.values()){
            if (i.code == code){
                return i;
            }
        }
        return null;
    }

    static String resTypeOf(String res) {
        String intro = "(?<type>ACK|response).*";

        Pattern introPatten = Pattern.compile(intro);
        Matcher IntroMatcher = introPatten.matcher(res);

        if (IntroMatcher.matches()) {
            return IntroMatcher.group("type");
        } else {
            return null;
        }
    }
}
