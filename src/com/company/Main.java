package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    private static int GIVENPATHDEPTH;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please provide directory");

        String GIVENPATH = scanner.nextLine();
        GIVENPATHDEPTH = getIncision(GIVENPATH);

        DrawPathTree(new File(GIVENPATH));
    }

    private static void DrawPathTree(File file) {
        drawFile(file);
        if (file.isDirectory()) {
            File[] listOfFiles = file.listFiles();
            for (File f : listOfFiles) {
                DrawPathTree(f);
            }
        }
    }

    private static void drawFile(File file) {
        StringBuilder sb = new StringBuilder();
        sb.append(drawIncision(getIncision(file) - GIVENPATHDEPTH));
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
