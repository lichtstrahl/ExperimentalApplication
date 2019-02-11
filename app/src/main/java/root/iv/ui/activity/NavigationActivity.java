package root.iv.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.R;

public class NavigationActivity extends AppCompatActivity {
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        bottomNavigation.setOnNavigationItemSelectedListener(this::bottomNavigationItemSelected);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNavigationCamera:
                Toast.makeText(this, "Camera", Toast.LENGTH_LONG).show();
                break;
            case R.id.menuNavigationSearch:
                Toast.makeText(this, "Search", Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }

    public boolean bottomNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottomCamera:
                Toast.makeText(this, "Camera", Toast.LENGTH_LONG).show();
                break;

            case R.id.bottomSearch:
                Toast.makeText(this, "Search", Toast.LENGTH_LONG).show();
                break;
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START, true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
