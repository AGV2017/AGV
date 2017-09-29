package happyfood.vn.kaak.myapplication.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import happyfood.vn.kaak.myapplication.Activity.HomeActivity;
import happyfood.vn.kaak.myapplication.ActivityLikeDialog.AddSpentMoneyDialog;
import happyfood.vn.kaak.myapplication.Adapter.ListThuChiAdapter;
import happyfood.vn.kaak.myapplication.Model.SpentMoney;
import happyfood.vn.kaak.myapplication.R;

import static happyfood.vn.kaak.myapplication.Activity.HomeActivity.REQUEST_FOR_ADD_SPENT_MONEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoneyManagementFragment extends Fragment {
    View view;

    TextView tvTotalMoney;
    ImageButton ibtnAdd;
    //CaldroidFragment caldroidFragment;
    final CaldroidSampleCustomFragment caldroidFragment = new CaldroidSampleCustomFragment();
    ListView lstDetail;
    ArrayList<SpentMoney> arrayDetail=new ArrayList<>();
    ListThuChiAdapter adapter;
    Calendar cal = Calendar.getInstance();
    final SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
    HashMap<Date,ArrayList<SpentMoney>> data=new HashMap<>();

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
        {
            view=inflater.inflate(R.layout.fragment_money_management,container,false);
            addFakeData();
            //caldroidFragment = new CaldroidFragment();
            lstDetail=(ListView)view.findViewById(R.id.lstDetail);
            adapter=new ListThuChiAdapter(getContext(),arrayDetail);
            lstDetail.setAdapter(adapter);

            Bundle args = new Bundle();

            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            caldroidFragment.setArguments(args);

            FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
            t.replace(R.id.layout_calendar, caldroidFragment);
            t.commit();

            Map<String, Object> extraData = caldroidFragment.getExtraData();
            extraData.put("NGUYENPHUC",data);
            caldroidFragment.refreshView();

            caldroidFragment.setCaldroidListener(listener);

            tvTotalMoney=(TextView)view.findViewById(R.id.tvTongThuChi);
            tvTotalMoney.setText(tinhTongChi()+"/8.000");

            ibtnAdd=(ImageButton) view.findViewById(R.id.ibtnAdd);
            ibtnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), AddSpentMoneyDialog.class);
                    startActivityForResult(intent, REQUEST_FOR_ADD_SPENT_MONEY);
                }
            });
        }
        return view;
    }
    final CaldroidListener listener = new CaldroidListener() {
        View viewCu=null;
        @Override
        public void onSelectDate(Date date, View view) {
            if(viewCu!=null)
                viewCu.setBackgroundColor(Color.WHITE);
            viewCu=view;
            view.setBackgroundColor(Color.GRAY);
            arrayDetail.clear();
            ArrayList<SpentMoney> dsChiTrongNgay=new ArrayList<>();
            dsChiTrongNgay=data.get(date);
            if(dsChiTrongNgay!=null)
                for(int i=0;i<dsChiTrongNgay.size();i++){
                    SpentMoney tungDongSpentMoney =dsChiTrongNgay.get(i);
                    arrayDetail.add(tungDongSpentMoney);
                }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onChangeMonth(int month, int year) {
        }

        @Override
        public void onLongClickDate(Date date, View view) {

        }

        @Override
        public void onCaldroidViewCreated() {

        }

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==AddSpentMoneyDialog.RESULT_CODE){
            Bundle dataResult=data.getBundleExtra("DATA");
            //When user click cancel
            if(dataResult.getInt("RESULT")==AddSpentMoneyDialog.RESULT_CANCEL){
                Toast.makeText(getContext(),"Bên kia mới bấm cancel",Toast.LENGTH_SHORT).show();
            }
            else {
                String content=dataResult.getString("CONTENT");
                int cost=dataResult.getInt("COST");
                int loai=dataResult.getInt("TYPE");
                if(loai==AddSpentMoneyDialog.GET_MONEY)
                    Toast.makeText(getContext(),"Mới thu vào "+content+" số tiền: "+cost,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(),"Mới chi ra "+content+" số tiền: "+cost,Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addFakeData(){
        ArrayList<SpentMoney> dsSpentMoney =new ArrayList<>();
        SpentMoney thongTinChi=new SpentMoney("Ăn sáng",25);
        dsSpentMoney.add(thongTinChi);

        thongTinChi=new SpentMoney("Ăn trưa",25);
        dsSpentMoney.add(thongTinChi);

        thongTinChi=new SpentMoney("Ăn tối",30);
        dsSpentMoney.add(thongTinChi);

        data.put(new GregorianCalendar(2017, Calendar.SEPTEMBER, 27).getTime(), dsSpentMoney);
        //-----------------------
        dsSpentMoney =new ArrayList<>();
        thongTinChi=new SpentMoney("Ăn sáng",20);
        dsSpentMoney.add(thongTinChi);

        thongTinChi=new SpentMoney("cà phê",15);
        dsSpentMoney.add(thongTinChi);

        thongTinChi=new SpentMoney("Ăn trưa",25);
        dsSpentMoney.add(thongTinChi);

        thongTinChi=new SpentMoney("Ăn tối",25);
        dsSpentMoney.add(thongTinChi);

        data.put(new GregorianCalendar(2017, Calendar.SEPTEMBER, 26).getTime(), dsSpentMoney);
        //-----------------------
        dsSpentMoney =new ArrayList<>();
        thongTinChi=new SpentMoney("Ăn cà lem",20);
        dsSpentMoney.add(thongTinChi);

        thongTinChi=new SpentMoney("Ăn trưa",25);
        dsSpentMoney.add(thongTinChi);

        thongTinChi=new SpentMoney("Ăn tối",30);
        dsSpentMoney.add(thongTinChi);

        thongTinChi=new SpentMoney("Đổ xăng",50);
        dsSpentMoney.add(thongTinChi);

        data.put(new GregorianCalendar(2017, Calendar.SEPTEMBER, 25).getTime(), dsSpentMoney);
    }
    private int tinhTongChi(){
        int result=0;
        for(Map.Entry<Date, ArrayList<SpentMoney>> entry : data.entrySet()) {
            ArrayList<SpentMoney> dsSpentMoney = entry.getValue();
            for(int i = 0; i< dsSpentMoney.size(); i++){
                result+= dsSpentMoney.get(i).getSoTien();
            }
        }
        return result;
    }

}
