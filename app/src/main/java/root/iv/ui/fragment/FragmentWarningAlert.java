package root.iv.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.OnClick;
import root.iv.R;

public class FragmentWarningAlert extends Fragment {
    private int count = 0;

    private TextView viewCount;
    private ViewGroup backgroundViewAlerts;

    @OnClick(R.id.button)
    public void clickButton() {
        count++;
        updateAlertWarnings();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_warning_alert, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        return view;
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.main_menu_warning);
        View view = item.getActionView();

        viewCount = view.findViewById(R.id.viewCountWarnings);
        backgroundViewAlerts = view.findViewById(R.id.backgroundViewAlerts);
        updateAlertWarnings();

        view.setOnClickListener(v -> onOptionsItemSelected(item));
    }

    private void updateAlertWarnings() {
        viewCount.setText(String.valueOf(count));
        backgroundViewAlerts.setVisibility(count != 0 ? View.VISIBLE : View.INVISIBLE);
    }

    public static FragmentWarningAlert getInstance() {
        return new FragmentWarningAlert();
    }
}