package root.iv.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import root.iv.R;

import android.os.Bundle;

public class GUIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui);
        ButterKnife.bind(this);
    }
}
