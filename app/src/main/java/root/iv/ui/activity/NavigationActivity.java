package root.iv.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class NavigationActivity extends AppCompatActivity {
    @BindView(R.id.navigationView)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
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
}
