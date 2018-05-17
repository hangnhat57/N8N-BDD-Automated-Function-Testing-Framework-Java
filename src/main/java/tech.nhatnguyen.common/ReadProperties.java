package tech.nhatnguyen.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadProperties {
    private String propFile ;

    public ReadProperties(String propFileName) {
        this.propFile = System.getProperty("user.dir") +"/"+ propFileName;
    }
    
    public String getValue(String key){
        try{
            java.util.Properties prop = new java.util.Properties();
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream(propFile);
            if (inputStream != null){
                prop.load(inputStream);
            }
            else {
                throw new FileNotFoundException("Properties file not found : "+ propFile);
            }
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return "Not found";
        }

    }
    public int getNumber(String key){
        try{
            java.util.Properties prop = new java.util.Properties();
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream(propFile);
            if (inputStream != null){
                prop.load(inputStream);
            }
            else {
                throw new FileNotFoundException("Properties file not found : "+ propFile);
            }
            return Integer.parseInt(prop.getProperty(key));
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

    }
}
