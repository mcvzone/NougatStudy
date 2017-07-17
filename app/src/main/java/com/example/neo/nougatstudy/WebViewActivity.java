package com.example.neo.nougatstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        wv = (WebView)findViewById(R.id.WEBVIEW_WV_CONTAINER);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);

        findViewById(R.id.WEBVIEW_BT_WEB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv.loadUrl("file:///android_asset/sam.html");
            }
        });
    }
}
