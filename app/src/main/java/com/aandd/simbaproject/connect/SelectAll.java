package com.aandd.simbaproject.connect;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.aandd.simbaproject.activity.AllRecordActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SelectAll extends AsyncTask<String,Void,String> {

    private String result;
    private Context context;

    public SelectAll(Context context, String result) {
        this.context = context;
        this.result = result;
    }

    protected void onPreExecute(){ }

    @Override
    protected String doInBackground(String... arg0) {

        OutputStreamWriter request = null;
        String response = null;
        URL url;
        String parameters = "username="+(String)arg0[0]+"&password="+(String)arg0[1];
        String link="http://ilmagico.altervista.org/android/list.php";
        HttpURLConnection connection;
            try{
                url = new URL(link);
                connection = (HttpURLConnection)url.openConnection();
                parameters = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode((String)arg0[0], "UTF-8");
                parameters += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode((String)arg0[1], "UTF-8");
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

    @Override
    protected void onPostExecute(String result){
        String jsonParsed[];
        JSONArray jsonArray;
        JSONObject json;
        try {
            jsonArray = new JSONArray(result);
            jsonParsed = new String[jsonArray.length()];
            for(int i=0;i<jsonArray.length();i++){
                json = (JSONObject) jsonArray.get(i);
                jsonParsed[i] = json.get("fileName").toString();
            }
            ((AllRecordActivity) context).setRecordList(jsonParsed);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, jsonParsed);
            ((AllRecordActivity) context).setListAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
