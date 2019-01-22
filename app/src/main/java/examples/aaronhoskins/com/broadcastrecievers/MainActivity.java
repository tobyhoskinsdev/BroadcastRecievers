package examples.aaronhoskins.com.broadcastrecievers;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBroadcastReceiver = new MyBroadcastReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("examples.aaronhoskins.com.broadcastrecievers.main_broadcast");

        registerReceiver(myBroadcastReceiver, intentFilter,"android.permission.INTERNET",null);

        Intent intent = new Intent();
        intent.setAction("examples.aaronhoskins.com.broadcastrecievers.main_broadcast");
        intent.putExtra("key", "Can we go and get some darn sleep already");
        Log.d("TAG", "onCreate: SENDING BROADCAST");
        sendBroadcast(intent, "android.permission.INTERNET");

        }



}
