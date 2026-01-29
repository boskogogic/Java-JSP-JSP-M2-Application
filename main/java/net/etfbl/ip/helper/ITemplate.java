
package net.etfbl.ip.helper;

import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author lx.ds
 */
public interface ITemplate {

    OutputStream saveAsDoc() throws Exception;
    
    OutputStream saveAsPdf() throws Exception;
    
    OutputStream saveAsHtml() throws Exception;

    <TData extends Object> ITemplate fill(String key, TData data) throws Exception;

    <TData extends Object> ITemplate fill(String key, Class<TData> clazz, List<TData> data) throws Exception;

    void setValue(String name, String value) throws Exception;
    
    void setValueAsHtml(String name, String value) throws Exception;
    void setFieldAsHtml(String name) throws Exception;
}
