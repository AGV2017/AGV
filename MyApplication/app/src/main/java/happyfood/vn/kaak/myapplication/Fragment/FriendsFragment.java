package happyfood.vn.kaak.myapplication.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import happyfood.vn.kaak.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {
    View view;
    public static FriendsFragment newInstance() {
        FriendsFragment fragment = new FriendsFragment();
        return fragment;
    }

    public FriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null)
            view=inflater.inflate(R.layout.fragment_friends,container,false);
        return view;
    }

}
