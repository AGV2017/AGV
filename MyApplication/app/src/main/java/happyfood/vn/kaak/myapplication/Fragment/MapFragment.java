package happyfood.vn.kaak.myapplication.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;


import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.SearchView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import happyfood.vn.kaak.myapplication.Model.Restaurant;
import happyfood.vn.kaak.myapplication.R;
import happyfood.vn.kaak.myapplication.Helper.*;

public class MapFragment extends Fragment implements OnMapReadyCallback{

    private View view;
    private LinearLayout layoutList, layoutActionbar;
    private FrameLayout layoutMap;
    private View rootView;
    private GoogleMap mGoogleMap;
    private SupportMapFragment mapFrag;
    private TabHost mTabhost;
    private ListView lstRestaurantOnTab1,lstRestaurantOnTab2,lstRestaurantOnTab3,lstRestaurantOnTab4,lstRestaurantOnTab5;
    private ArrayList<Restaurant> arrayRestaurant=new ArrayList<>();
    private SearchView searchView;
    private Button btnType;
    private ImageButton ibtnMenu,ibtnShowList,ibtnScanQR;
    private FusedLocationProviderClient mFusedLocationClient;
    private MarkerOptions markerOptionsMyLocation;
    private Marker markerMyLocation;

    private LatLng myLocation=new LatLng(10.7721,106.696);
    private Circle mCircle;

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    public MapFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide title of activity on actionbar
        /*getSupportActionBar().hide();
        setContentView(R.layout.fragment_map);*/


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.fragment_map,container,false);
            mFusedLocationClient= LocationServices.getFusedLocationProviderClient(getActivity().getApplicationContext());
            getFormWidgets();
            ibtnShowList.setVisibility(View.GONE);
            addEvent();
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        searchView.setQuery("", false);
        rootView.requestFocus();
    }


    private void getFormWidgets() {
        rootView=view.findViewById(R.id.layout_root);
        //Kiểm tra xem thiết bị đã cấp quyền truy cập vị trí hay chưa
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            GoogleHelper.checkLocationPermission(getActivity());
        }

        mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFrag != null) {
            mapFrag.getMapAsync(this);
        }
        /*mapFrag = getFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);*/

        searchView=(SearchView)view.findViewById(R.id.svSearch);
        //Set searchview auto expend
        searchView.setIconifiedByDefault(false);
        //Clear focus on searchview
        searchView.setFocusable(false);
        searchView.setIconified(false);
        searchView.clearFocus();

        //ánh xạ các control
        layoutList=(LinearLayout)view.findViewById(R.id.layout_list);
        layoutMap=(FrameLayout)view.findViewById(R.id.layout_map);
        layoutActionbar=(LinearLayout)view.findViewById(R.id.layout_actionbar);
        btnType=(Button)view.findViewById(R.id.btnType);
        ibtnMenu=(ImageButton)view.findViewById(R.id.ibtnMenu);
        ibtnScanQR=(ImageButton)view.findViewById(R.id.ibtnScanQRCode);
        ibtnShowList=(ImageButton)view.findViewById(R.id.ibtnShowList);

        mTabhost=(TabHost)view.findViewById(R.id.mTabHost);
        addTabspec();

        lstRestaurantOnTab1=(ListView)view.findViewById(R.id.lst_restaurant_tab_1);
        lstRestaurantOnTab2=(ListView)view.findViewById(R.id.lst_restaurant_tab_2);
        lstRestaurantOnTab3=(ListView)view.findViewById(R.id.lst_restaurant_tab_3);
        lstRestaurantOnTab4=(ListView)view.findViewById(R.id.lst_restaurant_tab_4);
        lstRestaurantOnTab5=(ListView)view.findViewById(R.id.lst_restaurant_tab_5);

        //Lấy chiều cao của actionbar gán cho 2 layout actionbar
        // Calculate ActionBar height
        int actionBarHeight=0;
        TypedValue tv = new TypedValue();
        if (getActivity().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        layoutActionbar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, actionBarHeight));

        //Ẩn 2 button cài dặt và quét QRCode đi
        ibtnMenu.setVisibility(View.GONE);
        ibtnScanQR.setVisibility(View.GONE);

        /*fakeDataRestaurant();

        RestaurantOnMapAdapter restaurantOnMapAdapter_tab_1=new RestaurantOnMapAdapter(this,arrayRestaurant);
        lstRestaurantOnTab1.setAdapter(restaurantOnMapAdapter_tab_1);

        RestaurantOnMapAdapter restaurantOnMapAdapter_tab_2=new RestaurantOnMapAdapter(this,arrayRestaurant);
        lstRestaurantOnTab2.setAdapter(restaurantOnMapAdapter_tab_2);

        RestaurantOnMapAdapter restaurantOnMapAdapter_tab_3=new RestaurantOnMapAdapter(this,arrayRestaurant);
        lstRestaurantOnTab3.setAdapter(restaurantOnMapAdapter_tab_3);

        RestaurantOnMapAdapter restaurantOnMapAdapter_tab_4=new RestaurantOnMapAdapter(this,arrayRestaurant);
        lstRestaurantOnTab4.setAdapter(restaurantOnMapAdapter_tab_4);

        RestaurantOnMapAdapter restaurantOnMapAdapter_tab_5=new RestaurantOnMapAdapter(this,arrayRestaurant);
        lstRestaurantOnTab5.setAdapter(restaurantOnMapAdapter_tab_5);*/
    }

    /**
     * Hàm add sự kiện cho control
     *
     */
    private void addEvent(){
        mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                String str="Tab "+tabId+" ở vị trí "+mTabhost.getCurrentTab();
                Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });

        btnType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder chooseRadius=new AlertDialog.Builder(getActivity());
                final CharSequence items[] = new CharSequence[] {"100m", "200m", "500m", "1km"};
                int old=-1;
                for(int i=0;i<items.length;i++){
                    if(btnType.getText().toString().trim().equals(items[i])){
                        old=i;
                        break;
                    }
                }
                chooseRadius.setSingleChoiceItems(items,old, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnType.setText(items[which]);
                        dialog.dismiss();
                    }
                });
                chooseRadius.show();

            }
        });

        ibtnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams paramsLayoutList=(LinearLayout.LayoutParams)layoutList.getLayoutParams();
                paramsLayoutList.weight=0.4f;
                layoutList.setLayoutParams(paramsLayoutList);

                ibtnShowList.setVisibility(View.GONE);
            }
        });

        ibtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"You just click button menu",Toast.LENGTH_SHORT).show();
            }
        });

        ibtnScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"You just click button scan QRcode",Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                AsyncGetLocationFromAddress task=new AsyncGetLocationFromAddress();
                task.execute("http://maps.google.com/maps/api/geocode/json?address="+query.replaceAll(" ","%20")+"&sensor=false");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        //mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //User has previously accepted this permission
            if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mGoogleMap.setMyLocationEnabled(true);
            }
        } else {
            //Not in api-23, no need to prompt
            mGoogleMap.setMyLocationEnabled(true);
        }

        //Get my location
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            myLocation=new LatLng(location.getLatitude(),location.getLongitude());
                            if(mCircle == null || markerMyLocation == null){
                                drawMarkerWithCircle(myLocation);
                            }else{
                                updateMarkerWithCircle(myLocation);
                            }
                            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation,17));
                            /*AsyncGetDataForMapActivity async=new AsyncGetDataForMapActivity(getActivity(),lstRestaurantOnTab1,myLocation);
                            async.execute(new String[] {"http://nguyenphuc20.esy.es/newblog/category/tho-nguyen-nhat-anh?json=1"});*/
                        }
                    }
                });

        AsyncGetDataForMapActivity async=new AsyncGetDataForMapActivity(getActivity(),lstRestaurantOnTab1,myLocation);
        async.execute(new String[] {"http://nguyenphuc20.esy.es/newblog/category/tho-nguyen-nhat-anh?json=1"});
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //Mở rộng bản đồ ra full màn hình và hiển thị nút showlist
                LinearLayout.LayoutParams paramsLayoutList=(LinearLayout.LayoutParams)layoutList.getLayoutParams();
                paramsLayoutList.weight=0;
                layoutList.setLayoutParams(paramsLayoutList);
                //Cập nhật lại vị trí của marker trên bản đồ
                myLocation=latLng;
                if(mCircle == null || markerMyLocation == null){
                    drawMarkerWithCircle(latLng);
                }else{
                    updateMarkerWithCircle(latLng);
                }
                //Hiển thị nút show danh sách listview
                ibtnShowList.setVisibility(View.VISIBLE);
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case GoogleHelper.MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay!
                    if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mGoogleMap.setMyLocationEnabled(true);

                    }
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getActivity().getApplicationContext(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    /**
     * Hàm add tabspec cho tabhost
     */
    private void addTabspec(){
        mTabhost.setup();
        TabHost.TabSpec spec;

        spec=mTabhost.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator(getResources().getString(R.string.list_restaurant_tabspec_1));
        mTabhost.addTab(spec);

        spec=mTabhost.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator(getResources().getString(R.string.list_restaurant_tabspec_2));
        mTabhost.addTab(spec);

        spec=mTabhost.newTabSpec("tab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator(getResources().getString(R.string.list_restaurant_tabspec_3));
        mTabhost.addTab(spec);

        spec=mTabhost.newTabSpec("tab4");
        spec.setContent(R.id.tab4);
        spec.setIndicator(getResources().getString(R.string.list_restaurant_tabspec_4));
        mTabhost.addTab(spec);

        spec=mTabhost.newTabSpec("tab5");
        spec.setContent(R.id.tab5);
        spec.setIndicator(getResources().getString(R.string.list_restaurant_tabspec_5));
        mTabhost.addTab(spec);
    }

    /**
     * Hàm tạo dữ liệu giả cho arraylist
     */
    private void fakeDataRestaurant(){
        Restaurant restaurant=new Restaurant();
        restaurant.setName("Nhà hàng siêu ngon");
        restaurant.setAddress("289 Đinh Bộ Lĩnh, P26, quận Bình Thạnh, TP.HCM");
        restaurant.setVote(9.5f);
        arrayRestaurant.add(restaurant);

        restaurant=new Restaurant();
        restaurant.setName("Nhà hàng dở ẹt");
        restaurant.setAddress("100 Đinh Bộ Lĩnh, P26, quận Bình Thạnh, TP.HCM");
        restaurant.setVote(3.5f);
        arrayRestaurant.add(restaurant);

        restaurant=new Restaurant();
        restaurant.setName("King BBQ");
        restaurant.setAddress("10 Xô viết nghệ tĩnh, P26, quận Bình Thạnh, TP.HCM");
        restaurant.setVote(7.5f);
        arrayRestaurant.add(restaurant);

        restaurant=new Restaurant();
        restaurant.setName("Highland coffe");
        restaurant.setAddress("123 Nguyễn Văn Cừ, P26, Quận 5, TP.HCM");
        restaurant.setVote(8.5f);
        arrayRestaurant.add(restaurant);
    }

    /**
     * Hàm vẽ vòng tròn xung quanh mylocation
     */
    private void updateMarkerWithCircle(LatLng position) {
        mCircle.setCenter(position);
        markerMyLocation.setPosition(position);
    }

    private void drawMarkerWithCircle(LatLng position){
        double radiusInMeters = 100.0;
        int strokeColor = 0xffff0000; //red outline
        int shadeColor = 0x44ff0000; //opaque red fill

        CircleOptions circleOptions = new CircleOptions().center(position).radius(radiusInMeters).fillColor(shadeColor).strokeColor(strokeColor).strokeWidth(8);
        mCircle = mGoogleMap.addCircle(circleOptions);

        MarkerOptions markerOptions = new MarkerOptions().position(position).title("Your location");
        markerMyLocation = mGoogleMap.addMarker(markerOptions);
    }

    public class AsyncGetLocationFromAddress extends AsyncTask<String,Void,LatLng> {

        ProgressDialog mdialog;
        public AsyncGetLocationFromAddress(){
            this.mdialog=new ProgressDialog(getActivity());
            this.mdialog.setMessage("Finding location...");
            this.mdialog.setTitle("Please wait");
        }
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mdialog.show();

        }

        @Override
        protected LatLng doInBackground(String... params) {
            HttpHandler httpHandler=new HttpHandler();
            for(int j=0;j<params.length;j++){
                String jsonStr=httpHandler.makeServiceCall(params[j]);
                if(jsonStr!=null){
                    try{
                        JSONObject result=new JSONObject(jsonStr);
                        String status=result.getString("status");
                        if(status.equals("OK")){
                            JSONArray arrayResult=result.getJSONArray("results");
                            JSONObject geometry=arrayResult.getJSONObject(0).getJSONObject("geometry");
                            JSONObject location=geometry.getJSONObject("location");
                            float lat=(float)location.getDouble("lat");
                            float lng=(float)location.getDouble("lng");
                            return new LatLng(lat,lng);
                        }
                        else {
                            return null;
                        }
                    }catch (Exception e){
                        Log.e("ErrorParse","JSON Parsing errror: "+e.getMessage());
                    }
                }
                else {
                    Log.e("GetJSON","Couldn't get JSON from server");
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(LatLng latLng) {
            super.onPostExecute(latLng);
            mdialog.dismiss();
            //Clear focus on searchview
            searchView.setFocusable(false);
            searchView.setIconified(false);
            searchView.clearFocus();

            if(latLng==null){
                Toast.makeText(getActivity().getApplicationContext(),"Cann't find your location!",Toast.LENGTH_SHORT).show();
            }
            else {
                myLocation=latLng;
                //update marker on map and draw circle
                if(mCircle == null || markerMyLocation == null){
                    drawMarkerWithCircle(latLng);
                }else{
                    updateMarkerWithCircle(latLng);
                }
                //move camera to location just found
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocation.latitude,myLocation.longitude),17));
            }
        }
    }
}
