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
public class MoneyManagementFragment extends Fragment {
    View view;
    public static MoneyManagementFragment newInstance(){
        MoneyManagementFragment moneyManagementFragment=new MoneyManagementFragment();
        return moneyManagementFragment;
    }

    public MoneyManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null)
            view=inflater.inflate(R.layout.fragment_money_management,container,false);
        return view;
    }

}
