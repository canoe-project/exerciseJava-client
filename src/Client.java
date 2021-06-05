import com.sun.tools.javac.Main;

import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Client {
    static int num_req = 0;
    static String cid = "";

    public static Read read= null;
    public static Write write =null;

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));



        try {
            while(Client.cid.isBlank()){
                System.out.print("Enter your nickname here : ");
                Client.cid = bufferedReader.readLine();
            }
            socket = new Socket("localhost", 55555);
            read = new Read(socket.getInputStream());
            write =new Write(socket.getOutputStream(), bufferedReader);

            read.start();
            write.start();

        }catch(Exception e) {
            socket.close();
            e.printStackTrace();
        }
    }
}
