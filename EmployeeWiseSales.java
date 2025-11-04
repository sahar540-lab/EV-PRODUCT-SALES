import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
public class EmployeeWiseSales {
    public static void main(String[] args) {
        String fileName = "EVE01Sales.txt";  
        HashMap<String, Double> employeeSales = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            br.readLine();   // skip the first line
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                // Extract Rep ID, Qty, and Unit Price
                String repId = data[2];
                int qty = Integer.parseInt(data[4]);
                double unitPrice = Double.parseDouble(data[5]);
                // Calculate sale amount for the record
                double saleAmount = qty * unitPrice;
                // get the previous total of this employee & if not fount then 0
                double previousTotal = employeeSales.getOrDefault(repId, 0.0);
                // add the new sale amount to the previous total
                double newTotal = previousTotal + saleAmount;
                // update the total into the map
                employeeSales.put(repId, newTotal);
            }
            br.close();
            System.out.println("Employee Wise Sales Total:");
            for (String rep : employeeSales.keySet()) {    //Go through each employee ID (key) in the map and print their total sales
                System.out.println(rep + " = " + employeeSales.get(rep));
            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        } catch (NumberFormatException e) {
            System.out.println("Error in number format.");
        }
    }
}
