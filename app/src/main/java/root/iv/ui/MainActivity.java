package root.iv.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import root.iv.R;

public class MainActivity extends AppCompatActivity {
    private int count = 0;

    private TextView viewCount;
    private ViewGroup backgroundViewAlerts;

    @OnClick(R.id.button)
    public void clickButton() {
        count++;
        updateAlertWarnings();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_warning:
                count = 0;
                updateAlertWarnings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.main_menu_warning);
        View view = item.getActionView();

        viewCount = view.findViewById(R.id.viewCountWarnings);
        backgroundViewAlerts = view.findViewById(R.id.backgroundViewAlerts);
        updateAlertWarnings();

        view.setOnClickListener(v -> onOptionsItemSelected(item));

        return super.onPrepareOptionsMenu(menu);
    }

    private void updateAlertWarnings() {
        viewCount.setText(String.valueOf(count));
        backgroundViewAlerts.setVisibility(count != 0 ? View.VISIBLE : View.INVISIBLE);
    }
}
