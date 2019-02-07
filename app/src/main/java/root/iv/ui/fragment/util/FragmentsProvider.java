package root.iv.ui.fragment.util;

import androidx.fragment.app.Fragment;
import root.iv.ui.fragment.FragmentCanvas;
import root.iv.ui.fragment.FragmentWarningAlert;

public class FragmentsProvider {
    private static final int COUNT_FRAGMENTS = 2;
    private static final Fragment[] fragments = new Fragment[] {
            FragmentWarningAlert.getInstance(),
            FragmentCanvas.getInstance()
    };

    public static Fragment getFragment(int i) {
        return fragments[i];
    }

    public static int getCount() {
        return COUNT_FRAGMENTS;
    }
}
