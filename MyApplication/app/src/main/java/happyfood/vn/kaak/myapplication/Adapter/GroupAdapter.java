package happyfood.vn.kaak.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import happyfood.vn.kaak.myapplication.Model.Friend;
import happyfood.vn.kaak.myapplication.Model.Group;
import happyfood.vn.kaak.myapplication.R;

/**
 * Created by MyPC on 02/10/2017.
 */

public class GroupAdapter extends BaseAdapter {
    Context context;
    ArrayList<Group> arrayGroups=new ArrayList<>();

    public GroupAdapter(Context context, ArrayList<Group> arrayGroups) {
        this.context = context;
        this.arrayGroups = arrayGroups;
    }

    @Override
    public int getCount() {
        return arrayGroups.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayGroups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.custom_layout_list_friends_and_groups,parent,false);
        }
        TextView tvName=(TextView)convertView.findViewById(R.id.tvName);
        ImageView imgAvatar=(ImageView)convertView.findViewById(R.id.imgAvatar);

        Group group=arrayGroups.get(position);
        tvName.setText(group.getName());
        if(group.getImage()!=null)
            imgAvatar.setImageBitmap(group.getImage());
        return convertView;
    }
}
