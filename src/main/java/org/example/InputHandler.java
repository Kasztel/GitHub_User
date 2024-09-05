package org.example;

import java.util.Scanner;

public class InputHandler {
    public static String getUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username of programmist: ");
        if (scanner.hasNext()) {
            return scanner.next();
        }
        return null;
    }
}
