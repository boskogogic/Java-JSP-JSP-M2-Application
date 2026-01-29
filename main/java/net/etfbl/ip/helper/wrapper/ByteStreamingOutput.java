package net.etfbl.ip.helper.wrapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;


/**
 *
 * @author lx.ds
 */
public class ByteStreamingOutput implements StreamingOutput {

    ByteArrayOutputStream input;

    /**
     * File streaming output to send the file to client
     * @param input
     */
    public ByteStreamingOutput(OutputStream input) {
        this.input = (ByteArrayOutputStream)input;
    }

    @Override
    public void write(OutputStream output) throws IOException, WebApplicationException {
        try {
            InputStream inputStream = new ByteArrayInputStream(input.toByteArray());
            int bytes;
            while ((bytes = inputStream.read()) != -1) {
                output.write(bytes);
            }
            
        } catch (Exception e) {
            throw new WebApplicationException(e);
        } finally {
            if (output != null) output.close();
            input.close();
        }
    }
    
}
