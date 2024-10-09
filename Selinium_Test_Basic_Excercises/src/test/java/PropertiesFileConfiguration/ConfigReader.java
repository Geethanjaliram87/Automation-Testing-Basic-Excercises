package PropertiesFileConfiguration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader(String filePath) {
        properties = new Properties();
        try {
            FileInputStream input = new FileInputStream(filePath);
            properties.load(input);
            input.close();
        } catch (IOException e) {
            System.err.println("Error reading properties file: " + e.getMessage());
        }
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getBaseURL() {
        return properties.getProperty("baseURL");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public static void main(String[] args) {
        // Specify the path to your properties file
        ConfigReader configReader = new ConfigReader("config.properties");

        // Access the configuration values
        String browser = configReader.getBrowser();
        String baseURL = configReader.getBaseURL();
        String username = configReader.getUsername();
        String password = configReader.getPassword();

        // Print the configurations
        System.out.println("Browser: " + browser);
        System.out.println("Base URL: " + baseURL);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // Here, you can implement the logic to initialize WebDriver using the browser configuration
        // and navigate to the base URL for your tests.
    }
}
