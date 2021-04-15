import java.io.IOException;
import java.net.Socket;

public class Manager {
    Socket socket = null;
    Read read = null;
    Write write = null;

    Manager(Socket socket) throws IOException {
        this.socket = socket;
        this.read = new Read(socket.getInputStream());
        this.write = new Write(socket.getOutputStream());

        read.start();
        write.start();
    }
}
