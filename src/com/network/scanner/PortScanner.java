package com.network.scanner;

import java.net.Socket;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PortScanner {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Java Port Scanner =====");

        System.out.print("Enter host: ");
        String host = sc.nextLine();

        System.out.print("Start port: ");
        int startPort = sc.nextInt();

        System.out.print("End port: ");
        int endPort = sc.nextInt();

        System.out.println("\nScanning...\n");

        try {

            FileWriter writer = new FileWriter("scan_result.txt");

            for(int port = startPort; port <= endPort; port++) {

                try {

                    Socket socket = new Socket(host, port);

                    String service = getService(port);

                    System.out.println("Port " + port + " OPEN (" + service + ")");
                    writer.write("Port " + port + " OPEN (" + service + ")\n");

                    socket.close();

                }

                catch(Exception e) {

                    System.out.println("Port " + port + " CLOSED");
                    writer.write("Port " + port + " CLOSED\n");

                }
            }

            writer.close();

            System.out.println("\nScan finished.");
            System.out.println("Results saved to scan_result.txt");

        }

        catch(IOException e) {
            System.out.println("Error writing result file.");
        }

        sc.close();
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