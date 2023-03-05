package ru.smartjava.client;

import ru.smartjava.classes.ReadFromSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class client {
    public static void main(String[] args) {
        String host = "netology.homework";
        ReadFromSocket readFromSocket = new ReadFromSocket();

        try {
            InetAddress inetAddress = InetAddress.getByName(host);
            System.out.println();
            System.out.println(host + ", ip address: " + inetAddress.getHostAddress());
            System.out.println();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        int port = 8090;
        try(Socket clientSocket = new Socket(host,port);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        )
        {
            while(true) {
                String resp = in.readLine();
                if(resp == null) {
                    return;
                }
                if(resp.contains("Вопрос:")){
                    System.out.println(resp.split(":")[1]);
                    System.out.print("> ");
                    String inputString = console.readLine();
                    out.println(inputString);
                } else {
                    System.out.println(resp);
                }


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
