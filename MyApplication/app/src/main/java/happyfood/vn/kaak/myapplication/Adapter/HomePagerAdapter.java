package happyfood.vn.kaak.myapplication.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import happyfood.vn.kaak.myapplication.Fragment.FriendsFragment;
import happyfood.vn.kaak.myapplication.Fragment.MapFragment;
import happyfood.vn.kaak.myapplication.Fragment.MoneyManagementFragment;
import happyfood.vn.kaak.myapplication.Fragment.ScanFragment;
import happyfood.vn.kaak.myapplication.Fragment.SettingsFragment;

/**
 * Created by MyPC on 25/09/2017.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS=5;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return MapFragment.newInstance();
            case 1: return ScanFragment.newInstance();
            case 2: return FriendsFragment.newInstance();
            case 3: return MoneyManagementFragment.newInstance();
            case 4: return SettingsFragment.newInstance();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
