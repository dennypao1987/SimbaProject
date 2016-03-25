package com.aandd.simbaproject.connect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPostCall {

    public String call(String... arg0){
        OutputStreamWriter request = null;
        String response = null;
        URL url;
        String parameters = "username="+(String)arg0[0]+"&password="+(String)arg0[1];
        String link="http://ilmagico.altervista.org/android/list.php";
        HttpURLConnection connection;

        try{
            url = new URL(link);
            connection = (HttpURLConnection)url.openConnection();
//                parameters = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8");
//                parameters += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            request = new OutputStreamWriter(connection.getOutputStream());
            request.write(parameters);
            request.flush();
            request.close();

            String line = "";
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();

            while((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
            response = sb.toString();
            return response;
        }
        catch(Exception e){
            e.printStackTrace();
            return new String("Exception: " + e.getMessage());
        }
    }

}
