package com.example.neo.nougatstudy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("MYLOG", "MyService onCreate 실행됨");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("MYLOG", "onStartCommand 실행됨");

        if (intent == null) {
            return Service.START_STICKY;    //서비스가 종료 된 경우 다시 실행 하라.
        } else {
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent){
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Toast.makeText(getApplicationContext(), "command / name : " + command + "/"+ name, Toast.LENGTH_SHORT).show();

        try{
            Thread.sleep(5000);
        } catch(Exception e){

        }
        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", name + " from service.");
        startActivity(showIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
