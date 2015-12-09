package stack.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author v.chibrikov
 */
public class Client {
    public static void main(String[] args) {
            System.out.println(" Starting Client ");
            try(Socket socket = new Socket("localhost", 55555)) {
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                printWriter.println("Hello from client");
                printWriter.println("Conected, Yes!");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
