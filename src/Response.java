import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Response {
    private String message = null;
    private Protocol.statusCode code = null;

    Response(String message){
        String responseReg = "(?<type>\\w+)\\/{3}(?<statusCode>\\d+)\\/{3}(?<message>.+(\\s+.+)*)\\/{3}";

        Pattern pattern = Pattern.compile(responseReg);
        Matcher matcher = pattern.matcher(message);

        if(matcher.matches()){
            this.message = matcher.group("message");
            this.code = Protocol.codeParser(Integer.parseInt(matcher.group("statusCode")));
        }
        else{
            this.code = Protocol.statusCode.Error;
        }
    }
    public Protocol.statusCode getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    public int getNumReq(){
        return 0;
    }
}
