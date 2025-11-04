import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class TotalSalesAmount {
    public static void main(String[] args) {
        String fileName = "EVE01-Sales.txt";  
        double totalSales = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            br.readLine();   // Skip the first line (column headings)
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");  // Split the line using tab (\t)
                // Get Qty and Unit Price (last two columns)
                int qty = Integer.parseInt(data[4]);
                double unitPrice = Double.parseDouble(data[5]);
                // Calculate total for this record
                double recordTotal = qty * unitPrice;
                // Add it to overall total
                totalSales += recordTotal;
            }
            br.close();
            System.out.println("Total Sales Amount = " + totalSales);
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        } catch (NumberFormatException e) {
            System.out.println("Error in number format.");
        }
    }
}
