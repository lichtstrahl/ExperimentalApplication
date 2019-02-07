package root.iv.ui.fragment.util;

import android.app.Fragment;
import android.app.FragmentManager;

import androidx.legacy.app.FragmentPagerAdapter;


public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentsProvider.getFragment(position);
    }

    @Override
    public int getCount() {
        return FragmentsProvider.getCount();
    }
}
