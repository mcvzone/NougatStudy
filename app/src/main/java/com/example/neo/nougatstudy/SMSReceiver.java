package com.example.neo.nougatstudy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
}
