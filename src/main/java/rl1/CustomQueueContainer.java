package rl1;

import java.io.*;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class CustomQueueContainer {

    private RandomAccessFile rFile;
    private long readPtr;


    public Boolean appender(String msg){

        try {
            this.rFile.seek(this.rFile.length());
            this.rFile.writeBytes(msg);
            this.rFile.writeBytes("\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public String reader(){
        String res = null;

        try {
            rFile.seek(readPtr);//todo : comment this
            res = rFile.readLine();
            this.readPtr = rFile.getFilePointer();
//            System.out.println("" + res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public CustomQueueContainer(String path) {
        try {
            this.rFile = new RandomAccessFile(path, "rw");
            this.readPtr = 0;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception
    {
        System.out.print("enter character or string");
        CustomQueueContainer cq = new CustomQueueContainer("cqFile.txt");
        cq.appender("First msg");
        cq.appender("Second msg");
        cq.appender("Third msg");


        while(true){
            String tmp = cq.reader();
            if (tmp!=null)
                System.out.println("output : " + tmp);
            else
                break;

        }

//        try (RandomAccessFile sc
//                     = new RandomAccessFile("text.txt", "rw")) {
//            //Scanner s = new Scanner(System.in);
//            String a = "hello";
//            //a = s.next();
//            sc.writeBytes(a);
//            sc.writeBytes(a);
//            sc.writeBytes(a);
//            sc.seek(0);
//            System.out.println("output");
//            System.out.println("" + sc.readLine());
//
//            // read chars
////            for (int i = 0; i < a.length(); i++) {
////                System.out.print("" + sc.readChar());
////            }
//            System.out.println(
//                    "Writing to Memory is complete");
//            System.out.println(
//                    "Reading from Memory is complete");
//        }
    }
}
