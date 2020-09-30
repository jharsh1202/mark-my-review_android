package com.example.markmyreview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.net.URL;

public class FillFeedback extends AppCompatActivity {
    WebView webView;
    Intent intent;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_feedback);
        progressBar=findViewById(R.id.progressBar);
        intent=getIntent();
        String url= intent.getStringExtra("url");
        //Log.i("url",url);
        webView= findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);




        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
                handler.proceed();
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //to hide progress bar when finish loading page
                progressBar.setVisibility(View.GONE);
                MainActivity.editedUrl=webView.getUrl();
                Log.i("pageloaded",MainActivity.editedUrl);
            }
        });


        webView.loadUrl(url);

    }
}