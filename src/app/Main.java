package app;

import exception.DuplicateRecordException;
import java.util.Scanner;
import model.Record;
import service.RecordManager;

public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final RecordManager MANAGER = new RecordManager();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("EXCEPTION SAFE RECORDS APPLICATION");

        while (running) {
            System.out.println("\n1 - Add record");
            System.out.println("0 - Exit");
            System.out.print("Choice: ");
            String choice = INPUT.nextLine().trim();

            if (choice.equals("1")) {
                addRecord();
            } else if (choice.equals("0")) {
                running = false;
                System.out.println("Application closed.");
            } else {
                System.out.println("Invalid choice. Please enter 1 or 0.");
            }
        }
    }

    private static void addRecord() {
        try {
            System.out.print("Record ID: ");
            String id = INPUT.nextLine();
            System.out.print("Name: ");
            String name = INPUT.nextLine();
            
            int quantity = readQuantity();

            MANAGER.add(new Record(id, name, quantity));
            System.out.println("Record added successfully.");

        } catch (DuplicateRecordException | IllegalArgumentException exception) {
            System.out.println("Unable to add record: " + exception.getMessage());
        }
    }

    private static int readQuantity() {
        System.out.print("Quantity: ");
        String text = INPUT.nextLine();
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Quantity must be a whole number.");
        }
    }
}