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
                System.out.print("Enter your Message : ");
                Request reqMessage = new Request(bufferedReader.readLine());
                System.out.println(Client.num_req);
                rtp.setTimer(reqMessage);
                Client.num_req++;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
