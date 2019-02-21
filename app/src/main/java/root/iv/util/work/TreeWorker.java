package root.iv.util.work;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import root.iv.R;
import root.iv.app.App;
import root.iv.ui.activity.DateTimeWorkActivity;

public class TreeWorker extends Worker {
    private static final String INPUT_MSG = "input:msg";
    public static final int NOTIFICATION_ID = 0;
    private static final String TAG = "tree-worker";

    public TreeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public static void start(AppCompatActivity activity, long delay, String msg) {
        Data data = new Data.Builder()
                .putString(INPUT_MSG, msg)
                .build();
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(TreeWorker.class)
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .setInputData(data)
                .build();

        WorkManager.getInstance().enqueue(request);
        WorkManager.getInstance().getWorkInfoByIdLiveData(request.getId()).observe(activity, state -> {
            App.logI("Status: " + state.getState().toString());
        });
    }

    @NonNull
    @Override
    public Result doWork() {
        App.logI(TAG + " start");
        createNotification(getInputData().getString(INPUT_MSG));
        App.logI(TAG + " finish");
        return Worker.Result.failure();
    }

    private void createNotification(String title) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(title);
        Intent intent = new Intent(getApplicationContext(), DateTimeWorkActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
        stackBuilder.addParentStack(DateTimeWorkActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
}
