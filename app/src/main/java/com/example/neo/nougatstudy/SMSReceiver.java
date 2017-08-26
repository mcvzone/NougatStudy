package com.example.neo.nougatstudy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MYLOG", "SMSReceiver onReceive 호출됨.");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if( messages.length > 0 ){
            String sender = messages[0].getOriginatingAddress();
            Log.d("MYLOG", "sender : " + sender );

            String msg = messages[0].getMessageBody().toString();
            Log.d("MYLOG", "msg : " + msg);

            Date received = new Date(messages[0].getTimestampMillis());
            Log.d("MYLOG", "date : " + received);

            sendToActivity(context, sender, msg, received);
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs = (Object[])bundle.get("pdus"); //smtp 프로토콜 안에 pdus 값으로 sms 값들이 들어가있다.
        SmsMessage[] messages = new SmsMessage[objs.length];

        for( int i=0; i<objs.length; i++ ){
            String format = bundle.getString("format");
            Log.d("MYLOG", "format : " + format);

            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i]);
            }
        }
        return messages;
    }

    private void sendToActivity(Context context, String sender, String msg, Date received){
        Intent intent = new Intent(context, SmsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender", sender);
        intent.putExtra("msg", msg);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        intent.putExtra("received", format.format(received));
        context.startActivity(intent);
    }

    {   /** 아래  SMSList 와 SMSDelete 를 사용 하기 위해선, 아래 권한을 요청 해야 함.
        //SMS Confirm
        permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);

        if (!(permission == PackageManager.PERMISSION_GRANTED)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
                //Toast.makeText(this, "SMS 수신권한 설명 필요함.", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 1);
            }
        }
        */
    }

    public static final String MESSAGE_TYPE_INBOX = "1";
    public static final String MESSAGE_TYPE_SENT = "2";
    public static final String MESSAGE_TYPE_CONVERSATIONS = "3";
    public static final String MESSAGE_TYPE_NEW = "new";

    public void SMSList(Context context) {

        // Retrieve All SMS
        /*
            Inbox = "content://sms/inbox"
            Failed = "content://sms/failed"
            Queued = "content://sms/queued"
            Sent = "content://sms/sent"
            Draft = "content://sms/draft"
            Outbox = "content://sms/outbox"
            Undelivered = "content://sms/undelivered"
            All = "content://sms/all"
            Conversations = "content://sms/conversations"

            addressCol= mCurSms.getColumnIndex("address");
            personCol= mCurSms.getColumnIndex("person");
            dateCol = mCurSms.getColumnIndex("date");
            protocolCol= mCurSms.getColumnIndex("protocol");
            readCol = mCurSms.getColumnIndex("read");
            statusCol = mCurSms.getColumnIndex("status");
            typeCol = mCurSms.getColumnIndex("type");
            subjectCol = mCurSms.getColumnIndex("subject");
            bodyCol = mCurSms.getColumnIndex("body");
         */
        Uri allMessage = Uri.parse("content://sms/");
        Cursor cur = context.getContentResolver().query(allMessage, null, null, null, null);
        int count = cur.getCount();
        Log.d("MYLOG", "SMS count = " + count);
        String row = "";
        String msg = "";
        String date = "";
        String protocol = "";
        while (cur.moveToNext()) {
            row = cur.getString(cur.getColumnIndex("address"));
            msg = cur.getString(cur.getColumnIndex("body"));
            date = cur.getString(cur.getColumnIndex("date"));
            protocol = cur.getString(cur.getColumnIndex("protocol"));

            String type = "";
            if (protocol == MESSAGE_TYPE_SENT) type = "sent";
            else if (protocol == MESSAGE_TYPE_INBOX) type = "receive";
            else if (protocol == MESSAGE_TYPE_CONVERSATIONS) type = "conversations";
            else if (protocol == null) type = "send";

            Log.d("MYLOG", "SMS Phone: " + row + " / Mesg: " + msg + " / Type: " + type + " / Date: " + date);
        }
    }

    public void SMSDelete(Context context) {
        Uri deleteUri = Uri.parse("content://sms");
        int count = 0;
        Cursor c = context.getContentResolver().query(deleteUri, null, null, null, null);
        while (c.moveToNext()) {
            try {
                // Delete the SMS
                String pid = c.getString(0);
                // Get id;
                String uri = "content://sms/" + pid;
                // count = this.getContentResolver().delete(Uri.parse(uri),null, null);
            } catch (Exception e) {
            }
        }
    }
}
