package root.iv.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import root.iv.app.App;

public class TreeWorker extends Worker {
    static final String TAG = "tree-worker";

    public TreeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        App.logI(TAG + "start");

        App.logI(TAG + "finish");
        return Worker.Result.failure();
    }
}
