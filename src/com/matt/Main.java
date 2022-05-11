package com.matt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.*;

public class Main {

    public static void main(String[] args) {



        try(Socket socket = new Socket("localhost", 6001)) {
//            socket.setSoTimeout(5000);
            BufferedReader echos = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            String echoString;
            String response;

            do {
                echoString = scanner.nextLine();
                stringToEcho.println(echoString);
//                printMenu();


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

    private static void printMenu() {
        System.out.println("Available actions:\npress");
        System.out.println("0 - to quit\n" +
                "1 - to play next song\n" +
                "2 - to play previous song\n" +
                "3 - to replay the current song\n" +
                "4 - list songs in the playlist\n" +
                "5 - print available actions.\n" +
                "6 - delete current song from playlist");

    }


//    private static void printList(LinkedList<Song> playList) {
//        Iterator<Song> iterator = playList.iterator();
//        System.out.println("================================");
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//        System.out.println("================================");
//    }



}
