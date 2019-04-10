package root.iv.ui.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import root.iv.R;
import root.iv.app.App;
import root.iv.util.receive.TreeReceiver;
import root.iv.util.service.TreeService;
import root.iv.util.work.TreeWorker;

public class DateTimeWorkActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private static final String TAG_DATE_DIALOG = "dialog:date";
    private static final String TAG_TIME_DIALOG = "dialog:time";
    private Calendar date;
    @BindView(R.id.viewResult)
    TextView viewResult;
    private TreeReceiver receiver = null;

    @OnClick(R.id.buttonSelectDate)
    public void clickSelectDate() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dialog = DatePickerDialog.newInstance(
          DateTimeWorkActivity.this,
          now.get(Calendar.YEAR),
          now.get(Calendar.MONTH),
          now.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show(getSupportFragmentManager(), TAG_DATE_DIALOG);
        dialog.setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_work);
        ButterKnife.bind(this);

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(TreeWorker.NOTIFICATION_ID);

        Intent background = new Intent(getApplicationContext(), TreeService.class);
        startService(background);

        App.logI("Служба запушена");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        App.logI(String.format(Locale.ENGLISH, "(%d %d %d)", year, monthOfYear, dayOfMonth));

        TimePickerDialog dialog = TimePickerDialog.newInstance(this, true);
        dialog.show(getSupportFragmentManager(), TAG_TIME_DIALOG);
        dialog.setCancelable(false);

        date = Calendar.getInstance();
        date.set(year, monthOfYear, dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        date.set(Calendar.HOUR, hourOfDay);
        date.set(Calendar.MINUTE, minute);
        date.set(Calendar.SECOND, second);

        long timeStart = date.getTimeInMillis();
        long timeNow = Calendar.getInstance().getTimeInMillis();

        viewResult.setText(date.getTime().toString());
        TreeWorker.start(this, timeStart-timeNow, "Сообщение");
    }
}
