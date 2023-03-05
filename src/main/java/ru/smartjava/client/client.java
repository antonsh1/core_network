package ru.smartjava.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
    public static void main(String[] args) {
        String host = "netology.homework";
        String hostIp;
        //Получаем ip хоста
        try {
            InetAddress inetAddress = InetAddress.getByName(host);
            hostIp = inetAddress.getHostAddress();
            System.out.println();
            System.out.println(host + ", ip address: " + inetAddress.getHostAddress());
            System.out.println();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        int port = 8090;
        try (Socket clientSocket = new Socket(hostIp, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))
        ) {
            //бесконечный цикл чтения с удаленной стороны
            while (true) {
                String resp = in.readLine();
                //Выходим если null
                if (resp == null) {
                    return;
                }
                //Если присутствует вопрос, запрашиваем у пользователя ввод строки
                if (resp.contains("Вопрос:")) {
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
