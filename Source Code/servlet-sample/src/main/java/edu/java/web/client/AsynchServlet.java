package edu.java.web.client;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(urlPatterns = "/synch", asyncSupported = true)
public class AsynchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final AsyncContext ctx = req.startAsync();
        ctx.setTimeout(60 * 1000);
        ctx.start(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3*1000);

                    //ctx.res: phản hồi khi startAsync được gọi
                    Writer writer = ctx.getResponse().getWriter();
                    URL obj = new URL("https://www.google.com/");
                    HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

//                    writer.write("Hello Async");
                    InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line;
                    while ((line = reader.readLine()) != null){
                        writer.write(line);
                    }
                    //complete: Hoàn thành các hoạt động không đồng bộ
                    ctx.complete();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
