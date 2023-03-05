package ru.smartjava.client;

import java.io.*;
import java.net.Socket;

public class client {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8090;
        try(Socket clientSocket = new Socket(host,port);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        )
        {
            out.write("Вася!");
            out.flush();
            String resp;
            do{
                resp = in.readLine();
                System.out.println(resp);
            } while (resp != null);

//            String resp = in.readLine();
//            System.out.println(resp);
//            resp = in.readLine();
//            System.out.println(resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
