package org.dongri.donpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.sql.Timestamp;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private String payUrl = "https://pay.hackerth.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        String campaignID = intent.getStringExtra( "campaign_id" );

        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //payUrl = payUrl + "/webview/campaign?id=" + campaignID + "&time=" + timestamp;

        payUrl = payUrl + "/webview/campaign?id=" + campaignID;

        webView.loadUrl(payUrl);
    }

}