import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
public class ProductWiseSales {
    public static void main(String[] args) {
        String fileName = "EVE01Sales.txt";  
        HashMap<String, Double> productSales = new HashMap<>();
        double grandTotal = 0;  // to store total of all products
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                // Extract Product, Qty, and Unit Price
                String product = data[3];
                int qty = Integer.parseInt(data[4]);
                double unitPrice = Double.parseDouble(data[5]);
                // Calculate sale amount for this record
                double saleAmount = qty * unitPrice;
                // Add this to product-wise total
                double previousTotal = productSales.getOrDefault(product, 0.0);
                double newTotal = previousTotal + saleAmount;
                productSales.put(product, newTotal); //Save the newTotal amount for this product inside the HashMap
                grandTotal += saleAmount;
            }
            br.close();
            System.out.println("Product Wise Sales Amount:");
            for (String product : productSales.keySet()) {
                System.out.println(product + " = " + productSales.get(product));
            }
            System.out.println("\nGrand Total of All Products = " + grandTotal);
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        } catch (NumberFormatException e) {
            System.out.println("Error in number format.");
        }
    }
}
