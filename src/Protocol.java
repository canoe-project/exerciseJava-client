/*
프로토콜 관리자
*/
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Protocol {

    private String type = null;
    private String message = null;
    private statusCode code = null;

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
    Protocol(String type,String statusCode, String message){
        this.type = type;
        this.message = message;
        this.code = codeParser(Integer.parseInt(statusCode));
    }

    public statusCode reqCheck(){
        for (statusCode i:statusCode.values()){
            if (i.toString().equals(this.message)){
                return i;
            }
        }
        return statusCode.Error;
    }
    public statusCode codeParser (int code){
        for (statusCode i:statusCode.values()){
            if (i.code == code){
                return i;
            }
        }
        return null;
    }

    public statusCode getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    static String reqFormat(String reqMessage){
        return "request"+"///"+reqMessage+"///"+"CID:"+Client.cid+"///"+"Num_Req:"+Client.num_req+"///"+"END_MSG";

    }
    static Protocol resParser(String res){
        String reg = "(?<type>\\w+)\\/{3}(?<statusCode>\\d+)\\/{3}(?<message>.+(\\s+.+)*)\\/{3}";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(res);

        if (m.matches())
        {
            return new Protocol(m.group("type"), m.group("statusCode"),m.group("message"));
        }
        else{
            return null;
        }
    }
}
