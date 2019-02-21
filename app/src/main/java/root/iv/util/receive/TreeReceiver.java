package root.iv.util.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import root.iv.app.App;

public class TreeReceiver extends BroadcastReceiver {
    private static final String TAG = "TreeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        App.logI(TAG + " onReceive");
        if (intent.getAction() != null) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                App.logI("Экран включился");
            }

            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                App.logI("Экран выключился");
            }
        }
    }
}
