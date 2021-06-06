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

                String message = dataInputStream.readUTF();
                System.out.println(message);
                switch (Protocol.resTypeOf(message)){
                    case "ACK":
                        currentAck = new ACK(message);
                        rtd.requestTimerCancel(currentAck.getNumAck());
                        rtd.setResponse(currentAck);
                        break;
                case "response":
                    Response res = new Response(message);

                    if(currentAck ==null){
                        rtd.resendReq(0);

                    }
                    else if (currentAck.getNumAck() == res.getNumReq()){
                        rtd.responseTimerCancel(res.getNumReq());
                    }
                    else{
                        rtd.resendReq(currentAck.getNumAck());
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
