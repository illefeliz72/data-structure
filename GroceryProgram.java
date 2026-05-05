import java.util.Scanner;
import java.io.*;

public class GroceryProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        try {
            FileWriter fw = new FileWriter("receipts.txt", true);
            PrintWriter pw = new PrintWriter(fw);

            String strProdName, strAnotherP, strCustomer;
            char cAnotherP = ' ', cCustomer = ' ';
            double dQty, dBill, dPrice, dTotal, dPay, dChange;

            do {
                dBill = 0;
                pw.println("--- NEW TRANSACTION ---");
                
                do {
                    System.out.println("\nWelcome to EFM Grocery");
                    System.out.print("Input product name: ");
                    strProdName = input.nextLine();
                    
                    System.out.print("Price: ");
                    dPrice = input.nextDouble();
                    
                    System.out.print("Quantity: ");
                    dQty = input.nextDouble();
                    input.nextLine(); 
                    dTotal = dQty * dPrice;
                    dBill += dTotal;

                  
                    pw.println("Item: " + strProdName + " | Qty: " + dQty + " | Subtotal: " + dTotal);
                    System.out.println("Total: " + dTotal);

                    System.out.print("Another product Y/N? ");
                    strAnotherP = input.nextLine();
                    cAnotherP = strAnotherP.charAt(0);

                } while ((cAnotherP == 'Y') || (cAnotherP == 'y'));

                System.out.println("Bill: " + dBill);
                pw.println("Total Bill: " + dBill);

                System.out.print("Payment: ");
                dPay = input.nextDouble();
                input.nextLine();

                if (dPay >= dBill) {
                    dChange = dPay - dBill;
                    System.out.println("Change: " + dChange);
                    System.out.println("Thank you for shopping");
                    
                    pw.println("Payment: " + dPay);
                    pw.println("Change: " + dChange);
                } else {
                    System.out.println("Money not enough!");
                    pw.println("TRANSACTION FAILED: Insufficient Funds");
                }
                
                pw.println("-----------------------\n");

                System.out.print("Another customer Y/N? ");
                strCustomer = input.nextLine();
                cCustomer = strCustomer.charAt(0);

            } while ((cCustomer == 'Y') || (cCustomer == 'y'));

            System.out.println("Grocery program is terminating...");
            pw.close(); 

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
        input.close();
    }
}