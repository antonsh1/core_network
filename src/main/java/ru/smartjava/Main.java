package ru.smartjava;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Сервер запущен");
        int port = 8090;
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
                     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                ) {
                    System.out.printf("Новое соединение установлено порт %d\n", clientSocket.getPort());
                    out.write("Привет от Сервера!\n");
                    out.flush();
                    String name = in.readLine();
                    System.out.println(name);
                    out.write("23456");
                    out.flush();
//                    out.write(String.format("Привет %s, ваш порт %d\n", name, clientSocket.getPort()));
//                    out.printf("Привет %s, ваш порт %d\n", name, clientSocket.getPort());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}