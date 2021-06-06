import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ACK{
    int numAck;
    ReliableTransPortSimulation rtp = ReliableTransPortSimulation.getInstance();

    ACK(String message){
        String responseReg = "^(?<type>\\w+)\\/{3}Num_ACK:(?<numAck>\\d+)\\/{3}END_MSG$";

        Pattern pattern = Pattern.compile(responseReg);
        Matcher matcher = pattern.matcher(message);

        if(matcher.matches()){
            this.numAck = Integer.parseInt(matcher.group("numAck"));
        }
    }

    public int getNumAck(){
        return numAck;
    }
}