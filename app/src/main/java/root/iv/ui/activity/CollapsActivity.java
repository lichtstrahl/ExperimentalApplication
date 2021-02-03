package root.iv.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.R;

public class CollapsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.shadowView)
    View shadowView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaps_mini);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        appBarLayout.addOnOffsetChangedListener((appBar, offset) -> {
            int totalRange = appBar.getTotalScrollRange();
            float a = Math.abs(offset/(float)totalRange);
            shadowView.setAlpha(a*a*a*a);
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            onOptionsItemSelected(item);
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.warning_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem itemWarning = menu.findItem(R.id.menuWarning);
        updateVisibleAlert(itemWarning);
        itemWarning.getActionView().setOnClickListener(v -> {
            Toast.makeText(this, "Warning", Toast.LENGTH_SHORT).show();
            count++;
            updateVisibleAlert(itemWarning);
        });

        return true;
    }

    private void updateVisibleAlert(MenuItem item) {
        ImageView viewAlert = item.getActionView().findViewById(R.id.viewAlert);
        TextView viewCount = item.getActionView().findViewById(R.id.viewAlertCount);
        viewAlert.setVisibility(count != 0 ? View.VISIBLE : View.GONE);
        viewCount.setVisibility(count != 0 ? View.VISIBLE : View.GONE);
        viewCount.setText(String.valueOf(count));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNavigationCamera:
                Toast.makeText(this, "Камера", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuNavigationSearch:
                Toast.makeText(this, "Поиск", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START, true);
                break;
        }
        return true;
    }


}
