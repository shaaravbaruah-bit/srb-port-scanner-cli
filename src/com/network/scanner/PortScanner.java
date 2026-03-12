package com.network.scanner;

import java.net.Socket;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PortScanner {

    static String host;
    static FileWriter writer;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Fast Java Port Scanner =====");

        System.out.print("Enter host: ");
        host = sc.nextLine();

        System.out.print("Start port: ");
        int startPort = sc.nextInt();

        System.out.print("End port: ");
        int endPort = sc.nextInt();

        System.out.println("\nScanning...\n");

        try {

            writer = new FileWriter("scan_result.txt");

            for(int port = startPort; port <= endPort; port++) {

                final int currentPort = port;

                new Thread(() -> scanPort(currentPort)).start();
            }

        }

        catch(IOException e) {
            System.out.println("Error creating result file.");
        }

        sc.close();
    }

    public static void scanPort(int port) {

        try {

            Socket socket = new Socket(host, port);

            String service = getService(port);

            String result = "Port " + port + " OPEN (" + service + ")";

            System.out.println(result);

            writer.write(result + "\n");

            socket.close();

        }

        catch(Exception e) {

            String result = "Port " + port + " CLOSED";

            System.out.println(result);

            try {
                writer.write(result + "\n");
            }
            catch(IOException ex) {}
        }
    }

    public static String getService(int port) {

        switch(port) {

            case 21: return "FTP";
            case 22: return "SSH";
            case 23: return "TELNET";
            case 25: return "SMTP";
            case 53: return "DNS";
            case 80: return "HTTP";
            case 110: return "POP3";
            case 143: return "IMAP";
            case 443: return "HTTPS";
            case 3306: return "MySQL";

            default: return "Unknown";
        }
    }
}