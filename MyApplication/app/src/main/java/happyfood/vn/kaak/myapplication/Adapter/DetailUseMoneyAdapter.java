package happyfood.vn.kaak.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import happyfood.vn.kaak.myapplication.Model.SpentMoney;
import happyfood.vn.kaak.myapplication.R;


/**
 * Created by MyPC on 27/09/2017.
 */

public class DetailUseMoneyAdapter extends BaseAdapter {
    Context context;
    ArrayList<SpentMoney> arrSpentMoney =new ArrayList<>();

    public DetailUseMoneyAdapter(Context context, ArrayList<SpentMoney> arrSpentMoney) {
        this.context = context;
        this.arrSpentMoney = arrSpentMoney;
    }

    @Override
    public int getCount() {
        return arrSpentMoney.size();
    }

    @Override
    public Object getItem(int position) {
        return arrSpentMoney.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView= LayoutInflater.from(context).inflate(R.layout.custom_layout_spent_money,parent,false);

        TextView tv1=(TextView) convertView.findViewById(R.id.tvNoiDung);
        TextView tvSoTien=(TextView)convertView.findViewById(R.id.tvSoTien);

        SpentMoney motSpentMoney = arrSpentMoney.get(position);
        tv1.setText(motSpentMoney.getNoiDung());
        tvSoTien.setText(motSpentMoney.getSoTien()+"");
        return convertView;
    }
}
