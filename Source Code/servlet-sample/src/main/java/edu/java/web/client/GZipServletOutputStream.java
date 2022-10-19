package edu.java.web.client;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GZipServletOutputStream extends ServletOutputStream {
    private GZIPOutputStream stream = null;

    public GZipServletOutputStream(OutputStream output) {
        try {
            stream = new GZIPOutputStream(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            stream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    public void write(byte[] bytes) throws IOException {
        stream.write(bytes);
    }

    public void write(byte[] bytes, int off, int len) throws IOException {
        stream.write(bytes, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        stream.write(b);
    }

}
