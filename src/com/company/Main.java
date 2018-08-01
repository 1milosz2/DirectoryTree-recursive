package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please provide directory");

        String givenPath = scanner.nextLine();
        int givenPathDepth = getIncision(givenPath);

        drawPathTree(new File(givenPath), givenPathDepth);
    }

    private static void drawPathTree(File file, int initialPathDepth) {
        drawFile(file, initialPathDepth);
        if (file.isDirectory()) {
            File[] listOfFiles = file.listFiles();
            for (File f : listOfFiles) {
                drawPathTree(f, initialPathDepth);
            }
        }
    }

    private static void drawFile(File file, int initialPathDepth) {
        StringBuilder sb = new StringBuilder();
        sb.append(drawIncision(getIncision(file) - initialPathDepth));
        sb.append("--> ");
        sb.append(file.getName());
        System.out.println(sb.toString());
    }

    private static String drawIncision(int incision) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, incision).forEach(i -> sb.append(" | "));
        return sb.toString();
    }

    private static int getIncision (String path){
        return Paths.get(path).getNameCount();
    }

    private static int getIncision (File file){
        return Paths.get(file.getAbsolutePath()).getNameCount();
    }
}
