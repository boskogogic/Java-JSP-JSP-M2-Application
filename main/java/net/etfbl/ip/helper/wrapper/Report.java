package net.etfbl.ip.helper.wrapper;

import fr.opensagres.xdocreport.core.io.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import net.etfbl.ip.helper.ITemplate;
import net.etfbl.ip.helper.utils.FileUtils;
import net.etfbl.ip.helper.utils.StringUtils;


/**
 *
 * @author lx.ds
 */
public class Report {

    private String templatePathAndName;
    private String fileNameWithoutExtension;
    private String fileNameWithExtension;
    private ITemplate template;
    
    public Report(String templatePathAndName) throws Exception {
        initialize(templatePathAndName, null);
    }
    public Report(String templatePathAndName, String fileNameWithoutExtension) throws Exception {
       initialize(templatePathAndName, fileNameWithoutExtension);
    }
    
    private void initialize(String templatePathAndName, String fileNameWithoutExtension) {
        this.templatePathAndName = templatePathAndName;
        this.fileNameWithoutExtension = fileNameWithoutExtension;
    }
    private ITemplate getTemplate() throws IOException, Exception {
        if (template == null) template = new XDocReport(getTemplatePathAndName());
        return template;
    }
    
    private void setFileName(String extension) throws Exception {
        if (!StringUtils.isNullOrEmpty(getFileNameWithoutExtension())) setFileNameWithExtension(getFileNameWithoutExtension() + "." + extension);
        setValue("fileName", getFileNameWithExtension());
    }
    
    public OutputStream save(String type) throws Exception {
        if (StringUtils.isNullOrEmpty(type)) return null;
        setFileName(type);
        if (StringUtils.endsWithIgnoreCase(type, "doc")) return saveAsDoc();
        if (StringUtils.endsWithIgnoreCase(type, "docx")) return saveAsDocx();
        if (StringUtils.endsWithIgnoreCase(type, "pdf")) return saveAsPdf();
        if (StringUtils.endsWithIgnoreCase(type, "html")) return saveAsHtml();
        return null;
    }
    
    public OutputStream saveAsDoc() throws Exception {
        try { return getTemplate().saveAsDoc(); } catch (IOException ex) { throw new Exception("Save as DOC exception", ex); }
    }
    public OutputStream saveAsDocx() throws Exception {
        try { return getTemplate().saveAsDoc(); } catch (IOException ex) { throw new Exception("Save as DOC exception", ex); }
    }
    public OutputStream saveAsPdf() throws Exception {
        try { return getTemplate().saveAsPdf(); } catch (IOException ex) { throw new Exception("Save as PDF exception", ex); }
    }
    
    public OutputStream saveAsHtml() throws Exception {
        try { return getTemplate().saveAsHtml(); } catch (IOException ex) { throw new Exception("Save as HTML exception", ex); }
    }
    
    public void saveToFile(File file) throws Exception {
        saveToFile(file, FileUtils.getFileExtension(file.getName()));
    }
    public void saveToFile(File file, String fileExtension) throws Exception {
        setFileName(fileExtension);
        
        try (FileOutputStream output = new FileOutputStream(file)) {
            
            ByteArrayOutputStream stream;
            
            if (StringUtils.endsWithIgnoreCase(fileExtension, "pdf")) stream = (ByteArrayOutputStream)getTemplate().saveAsPdf();
            else if (StringUtils.endsWithIgnoreCase(fileExtension, "doc")) stream = (ByteArrayOutputStream)getTemplate().saveAsDoc();
            else if (StringUtils.endsWithIgnoreCase(fileExtension, "docx")) stream = (ByteArrayOutputStream)getTemplate().saveAsDoc(); 
            else if (StringUtils.endsWithIgnoreCase(fileExtension, "html")) stream = (ByteArrayOutputStream)getTemplate().saveAsHtml(); 
            else throw new Exception("Invalid export file extension!");
            
            IOUtils.copy(new ByteArrayInputStream(stream.toByteArray()), output);
            output.flush();
        }
    }
    
    public <TData extends Object> ITemplate fill(String key, TData data) throws Exception {
        ITemplate oTemplate;
        try {
            oTemplate = getTemplate();
            oTemplate.fill(key, data);
        } catch (IOException ex) {
            throw new Exception("set value: ", ex);
        }
        return oTemplate;
    }

    public <TData extends Object> ITemplate fill(String key, Class<TData> clazz, List<TData> data) throws Exception {
        ITemplate oTemplate;
        try {
            oTemplate = getTemplate();
            oTemplate.fill(key, clazz, data);
        } catch (IOException ex) {
            throw new Exception("set value: ", ex);
        }
        return oTemplate;
    }
    
    public void setValue(String name, String value) throws Exception {
        try {
            getTemplate().setValue(name, value);
        } catch (IOException ex) {
            throw new Exception("set value: ", ex);
        }
    }
    
    public void setValueAsHtml(String name, String value) throws Exception {
        try {
            getTemplate().setValueAsHtml(name, value);
        } catch (IOException ex) {
            throw new Exception("set html value: ", ex);
        }
    }
    
    public void setFieldAsHtml(String key) throws Exception {
        getTemplate().setFieldAsHtml(key);
    }

    /**
     * @return the templatePathAndName
     */
    public String getTemplatePathAndName() {
        return templatePathAndName;
    }

    /**
     * @return the fileNameWithoutExtension
     */
    public String getFileNameWithoutExtension() {
        return fileNameWithoutExtension;
    }

    /**
     * @param fileNameWithoutExtension the fileNameWithoutExtension to set
     */
    public void setFileNameWithoutExtension(String fileNameWithoutExtension) {
        this.fileNameWithoutExtension = fileNameWithoutExtension;
    }

    /**
     * @return the fileNameWithExtension
     */
    public String getFileNameWithExtension() {
        return fileNameWithExtension;
    }

    /**
     * @param fileNameWithExtension the fileNameWithExtension to set
     */
    public void setFileNameWithExtension(String fileNameWithExtension) {
        this.fileNameWithExtension = fileNameWithExtension;
    }
}
