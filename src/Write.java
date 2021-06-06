/*
입력 쓰레드
*/

import java.io.*;
import java.util.Timer;

public class Write extends Thread{

    private DataOutputStream dataoutputStream = null;
    private BufferedReader bufferedReader = null;
    private ReliableTransPortSimulation rtp = null;

    Write(OutputStream outputStream, BufferedReader bufferedReader){
        this.dataoutputStream = new DataOutputStream(outputStream);
        this.bufferedReader = bufferedReader;
        this.rtp = ReliableTransPortSimulation.getInstance();
    }


    @Override
    public void run() {
        rtp.setOutputStream(dataoutputStream);
        while(Client.read.getState() != State.TERMINATED && Client.write.getState() != State.TERMINATED){
            try {
                Request reqMessage = new Request(bufferedReader.readLine());
                rtp.setTimer(reqMessage);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
