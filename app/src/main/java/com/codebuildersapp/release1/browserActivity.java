package com.codebuildersapp.release1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class browserActivity extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        String fileData = "file://" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/Documents/Project/";
        webView = findViewById(R.id.web_product);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        String webData = getIntent().getStringExtra("textData");
        setTitle(fileData);
        webView.loadDataWithBaseURL(fileData, webData, "text/html", "UTF-8", "");

    }
}