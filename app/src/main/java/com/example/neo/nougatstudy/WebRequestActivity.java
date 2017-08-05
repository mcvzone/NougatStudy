package com.example.neo.nougatstudy;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebRequestActivity extends AppCompatActivity {

    EditText webRequestText;
    TextView webReqTextView;
    Handler handler;

    String urlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_request);

        webReqTextView = (TextView)findViewById(R.id.webReqTextView);
        webRequestText = (EditText)findViewById(R.id.webRequestText);

        handler = new Handler();

        findViewById(R.id.webReqeustBtn).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                urlString = webRequestText.getText().toString();

                RequestThread requestThread = new RequestThread();
                requestThread.start();
            }
        });
    }

    class RequestThread extends Thread{
        public void run(){
            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                if (conn != null) {
                    conn.setConnectTimeout(10000);//mil 10초동안 응답없으면 끝
                    conn.setRequestMethod("GET");//get 방식
                    conn.setDoInput(true);//서버에서 받기
                    conn.setDoOutput(true);//서버로 보내기
                    int resCode = conn.getResponseCode();//이때 서버로 연결 시도

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));//한줄씩 읽어 들일 수 있는 객체 BufferedReader
                    String line = null;

                    while(true){
                        line = reader.readLine();

                        if (line == null) {
                            break;
                        }

                        println(line);
                    }

                    reader.close();
                    conn.disconnect();
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                webReqTextView.append(data + "\n");
            }
        });
    }
}
