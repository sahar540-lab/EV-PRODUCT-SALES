import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
public class SalesReportMenu{
    static String fileName = "EVE01Sales.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            printMenu();
            System.out.print("Enter your choice: ");
            while (!sc.hasNextInt()) {
                System.out.print("Please enter a number (0-6): ");
                sc.next();
            }
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    totalSales();
                    break;
                case 2:
                    employeeWise();
                    break;
                case 3:
                    productWise();
                    break;
                case 4:
                    regionWise();
                    break;
                case 5:
                    monthWise();
                    break;
                case 6:
                    dayWise();
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
            System.out.println(); // spacing after each action
        } while (choice != 0);
        sc.close();
    }
    static void printMenu() {
        System.out.println("\n=== SALES REPORT MENU ===");
        System.out.println("1. Total Sales Amount");
        System.out.println("2. Employee-wise Sales");
        System.out.println("3. Product-wise Sales");
        System.out.println("4. Region-wise Sales");
        System.out.println("5. Month-wise Sales");
        System.out.println("6. Day of the Week-wise Sales");
        System.out.println("0. Exit");
    }
    // Total Sales
    static void totalSales() {
        double total = 0.0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split("\t");
                int qty = Integer.parseInt(d[4]);
                double price = Double.parseDouble(d[5]);
                total += qty * price;
            }
            System.out.println("Total Sales Amount = " + total);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading the file.");
        }
    }
    //  Employee-wise Sales
    static void employeeWise() {
        HashMap<String, Double> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split("\t");
                String emp = d[2];
                int qty = Integer.parseInt(d[4]);
                double price = Double.parseDouble(d[5]);
                double sale = qty * price;
                map.put(emp, map.getOrDefault(emp, 0.0) + sale);
            }
            System.out.println("Employee-wise Sales:");
            for (Map.Entry<String, Double> e : map.entrySet()) {
                System.out.println(e.getKey() + " = " + e.getValue());
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading the file.");
        }
    }
    //  Product-wise Sales
    static void productWise() {
        HashMap<String, Double> map = new HashMap<>();
        double grand = 0.0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split("\t");
                String product = d[3];
                int qty = Integer.parseInt(d[4]);
                double price = Double.parseDouble(d[5]);
                double sale = qty * price;
                map.put(product, map.getOrDefault(product, 0.0) + sale);
                grand += sale;
            }
            System.out.println("Product-wise Sales:");
            for (Map.Entry<String, Double> e : map.entrySet()) {
                System.out.println(e.getKey() + " = " + e.getValue());
            }
            System.out.println("Grand Total = " + grand);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading the file.");
        }
    }
    // Region-wise Sales
    static void regionWise() {
        HashMap<String, Double> map = new HashMap<>();
        double grand = 0.0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split("\t");
                String region = d[1];
                int qty = Integer.parseInt(d[4]);
                double price = Double.parseDouble(d[5]);
                double sale = qty * price;
                map.put(region, map.getOrDefault(region, 0.0) + sale);
                grand += sale;
            }
            System.out.println("Region-wise Sales:");
            for (Map.Entry<String, Double> e : map.entrySet()) {
                System.out.println(e.getKey() + " = " + e.getValue());
            }
            System.out.println("Grand Total = " + grand);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading the file.");
        }
    }
    // Month-wise Sales 
    static void monthWise() {
        HashMap<String, Double> map = new HashMap<>();
        double grand = 0.0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split("\t");
                String dateStr = d[0];
                String[] parts = dateStr.split("-");
                String month = "";
                for (String p : parts) {
                    if (p.length() > 0 && Character.isLetter(p.charAt(0))) {
                        month = p;
                        break;
                    }
                }
                int qty = Integer.parseInt(d[4]);
                double price = Double.parseDouble(d[5]);
                double sale = qty * price;
                map.put(month, map.getOrDefault(month, 0.0) + sale);
                grand += sale;
            }
            System.out.println("Month-wise Sales:");
            for (Map.Entry<String, Double> e : map.entrySet()) {
                System.out.println(e.getKey() + " = " + e.getValue());
            }
            System.out.println("Grand Total = " + grand);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading the file.");
        }
    }
    // 6) Day of the Week-wise Sales
    static void dayWise() {
        HashMap<String, Double> map = new HashMap<>();
        double grand = 0.0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split("\t");
                String dateStr = d[0];
                LocalDate date;
                try {
                    date = LocalDate.parse(dateStr, formatter);
                } catch (DateTimeParseException ex) {
                    // skip rows with different/invalid date format
                    continue;
                }
                String day = date.getDayOfWeek().toString();     // MONDAY
                day = day.charAt(0) + day.substring(1).toLowerCase(); // Monday
                int qty = Integer.parseInt(d[4]);
                double price = Double.parseDouble(d[5]);
                double sale = qty * price;
                map.put(day, map.getOrDefault(day, 0.0) + sale);
                grand += sale;
            }
            System.out.println("Day of the Week-wise Sales:");
            for (Map.Entry<String, Double> e : map.entrySet()) {
                System.out.println(e.getKey() + " = " + e.getValue());
            }
            System.out.println("Grand Total = " + grand);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading the file.");
        }
    }
}
