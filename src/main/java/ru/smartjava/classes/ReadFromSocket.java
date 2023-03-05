package ru.smartjava.classes;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFromSocket {

    public void read(BufferedReader in) throws IOException {
        String resp = "";
        do {
            resp = in.readLine();
            System.out.println(resp);
        } while (resp == null);
//        return resp;
    }
}
