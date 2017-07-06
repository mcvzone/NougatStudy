package com.example.neo.nougatstudy;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
