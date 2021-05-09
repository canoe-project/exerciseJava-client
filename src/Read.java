/*
출력 쓰레드
*/

import java.io.*;

public class Read extends Thread{
    DataInputStream dataInputStream = null;
    Protocol protocol = null;

    Read(InputStream inputStream){
        this.dataInputStream = new DataInputStream(inputStream);
    }

    @Override
    public void run() {

        while(Client.read.getState() != State.TERMINATED && Client.write.getState() != State.TERMINATED){
            try {
                System.out.print("Enter your Message : ");
                protocol = Protocol.resParser(dataInputStream.readUTF());
                if(protocol != null){
                    System.out.println("response : "+protocol.getMessage());
                    if (protocol.getCode() == Protocol.statusCode.Quit){
                        break;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
