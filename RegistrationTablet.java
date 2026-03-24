import java.util.*;
import java.io.*;

public class RegistrationTablet {

    static Scanner input = new Scanner(System.in);
    static ArrayList<String[]> tablets = new ArrayList<>();
    static String fileName = "tablets.txt";

    public static void main(String[] args) {

        loadFromFile();
        int choice;

        do {
            System.out.println("\n===== GRAPHIC TABLET MENU =====");
            System.out.println("1. Add Tablet");
            System.out.println("2. Search Tablet");
            System.out.println("3. Edit Tablet");
            System.out.println("4. Delete Tablet");
            System.out.println("5. Sort Tablets");
            System.out.println("6. List Tablets");
            System.out.println("7. Access by Index");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    addTablet();
                    break;
                case 2:
                    searchTablet();
                    break;
                case 3:
                    editTablet();
                    break;
                case 4:
                    deleteTablet();
                    break;
                case 5:
                    sortTablets();
                    break;
                case 6:
                    listTablets();
                    break;
                case 7:
                    accessByIndex();
                    break;
                case 8:
                    saveToFile();
                    System.out.println("Program closed.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);
    }

    public static void addTablet() {

        System.out.println("\nRegister your Graphic Tablet!");

        System.out.print("Brand: ");
        String brand = input.nextLine();
        System.out.print("Model: ");
        String model = input.nextLine();
        System.out.print("Pressure Levels: ");
        String pressure = input.nextLine();
        System.out.print("Connection Type: ");
        String connection = input.nextLine();
        System.out.print("Price: ");
        String price = input.nextLine();

        tablets.add(new String[] { brand, model, pressure, connection, price });

        System.out.println("Tablet added successfully.");
    }

    public static void searchTablet() {

        if (tablets.isEmpty()) {
            System.out.println("No tablets registered.");
            return;
        }

        System.out.print("Enter model to search: ");
        String search = input.nextLine();

        boolean found = false;

        for (String[] t : tablets) {
            if (t[1].equalsIgnoreCase(search)) {

                System.out.println("\nTablet Found");
                System.out.println("Brand: " + t[0]);
                System.out.println("Model: " + t[1]);
                System.out.println("Pressure: " + t[2]);
                System.out.println("Connection: " + t[3]);
                System.out.println("Price: " + t[4]);

                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Tablet not found.");
    }

    public static void editTablet() {

        if (tablets.isEmpty()) {
            System.out.println("No tablets to edit.");
            return;
        }

        System.out.print("Enter model to edit: ");
        String search = input.nextLine();

        for (String[] t : tablets) {
            if (t[1].equalsIgnoreCase(search)) {

                System.out.print("New Brand: ");
                t[0] = input.nextLine();

                System.out.print("New Model: ");
                t[1] = input.nextLine();

                System.out.print("New Pressure: ");
                t[2] = input.nextLine();

                System.out.print("New Connection: ");
                t[3] = input.nextLine();

                System.out.print("New Price: ");
                t[4] = input.nextLine();

                System.out.println("Tablet updated.");
                return;
            }
        }

        System.out.println("Tablet not found.");
    }

    public static void deleteTablet() {

        if (tablets.isEmpty()) {
            System.out.println("No tablets to delete.");
            return;
        }

        System.out.print("Enter model to delete: ");
        String search = input.nextLine();

        for (int i = 0; i < tablets.size(); i++) {
            if (tablets.get(i)[1].equalsIgnoreCase(search)) {

                tablets.remove(i);
                System.out.println("Tablet deleted.");
                return;
            }
        }

        System.out.println("Tablet not found.");
    }

    public static void sortTablets() {

        tablets.sort((a, b) -> a[0].compareToIgnoreCase(b[0]));
        System.out.println("Tablets sorted by Brand.");
    }

    public static void listTablets() {

        if (tablets.isEmpty()) {
            System.out.println("No tablets registered.");
            return;
        }

        System.out.println("\nRegistered Tablets");
        System.out.println("--------------------------------------------------------------");

        System.out.printf("%-12s %-20s %-12s %-15s %-10s\n",
                "Brand", "Model", "Pressure", "Connection", "Price");

        for (String[] t : tablets) {
            System.out.printf("%-12s %-20s %-12s %-15s %-10s\n",
                    t[0], t[1], t[2], t[3], t[4]);
        }
    }

    // index feature
    public static void accessByIndex() {

        if (tablets.isEmpty()) {
            System.out.println("No tablets registered.");
            return;
        }

        System.out.print("Enter index: ");
        int index = input.nextInt();
        input.nextLine();

        if (index < 0 || index >= tablets.size()) {
            System.out.println("Invalid index.");
            return;
        }

        String[] t = tablets.get(index);

        System.out.println("\nTablet at index " + index);
        System.out.println("Brand: " + t[0]);
        System.out.println("Model: " + t[1]);
        System.out.println("Pressure: " + t[2]);
        System.out.println("Connection: " + t[3]);
        System.out.println("Price: " + t[4]);

        System.out.print("\nDo you want to update this tablet? (yes/no): ");
        String choice = input.nextLine();

        if (choice.equalsIgnoreCase("yes")) {

            System.out.print("New Brand: ");
            t[0] = input.nextLine();

            System.out.print("New Model: ");
            t[1] = input.nextLine();

            System.out.print("New Pressure: ");
            t[2] = input.nextLine();

            System.out.print("New Connection: ");
            t[3] = input.nextLine();

            System.out.print("New Price: ");
            t[4] = input.nextLine();

            tablets.set(index, t);

            System.out.println("Tablet updated successfully.");
        } else {
            System.out.println("No changes made.");
        }
    }

    public static void saveToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (String[] t : tablets) {
                writer.write(String.join(",", t));
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    public static void loadFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 5)
                    tablets.add(data);
            }

        } catch (IOException e) {
            System.out.println("No existing file found.");
        }
    }

    public static void searchAndRemove() {

        if (tablets.isEmpty()) {
            System.out.println("No tablets registered.");
            return;
        }

        System.out.print("Enter model to search: ");
        String search = input.nextLine();

        for (int i = 0; i < tablets.size(); i++) {

            String[] t = tablets.get(i);

            if (t[1].equalsIgnoreCase(search)) {

                // Display details
                System.out.println("\nTablet Found");
                System.out.println("Brand: " + t[0]);
                System.out.println("Model: " + t[1]);
                System.out.println("Pressure: " + t[2]);
                System.out.println("Connection: " + t[3]);
                System.out.println("Price: " + t[4]);

                // Ask user
                System.out.print("\nDo you want to remove this tablet? (yes/no): ");
                String choice = input.nextLine();

                if (choice.equalsIgnoreCase("yes")) {
                    tablets.remove(i); // removes and shifts automatically
                    System.out.println("Tablet removed successfully.");
                } else {
                    System.out.println("Tablet not removed.");
                }

                return;
            }
        }

        System.out.println("Tablet not found.");
    }
}