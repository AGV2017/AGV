package happyfood.vn.kaak.myapplication.Activity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;

import happyfood.vn.kaak.myapplication.Adapter.HomePagerAdapter;
import happyfood.vn.kaak.myapplication.R;

public class HomeActivity extends AppCompatActivity{

    public static final int FIND_TAB=0;
    public static final int SCAN_QR_CODE_TAB=1;
    public static final int FRIENDS_TAB=2;
    public static final int MONEY_MANAGEMENT_TAB=3;
    public static final int MORE_TAB=4;
    private ViewPager vpPager;
    FragmentPagerAdapter adapterViewPager;

    private ImageButton ibtnFind, ibtnScan, ibtnFriends, ibtnMore, ibtnMoneyManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        getFormWidgets();
    }
    /**
     * Hàm ánh xạ control
     * khởi tạo giá trị ban đầu
     */
    private void getFormWidgets(){
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new HomePagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setPageTransformer(true, new RotateUpTransformer());
        vpPager.setCurrentItem(0);

        ibtnFind=(ImageButton)findViewById(R.id.ibtnFind);
        ibtnScan=(ImageButton)findViewById(R.id.ibtnScan);
        ibtnFriends=(ImageButton)findViewById(R.id.ibtnFriends);
        ibtnMore=(ImageButton)findViewById(R.id.ibtnMore);
        ibtnMoneyManagement=(ImageButton)findViewById(R.id.ibtnMoneyManagement);
        addEvent();
    }
    /**
     * Add event
     */
    private void addEvent(){
        ibtnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(0);

                enableAllButton();
                ibtnFind.setImageResource(R.mipmap.ic_find_choose);
                ibtnFind.setEnabled(false);
            }
        });
        ibtnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(1);

                enableAllButton();
                ibtnScan.setImageResource(R.mipmap.ic_qrcode_choose);
                ibtnScan.setEnabled(false);
            }
        });
        ibtnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(2);

                enableAllButton();
                ibtnFriends.setImageResource(R.mipmap.ic_friends_choose);
                ibtnFriends.setEnabled(false);
            }
        });
        ibtnMoneyManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(3);
                enableAllButton();
                ibtnMoneyManagement.setImageResource(R.mipmap.ic_money_management_choose);
                ibtnMoneyManagement.setEnabled(false);
            }
        });
        ibtnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(4);

                enableAllButton();
                ibtnMore.setImageResource(R.mipmap.ic_more_choose);
                ibtnMore.setEnabled(false);
            }
        });
        vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if(position== FIND_TAB){
                    enableAllButton();
                    ibtnFind.setImageResource(R.mipmap.ic_find_choose);
                    ibtnFind.setEnabled(false);
                }
                else if(position==SCAN_QR_CODE_TAB){
                    enableAllButton();
                    ibtnScan.setImageResource(R.mipmap.ic_qrcode_choose);
                    ibtnScan.setEnabled(false);
                }
                else if(position==FRIENDS_TAB){
                    enableAllButton();
                    ibtnFriends.setImageResource(R.mipmap.ic_friends_choose);
                    ibtnFriends.setEnabled(false);
                }
                else if(position==MONEY_MANAGEMENT_TAB){
                    enableAllButton();
                    ibtnMoneyManagement.setImageResource(R.mipmap.ic_money_management_choose);
                    ibtnMoneyManagement.setEnabled(false);
                }
                else if(position==MORE_TAB)
                {
                    enableAllButton();
                    ibtnMore.setImageResource(R.mipmap.ic_more_choose);
                    ibtnMore.setEnabled(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void enableAllButton(){
        ibtnFind.setImageResource(R.mipmap.ic_find);
        ibtnScan.setImageResource(R.mipmap.ic_qrcode);
        ibtnFriends.setImageResource(R.mipmap.ic_friends);
        ibtnMoneyManagement.setImageResource(R.mipmap.ic_money_management);
        ibtnMore.setImageResource(R.mipmap.ic_more);

        ibtnFind.setEnabled(true);
        ibtnScan.setEnabled(true);
        ibtnFriends.setEnabled(true);
        ibtnMoneyManagement.setEnabled(true);
        ibtnMore.setEnabled(true);
    }
}
