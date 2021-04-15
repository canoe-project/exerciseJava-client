import java.io.*;

public class Write extends Thread{

    DataOutputStream dataoutputStream = null;
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    Write(OutputStream outputStream){
        this.dataoutputStream= new DataOutputStream(outputStream);
    }
    @Override
    public void run() {
        while(true){
            try {
                dataoutputStream.writeUTF(bufferedReader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
