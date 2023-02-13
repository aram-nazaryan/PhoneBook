package example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter file path: ");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        try {
            FileInputStream stream = new FileInputStream(filePath);
            Core.run(stream);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}