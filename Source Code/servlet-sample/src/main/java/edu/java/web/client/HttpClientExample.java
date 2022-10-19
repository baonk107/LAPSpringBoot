package edu.java.web.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpClientExample {
    public static void main(String[] args) throws IOException, InterruptedException {
//        BufferedReader reader = null;
//        try {
//            URL url = new URL("http://localhost:8080/test/html");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.addRequestProperty("Accept-Encoding", "gzip");
//
//            int responseCode = con.getResponseCode();
//            System.out.println("Response Code: " + responseCode);
//
//            InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
//            reader = new BufferedReader(inputStreamReader);
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (reader != null) reader.close();
//        }

        //Demo Async
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = null;
                try {
                    URL obj = new URL("http://localhost:8080/test/synch");
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    con.setRequestMethod("GET");
                    con.addRequestProperty("Accept-Encoding", "gzip");

                    InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
                    reader = new BufferedReader(inputStreamReader);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        executor.shutdown();
        Thread.sleep(5000);
    }
}
