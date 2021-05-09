/*
입력 쓰레드
*/

import java.io.*;

public class Write extends Thread{

    DataOutputStream dataoutputStream = null;
    BufferedReader bufferedReader = null;

    Write(OutputStream outputStream, BufferedReader bufferedReader){
        this.dataoutputStream = new DataOutputStream(outputStream);
        this.bufferedReader = bufferedReader;
    }
    @Override
    public void run() {
        while(Client.read.getState() != State.TERMINATED && Client.write.getState() != State.TERMINATED){
            try {
                dataoutputStream.writeUTF(Protocol.reqFormat(bufferedReader.readLine()));
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
