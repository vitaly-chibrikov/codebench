package stack.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author v.chibrikov
 */
public class Server {
    public static void main(String[] args) {
        System.out.println(" inside main ");
        try {
            System.out.println("Starting Server");
            ServerSocket serverSocket = new ServerSocket(55555);
            Socket clientSocket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null)
                System.out.println("Server Message:" + inputLine);

        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
    }
}
