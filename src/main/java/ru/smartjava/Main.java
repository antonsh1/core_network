package ru.smartjava;

import ru.smartjava.classes.ReadFromSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("Сервер запущен");
        String serverName = "netology.homework";
        ReadFromSocket readFromSocket = new ReadFromSocket();
        int port = 8090;
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
                ) {
                    System.out.printf("Новое соединение установлено порт %d%n", clientSocket.getPort());
                    out.printf("Вы подключились к серверу %s%n", serverName);
                    System.out.println("Вопрос 1");
                    out.println("Вопрос: Write your name?");
                    String name = in.readLine();
                    System.out.println("Name = " + name);
                    System.out.println("Сообщаем порт");
                    out.println("---------------------------------------");
                    out.printf("Привет %s, ваш порт %d%n", name, clientSocket.getPort());
                    out.println("---------------------------------------");
                    while(true) {
                        System.out.println("Вопрос 2");
                        out.println("Вопрос: Are you child? (yes/no)");
                        String resp = in.readLine();
                        System.out.println(resp);
                        if(Objects.equals(resp, "yes")) {
                            out.printf("Welcome to the kids area, %s! Let's play!%n" , name);
                            break;
                        } else if(Objects.equals(resp, "no")) {
                            out.printf("Welcome to the adult zone, %s! Have a good rest, or a good working day!%n", name);
                            break;
                        }
                    }
                    out.println("Завешаем сеанс!");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}