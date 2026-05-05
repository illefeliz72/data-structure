import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EFMEnterprise {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        try (FileWriter fw = new FileWriter("EFMEnterpriseLog.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {

            do {
                System.out.println("\nWelcome to EFM Enterprise Systems");
                pw.println("\nWelcome to EFM Enterprise Systems");
                System.out.println("We've got it all for you!");
                pw.println("We've got it all for you!");
                System.out.println("1. EFM Grocery ShopperMart POS");
                pw.println("1. EFM Grocery ShopperMart POS");
                System.out.println("2. EFM Movie Rental Registration");
                pw.println("2. EFM Movie Rental Registration");
                System.out.println("3. EFM Colleges Enrollment Registration");
                pw.println("3. EFM Colleges Enrollment Registration");
                System.out.println("4. Exit");
                pw.println("4. Exit");
                System.out.print("Choice: ");
                choice = input.nextInt();
                input.nextLine();
                pw.println("Choice: " + choice);

                switch (choice) {
                    case 1:
                        System.out.println("\nLoading EFM Grocery ShopperMart POS...");
                        pw.println("\nLoading EFM Grocery ShopperMart POS...");
                        String strProdName, strAnotherP;
                        String strCustomer;
                        char cAnotherP = ' ', cCustomer = ' ';
                        double dQty, dBill, dPrice;
                        double dTotal, dPay, dChange = 0;

                        do {
                            dBill = 0;
                            do {
                                System.out.println("\nWelcome to EFM Grocery");
                                pw.println("\nWelcome to EFM Grocery");

                                System.out.print("Input product name: ");
                                strProdName = input.nextLine();
                                pw.println("Input product name: " + strProdName);

                                System.out.print("Price: ");
                                dPrice = input.nextDouble();
                                input.nextLine();
                                pw.println("Price: " + dPrice);

                                System.out.print("Quantity: ");
                                dQty = input.nextDouble();
                                input.nextLine();
                                pw.println("Quantity: " + dQty);

                                dTotal = dQty * dPrice;
                                System.out.println("Total: " + dTotal);
                                pw.println("Total: " + dTotal);

                                dBill += dTotal;

                                System.out.print("Another product Y/N? ");
                                strAnotherP = input.nextLine();
                                pw.println("Another product Y/N? " + strAnotherP);
                                cAnotherP = strAnotherP.charAt(0);

                            } while (cAnotherP == 'Y' || cAnotherP == 'y');

                            System.out.println("Bill: " + dBill);
                            pw.println("Bill: " + dBill);

                            System.out.print("Payment: ");
                            dPay = input.nextDouble();
                            input.nextLine();
                            pw.println("Payment: " + dPay);

                            if (dPay >= dBill) {
                                dChange = dPay - dBill;
                                System.out.println("Change: " + dChange);
                                pw.println("Change: " + dChange);
                                System.out.println("Thank you for shopping");
                                pw.println("Thank you for shopping");
                            } else {
                                System.out.println("Money is not enough!");
                                pw.println("Money is not enough!");
                            }

                            System.out.print("Another customer Y/N? ");
                            strCustomer = input.nextLine();
                            pw.println("Another customer Y/N? " + strCustomer);
                            cCustomer = strCustomer.charAt(0);

                        } while (cCustomer == 'Y' || cCustomer == 'y');

                        System.out.println("Grocery program finished.");
                        pw.println("Grocery program finished.");
                        break;

                    case 2:
                        System.out.println("\nLoading EFM Movie Rental Registration...");
                        pw.println("\nLoading EFM Movie Rental Registration...");
                        int rent = 0, sales = 0;
                        int comedy = 0, horror = 0, scifi = 0, drama = 0, cartoons = 0;
                        int dvdTotal = 0, vcdTotal = 0, tapeTotal = 0;
                        String response = null;

                        do {
                            System.out.println("\nREGISTRATION");
                            pw.println("\nREGISTRATION");
                            System.out.println("1. DVD\n2. VCD\n3. Tape");
                            pw.println("1. DVD\n2. VCD\n3. Tape");
                            System.out.print("Choice: ");
                            int typeChoice = input.nextInt();
                            input.nextLine();
                            pw.println("Choice: " + typeChoice);

                            String type = "";
                            switch (typeChoice) {
                                case 1: type = "DVD"; dvdTotal++; break;
                                case 2: type = "VCD"; vcdTotal++; break;
                                case 3: type = "Tape"; tapeTotal++; break;
                                default: System.out.println("Invalid type!"); pw.println("Invalid type!"); continue;
                            }
                            System.out.println("Type: " + type);
                            pw.println("Type: " + type);

                            System.out.print("Input title: ");
                            String title = input.nextLine();
                            pw.println("Title: " + title);

                            System.out.println("\n1. Horror\n2. Scifi\n3. Drama\n4. Comedy\n5. Cartoons");
                            pw.println("1. Horror\n2. Scifi\n3. Drama\n4. Comedy\n5. Cartoons");
                            System.out.print("Category: ");
                            int category = input.nextInt();
                            input.nextLine();
                            pw.println("Category: " + category);

                            switch (category) {
                                case 1: horror++; break;
                                case 2: scifi++; break;
                                case 3: drama++; break;
                                case 4: comedy++; break;
                                case 5: cartoons++; break;
                                default: System.out.println("Invalid category!"); pw.println("Invalid category!"); continue;
                            }

                            System.out.print("Minutes: ");
                            int minutes = input.nextInt();
                            input.nextLine();
                            pw.println("Minutes: " + minutes);

                            System.out.print("Settings: ");
                            String settings = input.nextLine();
                            pw.println("Settings: " + settings);

                            System.out.println("1. Rental\n2. Sales");
                            pw.println("1. Rental\n2. Sales");
                            System.out.print("Transaction: ");
                            int transaction = input.nextInt();
                            input.nextLine();
                            pw.println("Transaction: " + transaction);

                            switch (transaction) {
                                case 1: rent++; break;
                                case 2: sales++; break;
                                default: System.out.println("Invalid transaction!"); pw.println("Invalid transaction!"); continue;
                            }

                            System.out.print("Price: ");
                            double price = input.nextDouble();
                            input.nextLine();
                            pw.println("Price: " + price);

                            System.out.print("Register Another? Y/N: ");
                            response = input.nextLine();
                            pw.println("Register Another? Y/N: " + response);

                        } while (response.equalsIgnoreCase("Y"));

                        System.out.println("\nReports:");
                        pw.println("\nReports:");
                        System.out.println("For rent: " + rent);
                        pw.println("For rent: " + rent);
                        System.out.println("For Sale: " + sales);
                        pw.println("For Sale: " + sales);
                        System.out.println("DVD Total: " + dvdTotal);
                        pw.println("DVD Total: " + dvdTotal);
                        System.out.println("VCD Total: " + vcdTotal);
                        pw.println("VCD Total: " + vcdTotal);
                        System.out.println("Tape Total: " + tapeTotal);
                        pw.println("Tape Total: " + tapeTotal);
                        System.out.println("Horror movies: " + horror);
                        pw.println("Horror movies: " + horror);
                        System.out.println("Scifi movies: " + scifi);
                        pw.println("Scifi movies: " + scifi);
                        System.out.println("Drama movies: " + drama);
                        pw.println("Drama movies: " + drama);
                        System.out.println("Comedy movies: " + comedy);
                        pw.println("Comedy movies: " + comedy);
                        System.out.println("Cartoons: " + cartoons);
                        pw.println("Cartoons: " + cartoons);
                        break;

                    case 3:
                        System.out.println("\nLoading EFM Colleges Enrollment Registration...");
                        pw.println("\nLoading EFM Colleges Enrollment Registration...");
                        char tabletChoice;
                        do {
                            System.out.println("\nRegister your Graphic Tablet!");
                            pw.println("\nRegister your Graphic Tablet!");

                            System.out.print("Enter brand: ");
                            String brand = input.nextLine();
                            pw.println("Brand: " + brand);
                            System.out.print("Enter model: ");
                            String model = input.nextLine();
                            pw.println("Model: " + model);
                            System.out.print("Enter Pressure Levels: ");
                            int pressureLevels = input.nextInt();
                            input.nextLine();
                            pw.println("Pressure Levels: " + pressureLevels);
                            System.out.print("Enter Connection Type: ");
                            String connectionType = input.nextLine();
                            pw.println("Connection Type: " + connectionType);
                            System.out.print("Enter Price: ");
                            double price = input.nextDouble();
                            input.nextLine();
                            pw.println("Price: " + price);

                            System.out.println("\nTablet Registration Successful");
                            pw.println("\nTablet Registration Successful");
                            System.out.println("Brand: " + brand);
                            System.out.println("Model: " + model);
                            System.out.println("Pressure Sensitivity: " + pressureLevels);
                            System.out.println("Port/Connection: " + connectionType);
                            System.out.printf("Price: $%.2f\n", price);
                            pw.println("Brand: " + brand + ", Model: " + model + ", Pressure: " + pressureLevels + ", Connection: " + connectionType + ", Price: $" + price);

                            System.out.print("\nProceed register another tablet? (Y/N): ");
                            tabletChoice = input.nextLine().toLowerCase().charAt(0);
                            pw.println("Proceed register another tablet? (Y/N): " + tabletChoice);

                        } while (tabletChoice == 'y');
                        System.out.println("Registration closed.");
                        pw.println("Registration closed.");
                        break;

                    case 4:
                        System.out.println("Thank you for using EFM Enterprise Systems");
                        pw.println("Thank you for using EFM Enterprise Systems");
                        System.out.println("Good bye");
                        pw.println("Good bye");
                        input.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                        pw.println("Invalid choice! Please try again.");
                }

            } while (true);

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
