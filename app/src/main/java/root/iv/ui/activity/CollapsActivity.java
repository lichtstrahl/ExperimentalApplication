package root.iv.ui.activity;

import android.os.Bundle;
import android.view.View;

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
        appBarLayout.addOnOffsetChangedListener((appBar, offset) -> {
            int totalRange = appBar.getTotalScrollRange();
            float a = Math.abs(offset/(float)totalRange);
            shadowView.setAlpha(a*a*a*a);
        });
    }
}
