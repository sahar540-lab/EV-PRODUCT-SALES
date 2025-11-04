import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class AutoMenuFromHeader {
    public static void main(String[] args) {
        String fileName = "EVE01Sales.txt"; 
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            // Read only the first line (header)
            String headerLine = br.readLine();
            if (headerLine != null) {
                String[] headers = headerLine.split("\t");
                System.out.println("=== Auto-Generated Menu ===");
                // Loop through and display menu options
                for (int i = 0; i < headers.length; i++) {
                    System.out.println((i + 1) + ". " + headers[i]);
                }
            } else {
                System.out.println("File is empty or missing header line.");
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        }
    }
}
