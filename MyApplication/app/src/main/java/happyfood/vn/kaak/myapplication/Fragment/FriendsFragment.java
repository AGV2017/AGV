package happyfood.vn.kaak.myapplication.Fragment;


import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import happyfood.vn.kaak.myapplication.Adapter.FriendsAdapter;
import happyfood.vn.kaak.myapplication.Adapter.GroupAdapter;
import happyfood.vn.kaak.myapplication.Model.Friend;
import happyfood.vn.kaak.myapplication.Model.Group;
import happyfood.vn.kaak.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {
    View view;
    private TabHost mTabhost;
    private ListView lstFriends, lstGroups;
    private ImageButton ibtnAddFriend, ibtnSearchFriend, ibtnAddGroup;
    private ArrayList<Friend> arrayFriends=new ArrayList<>();
    private ArrayList<Group> arrayGroups=new ArrayList<>();
    private FriendsAdapter adapterFriends;
    private GroupAdapter adapterGroups;

    //Data for test multichoise dialog
    final CharSequence items[] = new CharSequence[] {"Happy Pola", "Sơn Tùng MTP", "Lệ Rơi", "Châu Tinh Trì","Bao Công"};
    boolean[] checkedFriend = new boolean[]{
            false,
            false,
            false,
            false,
            false
    };


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
        if(view==null){
            view=inflater.inflate(R.layout.fragment_friends,container,false);
            getFormWidgets(view);
            addEvent();
        }

        return view;
    }

    /**
     * Mapped control
     * init value
     */
    private void getFormWidgets(View view){
        mTabhost=(TabHost)view.findViewById(R.id.mTabHost);
        lstFriends=(ListView)view.findViewById(R.id.lstFriends);
        lstGroups=(ListView)view.findViewById(R.id.lstGroups);
        ibtnAddFriend=(ImageButton)view.findViewById(R.id.ibtnAddFriend);
        ibtnAddGroup=(ImageButton)view.findViewById(R.id.ibtnAddGroup);
        ibtnSearchFriend=(ImageButton)view.findViewById(R.id.ibtnSearchFriend);

        addTabspec();
        addFakeData();

        adapterFriends=new FriendsAdapter(getContext(),arrayFriends);
        lstFriends.setAdapter(adapterFriends);
        adapterGroups=new GroupAdapter(getContext(),arrayGroups);
        lstGroups.setAdapter(adapterGroups);
    }
    /**
     * Add event
     */
    private void addEvent(){
        ibtnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Just click Add Friend",Toast.LENGTH_SHORT).show();
            }
        });
        ibtnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Just click Add Group",Toast.LENGTH_SHORT).show();
            }
        });
        ibtnSearchFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Just click Search Friend",Toast.LENGTH_SHORT).show();
            }
        });

        ibtnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create dialog for multi choise friends want add to group
                final AlertDialog.Builder ChooseFriend=new AlertDialog.Builder(getActivity());
                ChooseFriend.setMultiChoiceItems(items, checkedFriend, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedFriend[which]=isChecked;
                    }
                });
                ChooseFriend.setPositiveButton("Thêm vào nhóm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String listFriendChoosed="";
                        for(int i=0;i<checkedFriend.length;i++)
                            if(checkedFriend[i])
                                listFriendChoosed+="\n"+items[i];
                        Toast.makeText(getContext(),"Danh sách bạn được chọn: "+listFriendChoosed,Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                ChooseFriend.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ChooseFriend.show();
            }
        });
    }
    /**
     * Hàm add tabspec cho tabhost
     */
    private void addTabspec(){
        mTabhost.setup();
        TabHost.TabSpec spec;

        spec=mTabhost.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator(getResources().getString(R.string.all_list_friends));
        mTabhost.addTab(spec);

        spec=mTabhost.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator(getResources().getString(R.string.all_group));
        mTabhost.addTab(spec);
    }
    //add fake data
    private void addFakeData(){
        Friend friend=new Friend();
        friend.setName("Happy Pola");
        arrayFriends.add(friend);

        friend=new Friend();
        friend.setName("Sơn Tùng MTP");
        arrayFriends.add(friend);

        friend=new Friend();
        friend.setName("Lệ Rơi");
        arrayFriends.add(friend);

        friend=new Friend();
        friend.setName("Châu Tinh Trì");
        arrayFriends.add(friend);

        friend=new Friend();
        friend.setName("Bao Công");
        arrayFriends.add(friend);

        Group group=new Group();
        group.setName("Những thanh niên thông thái");
        arrayGroups.add(group);
    }
}
