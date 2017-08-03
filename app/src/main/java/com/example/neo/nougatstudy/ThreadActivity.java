package com.example.neo.nougatstudy;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ThreadActivity extends AppCompatActivity {

    TextView threadTextView;
    EditText threadEditText;

    ProgressBar threadProgressbar;

    ValueHandler valueHandler = new ValueHandler();
    CompletionThread completionThread;
    Handler handler = new Handler();

    String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        threadTextView = (TextView) findViewById(R.id.threadText);
        threadProgressbar = (ProgressBar)findViewById(R.id.threadProgressbar);
        threadEditText = (EditText)findViewById(R.id.threadEditText);
        completionThread = new CompletionThread();
        completionThread.start();

        findViewById(R.id.threadBtnStart).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //BackgroundThread backgroundThread = new BackgroundThread();
                //backgroundThread.start();

                new Thread(new Runnable(){
                    boolean isStart = true;
                    int count = 0;

                    @Override
                    public void run() {
                        while(isStart){

                            handler.post(new Runnable(){
                                public void run(){
                                    threadTextView.setText(""+count);
                                }
                            });

                            try {
                                Thread.sleep(1000);
                                count++;
                            }catch (Exception e){}
                        }
                    }
                }).start();
            }
        });

        findViewById(R.id.threadBtnProgressbar).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                threadProgressbar.setMax(100);

                int delaySec = Integer.parseInt(threadEditText.getText().toString());
                threadTextView.setText(delaySec + "초 후에 시작 됩니다.");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new Thread(new Runnable() {
                            int delay = 0;
                            int count =0;

                            @Override
                            public void run() {

                                while(true){
                                    count++;

                                    if( count > 100 ){
                                        break;
                                    }

                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            threadProgressbar.setProgress(count);
                                        }
                                    });

                                    try {
                                        Thread.sleep(100);
                                    }catch (Exception e){}
                                }

                                completionThread.completionHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        msg = "ok";
                                        Log.d("MYLOG", "메시지 : " + msg);
                                    }
                                });
                            }
                        }).start();
                    }
                }, delaySec*1000);
            }
        });

        findViewById(R.id.threadBtnAsyncTask).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ProgressTask task = new ProgressTask();
                task.execute("시작");
            }
        });
    }

    class ProgressTask extends AsyncTask<String, Integer, Integer> {
        int value = 0;

        @Override
        protected Integer doInBackground(String... strings) {
            /** 스레드의 내용을 기술한다. */
            //여기서 리턴 되는 값을 onPostExecute 전달 받는다.
            while(true){

                if( value > 100 ){
                    break;
                }

                value++;

                publishProgress(value); //doInBackground publishProgress를 호출 하면 onProgressUpdate 이 호출 된다..

                try {
                    Thread.sleep(100);
                }catch (Exception e){}
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Toast.makeText(getApplicationContext(), "완료", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //핸들러 실행 하듯이 UI 를 UPDATE 하는것과 같은 효과가 나타난다.
            threadProgressbar.setProgress(values[0].intValue());
        }
    }


    class BackgroundThread extends Thread{
        boolean isStart = true;
        public void run(){
            int count = 0;
            while(isStart){

                try {
                    Thread.sleep(1000);
                    count++;

                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putInt("value", count);
                    message.setData(bundle);
                    valueHandler.sendMessage(message);
                }catch (Exception e){

                }
            }

        }
    }


    class ValueHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            threadTextView.setText(""+value);
        }
    }

    class CompletionThread extends Thread{
        public Handler completionHandler = new Handler();

        public void run(){
            Log.d("MYLOG", "스레드가 대기 상태로 들어 갑니다.");
            /** 스레드가 계속 대기 상태로 동작 되도록 할수 있다. */
            Looper.prepare();
            Looper.loop();
        }
    }
}
