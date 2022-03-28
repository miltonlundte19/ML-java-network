package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class servermodel {
    public static void main(String[] args) {
        int port = 1234;
        boolean run = true;
        ServerSocket serverSocket;
        Socket socket;
        System.out.println("Server started.");

        try {
            Object tryPort = tryPortt(port);
            serverSocket = null;
            if (tryPort instanceof ServerSocket) {
                 serverSocket = (ServerSocket) tryPort;
            }


            while (true) {
                System.out.println("Waiting for connections!");
                socket = serverSocket.accept();
                // GO
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ListenerThread in = new ListenerThread(new BufferedReader(new InputStreamReader(socket.getInputStream())));
                Thread listener = new Thread(in);
                listener.start();
                System.out.println("Client connected!");
                Scanner tgb = new Scanner(System.in);
                //Protocol
                while (run) {
                    String msg = tgb.nextLine();
                    out.println("SERVER: " + msg);
                }
                out.close();
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Server fail");
        }
    }
    private static Object tryPortt(int port) {
        try {
            return new ServerSocket(port);
        } catch (IOException e) {
            return false;
        }
    }

    private ServerSocket tryPort(int port) {

        return null;
    }
}
