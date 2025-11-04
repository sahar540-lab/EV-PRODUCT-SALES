import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
public class DayWiseSales {
    public static void main(String[] args) {      
        String fileName = "EVE01Sales.txt"; 
        HashMap<String, Double> daySales = new HashMap<>();
        double grandTotal = 0.0;
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                // Extract date, qty, and price
                String dateStr = data[0];
                int qty = Integer.parseInt(data[4]);
                double unitPrice = Double.parseDouble(data[5]);
                // Parse the date
                Date date;
                try {
                    date = format.parse(dateStr); //datestr is a date in string format
                } catch (ParseException e) {
                    System.out.println("Skipping invalid date: " + dateStr);
                    continue;
                }
                // Get the day of the week like Monday, Tuesday
                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
                String dayOfWeek = dayFormat.format(date);
                // Calculate sales amount
                double saleAmount = qty * unitPrice;
                // Add to that day’s total
                double previousTotal = daySales.getOrDefault(dayOfWeek, 0.0); //get the previous totalz add to new one & update
                double newTotal = previousTotal + saleAmount;
                daySales.put(dayOfWeek, newTotal);
                // Add to grand total
                grandTotal += saleAmount;
            }
            br.close();
            System.out.println("Day of the Week Wise Sales Amount:");
            for (String day : daySales.keySet()) {
                System.out.println(day + " = " + daySales.get(day));
            }
            System.out.println("\nGrand Total of All Days = " + grandTotal);
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        } catch (NumberFormatException e) {
            System.out.println("Error in number format.");
        }
    }
}
