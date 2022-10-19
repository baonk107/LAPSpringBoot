package edu.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(value = "/upload", name = "upload-servlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class UploadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();
        String appPath = req.getServletContext().getRealPath("");
        File folder = new File(appPath, "temp");
        if (!folder.exists()) folder.mkdir();
        writer.println("---------" + appPath);

        for (Part part : req.getParts()) {
            String name = extractFileName(part);
            byte[] buff = new byte[1024];
            int read = -1;
            FileOutputStream outputStream = new FileOutputStream(new File(folder, name));
            try {
                InputStream inputStream = part.getInputStream();
                while ((read = inputStream.read(buff)) != -1) {
                    outputStream.write(buff);
                }
            } finally {
                outputStream.close();
            }

        }
        //Extract File Name
        req.getParts().forEach(part -> {
            String name = extractFileName(part);
            writer.println("File Name: " + name);
        });

        for (Part part : req.getParts()) {
            Collection<String> headers = part.getHeaderNames();
            headers.forEach(header -> {
                writer.println(header + " : " + part.getHeader(header));
            });
        }

        writer.println("Upload has been done successfully!");
    }

    private String extractFileName(Part part) {
        String content = part.getHeader("content-disposition");
        Pattern pattern = Pattern.compile(".*filename\\=\"(.*)\".*");
        Matcher matcher = pattern.matcher(content);
        return matcher.matches() ? matcher.group(1) : "unknown";
    }
}
