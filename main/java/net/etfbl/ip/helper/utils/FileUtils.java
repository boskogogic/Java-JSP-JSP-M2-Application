package net.etfbl.ip.helper.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.Base64;

/**
 *
 * @author lx.ds
 */
public class FileUtils {
    
    
    /**
     * Get file object by path and name
     * @param path
     * @param name
     * @return file object by path and name
     */
    public static File getFile(String path, String name) {
        return new File(getFilePathAndName(path, name, null));
    }
    
    /**
     * Get file path and name
     * @param path
     * @param name
     * @param extension
     * @return file path and name created by path, name and extension
     */
    public static String getFilePathAndName(String path, String name, String extension) {
        return Paths.get(path, StringUtils.concat(name, ".", extension)).toString();
    }
    
    /**
     * Get file name without extension
     * @param fileNameAndPath
     * @return file name without extension of file name
     */
    public static String getFileNameWithoutExtension(String fileNameAndPath) {
        int i = fileNameAndPath.lastIndexOf('.');
        int p = Math.max(fileNameAndPath.lastIndexOf('/'), fileNameAndPath.lastIndexOf('\\'));
        if (i > p) return fileNameAndPath.substring(p+1, i+1);
        return "";
    }

    /**
     * Get file extension by file name
     * @param fileName
     * @return extension of file name
     */
    public static String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
        if (i > p) return fileName.substring(i+1);
        return "";
    }
    
    public static boolean isFileExists(String fileNameAndPath) {
        File f = new File(fileNameAndPath);
        return (f.exists() && !f.isDirectory());
    }
    public static void deleteFileIfExists(String fileNameAndPath) {
        File f = new File(fileNameAndPath);
        if (f.exists() && !f.isDirectory()) f.delete();
    }
    public static void deleteFile(String fileNameAndPath) {
        File f = new File(fileNameAndPath);
        f.delete();
    }
    
    public static String getFileContentType(String fileName) {
        if (StringUtils.isNullOrEmpty(fileName)) return fileName;
        
        if (StringUtils.endsWithIgnoreCase(fileName, ".pdf")) return "application/pdf";
        else if (StringUtils.endsWithIgnoreCase(fileName, ".html")) return "text/html";
        else if (StringUtils.endsWithIgnoreCase(fileName, ".doc")) return "application/msword";
        else if (StringUtils.endsWithIgnoreCase(fileName, ".docx")) return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        else if (StringUtils.endsWithIgnoreCase(fileName, ".txt")) return "text/plain";
        else return "application/octet-stream";
    }
    
    public static String encodeUrlString(String url) {
        if (url == null || url.isEmpty()) return url;
        url = url.replace("&amp;", "%26");
        url = url.replace("&", "%26");
        return url;
    }
    public static String decodeUrlString(String url) {
        if (url == null || url.isEmpty()) return url;
        url = url.replace("%26", "&");
        return url;
    }
    
    //public static File downloadLinkToFile(String link, String username, String password, String fileName) {
    //    return downloadLinkToFile(link, username, password, getFile(LxConfig.getInstance().getTemporaryFileLocation(), fileName));
   // }
    public static File downloadLinkToFile(String link, String username, String password, File toFile) throws Exception {
        OutputStream stream = null;
        try { stream = downloadLinkToOutputStream(link, username, password, new FileOutputStream(toFile)); }
        catch (FileNotFoundException e) { throw new Exception("download to file"); }
        finally { 
            try { 
                if (stream != null) { stream.close(); stream.close(); }
            } catch (IOException e) { throw new Exception("close stream"); }
        }
        return toFile;
    }
    
    public static OutputStream downloadLinkToOutputStream(String link, String username, String password, OutputStream stream) throws Exception {
        if (StringUtils.isNullOrEmpty(link)) return null;
        if (stream == null) throw new Exception("OutputStream is not defined");
        
        // decode link because of ampersand
        link = decodeUrlString(link);
        
        URLConnection uc = null;
        
        try {
            uc = new URL(link).openConnection();
        } catch (IOException e) {
           
            throw new Exception( "open url connection");
        }
        if (uc == null) return null;
        
        
        if (!StringUtils.isNullOrEmpty(username) && !StringUtils.isNullOrEmpty(password)) {
            String authBasicToken = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
            //logger.debug("auth basic token: %s", authBasicToken);
            uc.setRequestProperty("Authorization", "Basic " + authBasicToken);
        }
        
        try (BufferedInputStream in = new BufferedInputStream(uc.getInputStream())) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                stream.write(dataBuffer, 0, bytesRead);
            }
            
        } catch (Exception e) {
            throw new Exception("download");
        }
        
        return stream;
    }
}
