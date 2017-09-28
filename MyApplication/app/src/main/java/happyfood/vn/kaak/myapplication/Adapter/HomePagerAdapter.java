package happyfood.vn.kaak.myapplication.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import happyfood.vn.kaak.myapplication.Fragment.FriendsFragment;
import happyfood.vn.kaak.myapplication.Fragment.MapFragment;
import happyfood.vn.kaak.myapplication.Fragment.MoneyManagementFragment;
import happyfood.vn.kaak.myapplication.Fragment.ScanFragment;
import happyfood.vn.kaak.myapplication.Fragment.SettingsFragment;
import happyfood.vn.kaak.myapplication.Activity.HomeActivity;
/**
 * Created by MyPC on 25/09/2017.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_ITEMS=5;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case HomeActivity.FIND_TAB:
                return MapFragment.newInstance();
            case HomeActivity.SCAN_QR_CODE_TAB:
                return ScanFragment.newInstance();
            case HomeActivity.FRIENDS_TAB:
                return FriendsFragment.newInstance();
            case HomeActivity.MONEY_MANAGEMENT_TAB:
                return MoneyManagementFragment.newInstance();
            case HomeActivity.MORE_TAB:
                return SettingsFragment.newInstance();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
