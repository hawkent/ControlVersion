package com.example.mapav10;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;



public class Url extends AppCompatActivity {
     static WebView webView;
        String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.url);
        webView = findViewById(R.id.webView);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
            url = extras.getString("url");
        webView.loadUrl(url);

    }


}
