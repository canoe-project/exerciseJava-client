import java.net.*;
import java.io.*;
import java.util.Scanner;

class Client {
    Socket mySocket = null;
    DataInputStream dataInputStream= null;

    public static void main(String[] args) {
        Client client = new Client();
        Manager manager = null;
        try {

            client.mySocket = new Socket("localhost", 55555);
            new Manager(client.mySocket);

        }catch(Exception e) {

        }
    }
}
