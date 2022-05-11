package com.matt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Database db = new Database();
        db.insert(0, 180, 100.00, 100.00, 123456);
        db.insert(1, 180, 100.00, 100.00, 123456);
        db.insert(2, 180, 100.00, 100.00, 123456);
        db.insert(3, 180, 100.00, 100.00, 123456);

        try(Socket socket = new Socket("localhost", 6000)) {
            socket.setSoTimeout(5000);
            BufferedReader echos = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do {
                System.out.println("Type start to start the simulation");
                echoString = scanner.nextLine();
                stringToEcho.println(echoString);

                if(!echoString.equals("start")) {
                    response = echos.readLine();
                    System.out.println(response);
                }

            } while(!echoString.equals("start"));

        } catch(
                SocketTimeoutException e) {
            System.out.println("The socket timed out");
        }
        catch(
                IOException e ) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }


}

