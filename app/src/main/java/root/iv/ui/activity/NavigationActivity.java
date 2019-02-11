package root.iv.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.R;

public class NavigationActivity extends AppCompatActivity {
    @BindView(R.id.navigationViewRight)
    NavigationView navigationViewRight;
    @BindView(R.id.navigationViewLeft)
    NavigationView navigationViewLeft;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;
    @BindView(R.id.bottomAppBar)
    BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        navigationViewRight.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        navigationViewLeft.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        bottomNavigation.setOnNavigationItemSelectedListener(this::bottomNavigationItemSelected);

        setSupportActionBar(bottomAppBar);
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
            case R.id.menuNavigationCamera:
                Toast.makeText(this, "Camera", Toast.LENGTH_LONG).show();
                break;

            case R.id.menuNavigationSearch:
                Toast.makeText(this, "Search", Toast.LENGTH_LONG).show();
                break;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(navigationViewRight, true);
                return true;
            case R.id.menuNavigationCamera:
                Toast.makeText(this, "Camera", Toast.LENGTH_LONG).show();
                return true;

            case R.id.menuNavigationSearch:
                Toast.makeText(this, "Search", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(navigationViewRight)) {
            drawerLayout.closeDrawer(navigationViewRight, true);
        } else if (drawerLayout.isDrawerVisible(navigationViewLeft)) {
            drawerLayout.closeDrawer(navigationViewLeft, true);
        } else {
            super.onBackPressed();
        }
    }
}
