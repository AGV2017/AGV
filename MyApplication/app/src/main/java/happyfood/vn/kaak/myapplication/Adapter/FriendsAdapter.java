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
import happyfood.vn.kaak.myapplication.R;

/**
 * Created by MyPC on 02/10/2017.
 */

public class FriendsAdapter extends BaseAdapter {
    Context context;
    ArrayList<Friend> arrayFriends=new ArrayList<>();

    public FriendsAdapter(Context context, ArrayList<Friend> arrayFriends) {
        this.context = context;
        this.arrayFriends = arrayFriends;
    }

    @Override
    public int getCount() {
        return arrayFriends.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayFriends.get(position);
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

        Friend friend=arrayFriends.get(position);
        tvName.setText(friend.getName());
        if(friend.getImage()!=null)
            imgAvatar.setImageBitmap(friend.getImage());
        return convertView;
    }
}
