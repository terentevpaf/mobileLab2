package com.example.lab2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    private Handler uHandler;
    JsonTask jst;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        uHandler = new Handler();
        setContentView(R.layout.activity_main);
        WebView webView = (WebView)findViewById(R.id.mainWV);
        webView.loadUrl("file:///android_asset/www/123.html");
        jst = new JsonTask();
        jst.execute();
    }

    class JsonTask extends AsyncTask<Void, Void, Void> {
        JsonData jd = new JsonData();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            jd.DownloadJson();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Intent i = new Intent(MainActivity.this, FullscreenActivity.class);
            i.putExtra("json", jd.JsonString());
            startActivity(i);
            finish();
        }
    }
}
