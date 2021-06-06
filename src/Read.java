/*
출력 쓰레드
*/

import java.io.*;

public class Read extends Thread{
    private DataInputStream dataInputStream = null;
    private Protocol protocol = null;
    private ReliableTransPortSimulation rtd = ReliableTransPortSimulation.getInstance();
    private ACK currentAck = null;
    Read(InputStream inputStream){
        this.dataInputStream = new DataInputStream(inputStream);
    }

    public void can(){}
    @Override
    public void run() {

        while(Client.read.getState() != State.TERMINATED && Client.write.getState() != State.TERMINATED){
            try {
                System.out.print("Enter your Message : ");
                String message = dataInputStream.readUTF();
                switch (Protocol.resTypeOf(message)){
                    case "ACK":
                        currentAck = new ACK(message);
                        rtd.requestTimerCancel(currentAck.getNumAck());
                        rtd.setResponse(currentAck);
                        break;
                case "response":
                    Response res = new Response(message);
                    if(currentAck.getNumAck() == res.getNumReq()){
                        rtd.responseTimerCancel(res.getNumReq());
                    }
                    System.out.println("response : "+res.getMessage());
                    if (res.getCode() == Protocol.statusCode.Quit){
                        break;
                    }
                    break;
                    default:
                }

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
