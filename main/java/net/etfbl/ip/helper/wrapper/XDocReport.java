package net.etfbl.ip.helper.wrapper;

import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.converter.XDocConverterException;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.document.SyntaxKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import net.etfbl.ip.helper.ITemplate;

/**
 *
 * @author lx.ds
 */
public class XDocReport implements ITemplate {
    
    IXDocReport report;
    FieldsMetadata fieldsMetadata;
    IContext context;
    
    public XDocReport(String pathAndName) throws IOException, XDocReportException {
        
        if (pathAndName.contains(":\\")) _initialize(new FileInputStream(pathAndName));
        else _initialize(this.getClass().getResourceAsStream(pathAndName));
    }

    @Override
    public OutputStream saveAsDoc() throws Exception {
        return save();
    }

    @Override
    public OutputStream saveAsPdf() throws Exception {
        return convertToType(ConverterTypeTo.PDF,null /*PdfOptions.create().fontEncoding("UTF-8")*/);
    }
    
    @Override
    public OutputStream saveAsHtml() throws Exception {
        return convertToType(ConverterTypeTo.XHTML, null);
    }
    
    public <TData extends Object> ITemplate fill(TData data) {
        Field[] fields = data.getClass().getDeclaredFields();
        
        for (Field aField : fields) {
            /*if (!LxObjectUtils.isDefaultValue(data, aField.getName()))
            {
                try {
                    setValue(aField.getName(), LxObjectUtils.<String>get(data, aField));
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(XDocReport.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/
        }
        
        return this;
    }

    @Override
    public <TData extends Object> ITemplate fill(String key, TData data) throws Exception {
        try {
            fieldsMetadata.load(key, data.getClass(), false);
            context.put(key, data);
        } catch (XDocReportException ex) {
            throw new Exception(ex);
        }
        return this;
    }

    @Override
    public <TData extends Object> ITemplate fill(String key, Class<TData> clazz, List<TData> list) throws Exception {
        try {
            fieldsMetadata.load(key, clazz, true);
            context.put(key, list);
        } catch (XDocReportException ex) {
            throw new Exception(ex);
        }
        return this;
    }

    @Override
    public void setValue(String key, String value) {
        context.put(key, value);
    }
    
    @Override
    public void setFieldAsHtml(String key) throws Exception {
        fieldsMetadata.addFieldAsTextStyling(key, SyntaxKind.Html);
    }
    
    @Override
    public void setValueAsHtml(String key, String value) {
        fieldsMetadata.addFieldAsTextStyling(key, SyntaxKind.Html);
        context.put(key, value);
    }

    public void setValueAsImage(String key, IImageProvider value) {
        fieldsMetadata.addFieldAsImage(key);
        context.put(key, value);
    }
    
    private OutputStream convertToType(ConverterTypeTo type, Object subOption) throws Exception {
        // CONVERT to HTML
        Options options = Options.getTo(type).via(ConverterTypeVia.XWPF);
        // you can pass an optional PDFViaITextOptions instance
        if (subOption != null) options.subOptions(subOption);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            report.convert(context, options, stream);
        } catch (XDocConverterException ex) {
            throw new Exception("XDocConverterException: " + ex.getMessage(), ex);
        } catch (XDocReportException ex) {
            throw new Exception("XDocReportException: " + ex.getMessage(), ex);
        }
        return stream;
    }
    
    private OutputStream save() throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            report.process(context, stream);
        } catch (XDocReportException ex) {
            throw new Exception("XDocReportException: " + ex.getMessage(), ex);
        }
        return stream;
    }
    
    private void _initialize(InputStream sourceStream) throws IOException, XDocReportException {
        // Load DOCX template file
        report = XDocReportRegistry.getRegistry().loadReport(sourceStream, TemplateEngineKind.Velocity);
        context = report.createContext();
        fieldsMetadata = report.createFieldsMetadata();
    }

}