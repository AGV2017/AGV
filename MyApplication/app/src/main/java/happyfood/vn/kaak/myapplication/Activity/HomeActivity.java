package happyfood.vn.kaak.myapplication.Activity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        //Hide actionbar
        getSupportActionBar().hide();

        setContentView(R.layout.activity_home);

        getFormWidgets();
        addEvent();
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
        vpPager.setCurrentItem(FIND_TAB);

        ibtnFind=(ImageButton)findViewById(R.id.ibtnFind);
        ibtnScan=(ImageButton)findViewById(R.id.ibtnScan);
        ibtnFriends=(ImageButton)findViewById(R.id.ibtnFriends);
        ibtnMore=(ImageButton)findViewById(R.id.ibtnMore);
        ibtnMoneyManagement=(ImageButton)findViewById(R.id.ibtnMoneyManagement);


    }
    /**
     * Add event
     */
    private void addEvent(){
        ibtnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(FIND_TAB);

                enableAllButton();
                updateImageButtonOnTab(ibtnFind,R.mipmap.ic_find_choose);
            }
        });
        ibtnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(SCAN_QR_CODE_TAB);

                enableAllButton();
                updateImageButtonOnTab(ibtnScan,R.mipmap.ic_qrcode_choose);
            }
        });
        ibtnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(FRIENDS_TAB);

                enableAllButton();
                updateImageButtonOnTab(ibtnFriends,R.mipmap.ic_friends_choose);
            }
        });
        ibtnMoneyManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(MONEY_MANAGEMENT_TAB);
                enableAllButton();
                updateImageButtonOnTab(ibtnMoneyManagement,R.mipmap.ic_money_management_choose);
            }
        });
        ibtnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(MORE_TAB);
                enableAllButton();
                updateImageButtonOnTab(ibtnMore,R.mipmap.ic_more_choose);
            }
        });
        vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                //Enable all Imagebutton and hide Imagebutton just clicked
                enableAllButton();
                switch (position){
                    case FIND_TAB:
                        updateImageButtonOnTab(ibtnFind,R.mipmap.ic_find_choose);
                        break;
                    case SCAN_QR_CODE_TAB:
                        updateImageButtonOnTab(ibtnScan,R.mipmap.ic_qrcode_choose);
                        break;
                    case FRIENDS_TAB:
                        updateImageButtonOnTab(ibtnFriends,R.mipmap.ic_friends_choose);
                        break;
                    case MONEY_MANAGEMENT_TAB:
                        updateImageButtonOnTab(ibtnMoneyManagement,R.mipmap.ic_money_management_choose);
                        break;
                    case MORE_TAB:
                        updateImageButtonOnTab(ibtnMore,R.mipmap.ic_more_choose);
                        break;
                    default: break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * Enable all button and set image source is ic_not_choose
     */
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
    /**
     * update view when tab choose
     */
    private void updateImageButtonOnTab(ImageButton ibtnChoosed, int imgChoose){
        ibtnChoosed.setEnabled(false);
        ibtnChoosed.setImageResource(imgChoose);
    }
}
