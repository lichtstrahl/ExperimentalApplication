package root.iv.ui.tab;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import androidx.viewpager.widget.PagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.R;

public class TabHolder implements SmartTabLayout.TabProvider {
    @BindView(R.id.tabIcon)
    ImageView tabIcon;

    public View getView(int pos) {
        return null;
    }

    private static final int[] iconsID = new int[] {
            R.drawable.ic_home,
            R.drawable.ic_search
    };

    @Override
    public View createTabView(ViewGroup viewGroup, int i, PagerAdapter pagerAdapter) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View tab = inflater.inflate(R.layout.tab_main, viewGroup);
        ButterKnife.bind(this, tab);

        tabIcon.setImageResource(iconsID[i]);

        return tab;
    }
}
