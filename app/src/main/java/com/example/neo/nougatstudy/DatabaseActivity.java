package com.example.neo.nougatstudy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DatabaseActivity extends AppCompatActivity {
    EditText databaseText, databaseText2;
    EditText databaseText3, databaseText4, databaseText5;
    TextView databaseView;
    SQLiteDatabase database;
    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        databaseView = (TextView) findViewById(R.id.databaseView);

        databaseText = (EditText)findViewById(R.id.databaseText);

        databaseText2 = (EditText)findViewById(R.id.databaseText2);

        databaseText3 = (EditText)findViewById(R.id.databaseText3);
        databaseText4 = (EditText)findViewById(R.id.databaseText4);
        databaseText5 = (EditText)findViewById(R.id.databaseText5);

        tableName = databaseText2.getText().toString();

        findViewById(R.id.databaseBtn).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String databaseName = databaseText.getText().toString();
                openDataBase(databaseName);
            }
        });

        findViewById(R.id.databaseBtn2).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                createTable(tableName);
            }
        });

        findViewById(R.id.databaseBtn3).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String name = databaseText3.getText().toString();
                String mobile = databaseText5.getText().toString();
                int age = Integer.parseInt(databaseText4.getText().toString().trim());

                insertTable(name, age, mobile);
            }
        });

        findViewById(R.id.databaseBtn4).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String name = databaseText3.getText().toString();
                String mobile = databaseText5.getText().toString();
                int age = Integer.parseInt(databaseText4.getText().toString().trim());

                selectTable();
            }
        });

    }

    public void openDataBase(String databaseName){
        println("openDataBase 호출 됨.");
        /*
        database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);   //mode_private ? 보안상이라는데 뭐지?
        if (database != null) {
            println("데이터 베이스 오픈 완료.");
        }*/

        DatabaseHelper databaseHelper = new DatabaseHelper(this, databaseName, null, 2);
        database = databaseHelper.getWritableDatabase();

    }

    public void createTable(String tableName){
        println("createTable 호출 됨.");

        if( database != null ){
            StringBuffer sql = new StringBuffer();
            sql.append("create table " + tableName + "(_id integer primary key autoincrement , name text, age integer, mobile text)");
            database.execSQL(sql.toString());

            println("테이블 생성됨.");
        } else {
            println("테이블을 먼저 생성 하세요.");
        }
    }

    public void insertTable(String name, int age, String mobile){

        if (database != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("insert into "+tableName+"(name, age, mobile) values (?, ?, ?)");
            Object[] params = {name, age, mobile};

            database.execSQL(sql.toString(), params);

            println("데이터 추가 함.");
        } else {
            println("데이터 베이스 확인 하세요.");
        }
    }

    public void selectTable(){
        println("데이터 조회하기.");
        if (database != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select _id, name, age, mobile from " + tableName);

            Cursor cursor = database.rawQuery(sql.toString(), null);

            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String mobile = cursor.getString(3);

                println(id + " - " + name + "_" + age + "_" + mobile);
            }

            cursor.close();
        }
    }

    public void println(String data){
        databaseView.append(data + "\n");
    }

    class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            StringBuffer sql = new StringBuffer();
            sql.append("create table if not exists customer(_id integer primary key autoincrement , name text, age integer, mobile text)");
            sqLiteDatabase.execSQL(sql.toString());

            println("테이블 생성됨.");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            println("onUpgrade 호출됨.");

            if( newVersion > 1 ){
                sqLiteDatabase.execSQL("drop table if exists " + "customer");
            }
        }
    }
}
