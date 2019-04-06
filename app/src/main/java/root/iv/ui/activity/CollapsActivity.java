package root.iv.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaps_mini);
        ButterKnife.bind(this);
//
//
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        appBarLayout.addOnOffsetChangedListener((appBar, offset) -> {
            int totalRange = appBar.getTotalScrollRange();
            float a = Math.abs(offset/(float)totalRange);
            shadowView.setAlpha(a*a*a*a);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu, menu);
        return true;
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
                Toast.makeText(this, "Меню", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


}
