package com.example.lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class JsonData
{
    String json = null;

    public String JsonString()
    {
        return json;
    }

    public void DownloadJson() {
        String url = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json";
        try
        {
            URL Url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) Url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            line = sb.toString();
            is.close();
            sb.delete(0, sb.length());
            json = line;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
