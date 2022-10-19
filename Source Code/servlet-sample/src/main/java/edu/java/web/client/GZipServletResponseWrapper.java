package edu.java.web.client;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class GZipServletResponseWrapper extends HttpServletResponseWrapper {
    private GZipServletOutputStream stream = null;
    private PrintWriter writer = null;

    public GZipServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public void close() {
        if (stream != null) stream.close();
        if (writer != null) writer.close();
    }

    public void flushBuffer() throws IOException {
        if (writer != null) writer.flush();
        stream.flush();
    }

    public ServletOutputStream getOutputStream() {
        if (stream != null) return stream;
        try {
            stream = new GZipServletOutputStream(getResponse().getOutputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stream;
    }

    public PrintWriter getWriter() {
        if (writer == null) {
            try {
                stream = (GZipServletOutputStream) getOutputStream();
                String encoding = getResponse().getCharacterEncoding();
                writer = new PrintWriter(new OutputStreamWriter(stream, encoding));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return writer;
    }
}
