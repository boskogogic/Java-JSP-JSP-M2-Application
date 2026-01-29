package net.etfbl.ip.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author lx.bg
 */
public class PropertiesFile {
    
    private final String filePathAndName;
    private Properties properties;
    
    public PropertiesFile(String filePathAndName) {
        File file = new File(filePathAndName);
        if (!file.exists()) throw new PropertiesException("File (%s) doesn't exists", filePathAndName);
        this.filePathAndName = filePathAndName;
    }
    public String get(String name) {
        return get(name, null);
    }
    public String get(String name, String defaultValue) {
        return getProperties().getProperty(name, defaultValue);
    }
 
    private Properties getProperties() throws PropertiesException {
        if (properties == null) {
            properties = new Properties();
            InputStream input = null;
            try { 
                input = new FileInputStream(filePathAndName);
                properties.load(input); 
            }
            catch (IOException ex) { throw new PropertiesException(ex); }
            finally {
                if (input != null) {
                    try { input.close(); } catch (Exception ex) { throw new PropertiesException(ex); }
                }
            }
        }
        return properties;
    }
    
    public Enumeration<?> getPropertyNames() {
        return getProperties().propertyNames();
    }
    
    public Set<Map.Entry<Object, Object>> getPropertySet() {
        return getProperties().entrySet();
    }
    
    public String getFilePathAndName() {
        return filePathAndName;
    }
    
    public void close() throws Exception {
        if (properties != null) properties.clear();
    }
}
