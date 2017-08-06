package com.example.neo.nougatstudy;

import android.Manifest;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        //ActionBar abar = getSupportActionBar();
        //abar.hide();

        int permissionReceiveSms = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        if (permissionReceiveSms == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "SMS 수신권한 있음.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "SMS 수신권한 없음.", Toast.LENGTH_SHORT).show();

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)){
                Toast.makeText(this, "SMS 수신권한 설명 필요함.", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
            }
        }

        findViewById(R.id.MAIN_BT_LAYOUT).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_SCROLLVIEW).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_SUBSCREEN).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_CALLPHONE).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_COMPONENT).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_SERVICE).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_EVENT).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_TOAST).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_TOAST_INFLATER).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_TOAST_SNACK).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_TOAST_ALERT).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_PROGRESS).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_ANIMATION).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_ANIMATIONTAB).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_FLAGMENT).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_WEBVIEW).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_LISTVIEW).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_SPINNER).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_GRIDVIEW).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_PICKER).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_SCHEDULE).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_CUSTOMVIEW).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_PAINTBOARD).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_THREAD).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_SERVERCALL).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_WEBREQ).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_DATABASE).setOnClickListener(this);
        findViewById(R.id.MAIN_BT_MULTIMEDIA).setOnClickListener(this);

        Log.d("MYLOG", "MainActivity onCreate 호출 됨");
        Intent paddedIntent = getIntent();
        processCommand(paddedIntent);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch(view.getId()){
            case R.id.MAIN_BT_LAYOUT:
                intent = new Intent(getApplicationContext(), LayoutActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_SCROLLVIEW:
                intent = new Intent(getApplicationContext(), ScrollViewActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_SUBSCREEN:
                intent = new Intent(getApplicationContext(), SubScreenActivity.class);
                startActivityForResult(intent, 101);
                break;

            case R.id.MAIN_BT_CALLPHONE:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01074002079"));
                startActivity(intent);
                break;
            case R.id.MAIN_BT_COMPONENT:
                ComponentName name = new ComponentName("com.example.neo.nougatstudy", "com.example.neo.nougatstudy.ComponentActivity");
                intent = new Intent();
                intent.setComponent(name);

                intent.putExtra("data", "name");

                ArrayList<String> names = new ArrayList<String>();
                names.add("김진원");
                names.add("황수연");
                intent.putExtra("names", names);

                SimpleData data = new SimpleData(100, "Hello");
                intent.putExtra("data", data);

                startActivityForResult(intent, 102);
                break;

            case R.id.MAIN_BT_SERVICE:
                intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("command", "show");
                intent.putExtra("name", "소녀시대");
                startService(intent);
                break;

            case R.id.MAIN_BT_EVENT:
                intent = new Intent(getApplicationContext(), EventActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_TOAST:
                Toast toast = Toast.makeText(getApplicationContext(), "위치가 바뀐 토스트", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 200, 200);
                toast.show();
                break;

            case R.id.MAIN_BT_TOAST_INFLATER:
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toastborder, (ViewGroup)findViewById(R.id.toast_layout_root));
                TextView text = layout.findViewById(R.id.text);
                text.setText("모양을 바꾼 토스트 입니다.");
                Toast toast2 = new Toast(getApplicationContext());
                toast2.setGravity(Gravity.CENTER, 0, -100);
                toast2.setDuration(Toast.LENGTH_SHORT);
                toast2.setView(layout);
                toast2.show();
                break;

            case R.id.MAIN_BT_TOAST_SNACK:
                Snackbar.make(view, "스낵바입니다.", Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.MAIN_BT_TOAST_ALERT:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("안내");
                builder.setMessage("종료하시겠습니까?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);

                builder.setPositiveButton("예", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LinearLayout lisearlayout = (LinearLayout)findViewById(R.id.MAIN_LY_LAYOUT);
                        Snackbar.make(lisearlayout, "예 버튼이 눌렸습니다.", Snackbar.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LinearLayout lisearlayout = (LinearLayout)findViewById(R.id.MAIN_LY_LAYOUT);
                        Snackbar.make(lisearlayout, "아니오 버튼이 눌렸습니다.", Snackbar.LENGTH_LONG).show();
                    }
                });

                builder.setNeutralButton("중립버튼", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LinearLayout lisearlayout = (LinearLayout)findViewById(R.id.MAIN_LY_LAYOUT);
                        Snackbar.make(lisearlayout, "중립버튼 버튼이 눌렸습니다.", Snackbar.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertdialog = builder.create();
                alertdialog.show();
                break;

            case R.id.MAIN_BT_PROGRESS:
                intent = new Intent(getApplicationContext(), ProgressActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_ANIMATION:
                intent = new Intent(getApplicationContext(), AnimationActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_ANIMATIONTAB:
                intent = new Intent(getApplicationContext(), AnimationTabActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_FLAGMENT:
                intent = new Intent(getApplicationContext(), FlagmentActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_WEBVIEW:
                intent = new Intent(getApplicationContext(), WebViewActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_LISTVIEW:
                intent = new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_SPINNER:
                intent = new Intent(getApplicationContext(), SpinnerActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_GRIDVIEW:
                intent = new Intent(getApplicationContext(), GridViewActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_PICKER:
                intent = new Intent(getApplicationContext(), PickerActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_SCHEDULE:
                intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_CUSTOMVIEW:
                intent = new Intent(getApplicationContext(), CustomViewActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_PAINTBOARD:
                intent = new Intent(getApplicationContext(), PaintBoardActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_THREAD:
                intent = new Intent(getApplicationContext(), ThreadActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_SERVERCALL:
                ClientThread clientThread = new ClientThread();
                clientThread.start();
                break;

            case R.id.MAIN_BT_WEBREQ:
                intent = new Intent(getApplicationContext(), WebRequestActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_DATABASE:
                intent = new Intent(getApplicationContext(), DatabaseActivity.class);
                startActivity(intent);
                break;

            case R.id.MAIN_BT_MULTIMEDIA:
                intent = new Intent(getApplicationContext(), MultiMediaActivity.class);
                startActivity(intent);
                break;


        }
    }

    class ClientThread extends Thread{
        public void run(){
            try {
                String localhost = "localhost";
                int port = 5001;

                Socket socket = new Socket(localhost, port);
                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                outstream.writeObject("안녕!");
                outstream.flush();
                Log.d("MYLOG", "서버로 보냄!");

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                final Object input = inputStream.readObject();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("MYLOG", "받은 값 : " + input);
                        Toast.makeText(getApplicationContext(), "받은 데이터는 : " + input, Toast.LENGTH_SHORT).show();
                    }
                });

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case 101:
                Toast.makeText(this, "응답코드 : " + resultCode, Toast.LENGTH_SHORT).show();

                String sName = data.getStringExtra("name");
                Toast.makeText(this, "이름은 : " + sName, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("MYLOG", "onNewIntent 호출 됨");
        processCommand(intent);
    }

    private void processCommand(Intent intent){
        if (intent != null) {
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(this, "서비스로 전달 받은 데이터 : " + command + " : " + name, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 1:
                if (grantResults.length > 0) {
                    if( grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                        Toast.makeText(this, "사용자가 SMS 권한 승인 함.", Toast.LENGTH_SHORT).show();
                    } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "사용자가 SMS 권한 거부 함.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "사용자가 SMS 수신 권한을 부여 받지 못함.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curid = item.getItemId();

        switch(curid){
            case R.id.menu_refresh:
                Toast.makeText(this, "새로고침메뉴 클릭됨.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_search:
                Toast.makeText(this, "검색 메뉴 선택됨.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_settings:
                Toast.makeText(this, "셋팅 메뉴 선택됨.", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
