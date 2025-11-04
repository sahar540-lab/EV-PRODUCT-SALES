import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
public class MonthWiseSales {
    public static void main(String[] args) {
        String fileName = "EVE01Sales.txt";   // Your sales file
        HashMap<String, Double> monthSales = new HashMap<>();
        double grandTotal = 0.0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                // Extract date, qty, and unit price
                String date = data[0];
                int qty = Integer.parseInt(data[4]);
                double unitPrice = Double.parseDouble(data[5]);
                // Split the date into parts
                String[] parts = date.split("-");
                String month = "";
                // Find the part that starts with a letter (the month)
                for (String p : parts) {
                    if (Character.isLetter(p.charAt(0))) {
                        month = p;
                        break;
                    }
                }
                // Calculate sale amount
                double saleAmount = qty * unitPrice;
                // Add to that month's total
                double previousTotal = monthSales.getOrDefault(month, 0.0); //get the previous totalz add to new one & update
                double newTotal = previousTotal + saleAmount;
                monthSales.put(month, newTotal);
                // Add to grand total
                grandTotal += saleAmount;
            }
            br.close();
            System.out.println("Month Wise Sales Amount:");
            for (String month : monthSales.keySet()) {
                System.out.println(month + " = " + monthSales.get(month));
            }
            System.out.println("\nGrand Total of All Months = " + grandTotal);
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        } catch (NumberFormatException e) {
            System.out.println("Error in number format.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Date format issue in file.");
        }
    }
}

