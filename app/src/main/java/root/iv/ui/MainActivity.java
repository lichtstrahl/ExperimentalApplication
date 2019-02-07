package root.iv.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.R;
import root.iv.ui.fragment.FragmentCanvas;
import root.iv.ui.fragment.FragmentWarningAlert;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tabLayout)
    SmartTabLayout viewPagerTab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private static final int[] iconsID = new int[] {
            R.drawable.ic_canvas,
            R.drawable.ic_warning
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPagerTab.setCustomTabView(this::createTabView);

        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("Canvas", FragmentCanvas.class));
        pages.add(FragmentPagerItem.of("Alert", FragmentWarningAlert.class));

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(), pages);

        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        Resources res = container.getContext().getResources();
        View tab = inflater.inflate(R.layout.tab_main, container, false);

        ImageView image = tab.findViewById(R.id.tabIcon);
        image.setImageResource(iconsID[position]);

        return tab;
    }
}
