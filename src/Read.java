/*
출력 쓰레드
*/

import java.io.*;
import java.util.Base64;
import java.util.Base64.Decoder;

public class Read extends Thread{
    DataInputStream dataInputStream = null;
    Protocol protocol = null;
    Decoder decoder = Base64.getDecoder();

    Read(InputStream inputStream){
        this.dataInputStream = new DataInputStream(inputStream);
    }

    @Override
    public void run() {

        while(Client.read.getState() != State.TERMINATED && Client.write.getState() != State.TERMINATED){
            try {
                System.out.print("Enter your Message : ");
                byte[] code = decoder.decode(dataInputStream.readUTF());
                String message = new String(code);
                System.out.println(message);
                protocol = Protocol.resParser(message);
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
