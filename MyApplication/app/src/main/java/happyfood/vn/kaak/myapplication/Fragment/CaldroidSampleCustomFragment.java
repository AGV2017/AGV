package happyfood.vn.kaak.myapplication.Fragment;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

import happyfood.vn.kaak.myapplication.Adapter.CaldroidSampleCustomAdapter;

/**
 * Created by MyPC on 26/09/2017.
 */

public class CaldroidSampleCustomFragment extends CaldroidFragment {
    @Override
    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        return new CaldroidSampleCustomAdapter(getActivity(), month, year, getCaldroidData(), extraData);
    }
}
