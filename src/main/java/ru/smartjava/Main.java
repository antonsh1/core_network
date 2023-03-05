package ru.smartjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("Сервер запущен");

        String serverName = "netology.homework";
        int port = 8090;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true)
                ) {

                    System.out.printf("Новое соединение установлено порт %d%n", clientSocket.getPort());
                    //Уведомляем куда подключился клиент
                    out.printf("Вы подключились к серверу %s%n", serverName);
                    //Приставка "Вопрос:" в тексте сообщения говорит удаленной стороне, что требуется ответ
                    out.println("Вопрос:Write your name");
                    String name = in.readLine();
                    out.println("---------------------------------------");
                    out.printf("Привет %s, ваш порт %d%n", name, clientSocket.getPort());
                    out.println("---------------------------------------");
                    //Добиваемся от клиента четкого ответа yes/no
                    while(true) {
                        out.println("Вопрос:Are you child? (yes/no)");
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