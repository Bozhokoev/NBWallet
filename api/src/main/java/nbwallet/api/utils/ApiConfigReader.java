package nbwallet.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConfigReader {

    private static Properties properties;

    private ApiConfigReader(){
    }

    static {
        try{
            InputStream fileInputStream = ApiConfigReader.class.getClassLoader().getResourceAsStream("nbwallet.properties");
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }

    public static String getValue(String key){
        return properties.getProperty(key).trim();
    }
}
