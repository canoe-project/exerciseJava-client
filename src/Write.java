/*
입력 쓰레드
*/

import java.io.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;

public class Write extends Thread{

    DataOutputStream dataoutputStream = null;
    BufferedReader bufferedReader = null;
    Encoder encoder = Base64.getEncoder();

    Write(OutputStream outputStream, BufferedReader bufferedReader){
        this.dataoutputStream = new DataOutputStream(outputStream);
        this.bufferedReader = bufferedReader;
    }
    @Override
    public void run() {
        while(Client.read.getState() != State.TERMINATED && Client.write.getState() != State.TERMINATED){
            try {
                byte[] message = Protocol.reqFormat(bufferedReader.readLine()).getBytes();
                String code = new String(encoder.encode(message));
                System.out.println(code);
                dataoutputStream.writeUTF(code);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
