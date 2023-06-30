package com.thread.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class FilesProcessor {
    public static void main(String[] args) {
        new Thread(new Watcher()).start();
    }
}

class Watcher implements Runnable{
    @Override
    public void run() {
        File directory = new File("D:\\Practices\\resources");

        while(true) {
            if (directory.listFiles().length != 0) {
                Arrays.stream(directory.listFiles()).forEach(file -> {
                    new Thread(new FileProcessor(file)).start();
                });
            }
            System.out.println("number of file processed is ... " + directory.listFiles().length);
            sleep();
        }
    }

    private void sleep(){
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class FileProcessor implements Runnable{
    private final File file;
    private final static String OUTPUT_PATH = "D:\\Practices\\output";

    public FileProcessor(File file){
        this.file = file;
    }

    @Override
    public void run() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH + "/" + file.getName()));

            Files.lines(Path.of(file.getCanonicalPath())).forEach(
                    (line)-> {
                        try {
                            writer.write(line);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}