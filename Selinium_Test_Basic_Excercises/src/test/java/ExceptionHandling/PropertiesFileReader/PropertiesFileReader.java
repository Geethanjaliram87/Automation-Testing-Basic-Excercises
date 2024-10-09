package ExceptionHandling.PropertiesFileReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
    private Properties properties;

    public PropertiesFileReader(String filePath) {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (FileNotFoundException e) {
            System.err.println("Error: Properties file not found at: " + filePath);
            System.err.println("Please ensure the file exists and the path is correct.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error: Unable to read properties file at: " + filePath);
            System.err.println("This might be due to file corruption or read permissions.");
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        // Path to the properties file
        String filePath = "testdata.properties"; // Update this path as needed

        // Create an instance of PropertiesFileReader
        PropertiesFileReader propertiesReader = new PropertiesFileReader(filePath);

        // Access properties
        String username = propertiesReader.getProperty("username");
        String password = propertiesReader.getProperty("password");
        String url = propertiesReader.getProperty("url");

        // Print properties if they were successfully loaded
        if (username != null && password != null && url != null) {
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("URL: " + url);
        } else {
            System.err.println("Error: Some properties could not be retrieved.");
        }
    }
}
