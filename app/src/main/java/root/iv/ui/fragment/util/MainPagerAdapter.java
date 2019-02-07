package root.iv.ui.fragment.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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
