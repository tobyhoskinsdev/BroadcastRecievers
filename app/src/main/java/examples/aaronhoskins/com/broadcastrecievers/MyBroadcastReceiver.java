package examples.aaronhoskins.com.broadcastrecievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String passedValue = intent.getStringExtra("key");
        Toast.makeText(context, "RECIEVING INFO = " + passedValue, Toast.LENGTH_LONG).show();
        Log.d("TAG", "onReceive: " + passedValue);
    }
}
