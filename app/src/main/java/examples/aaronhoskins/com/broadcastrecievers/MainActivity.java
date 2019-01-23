package examples.aaronhoskins.com.broadcastrecievers;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends BaseActivity {
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
        sendBroadcast(intent);

        IntentFilter intentFilter1 = new IntentFilter("android.intent.action.AIRPLANE_MODE");

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("AirplaneMode", "Service state changed");
                if(isAirplaneModeOn(context)) {
                    Log.d("TAG", "onReceive: AIRPLANE MODE IS ON");
                } else {
                    Log.d("TAG", "onReceive: AIRPLANE MODE IS OFF");
                }
            }
        };

        this.registerReceiver(receiver, intentFilter1);
        }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }



}
