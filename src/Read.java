import java.io.*;

public class Read extends Thread{
    DataInputStream dataInputStream = null;

    Read(InputStream inputStream){
        this.dataInputStream = new DataInputStream(inputStream);
    }
    @Override
    public void run() {
        try {
            System.out.println(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                System.out.println("echo: "+dataInputStream.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
