package dbinterface;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DatabaseConfig {
    public String dbUrl;
    public String dbName;
    public String collectionName;
    public String dbUsername;
    public char[] dbPassword;

    public DatabaseConfig() throws IOException {
        Properties databaseProperties = new Properties();
        URL url = ClassLoader.getSystemResource("database.properties");
        databaseProperties.load(url.openStream());
        dbUrl = databaseProperties.getProperty("dbUrl");
        dbName = databaseProperties.getProperty("dbName");
        collectionName = databaseProperties.getProperty("collectionName");
        dbUsername = databaseProperties.getProperty("dbUsername");
        dbPassword = databaseProperties.getProperty("dbPassword").toCharArray();
    }

}
