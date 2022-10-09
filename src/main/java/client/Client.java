package client;

import java.io.*;
import java.io.RandomAccessFile;
import rl1.CustomQueueContainer;

public class Client {
    public static void main(String[] args) throws Exception {
        CustomQueueContainer cq = new CustomQueueContainer("text.txt");
        String msg;

        while (true) {
            System.out.print("Enter message: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            msg = br.readLine();
            cq.appender(msg);
            System.out.println("Message sent");
        }

    }
}
