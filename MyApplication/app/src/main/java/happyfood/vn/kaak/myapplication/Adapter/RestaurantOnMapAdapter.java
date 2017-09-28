package happyfood.vn.kaak.myapplication.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import happyfood.vn.kaak.myapplication.Model.Restaurant;
import happyfood.vn.kaak.myapplication.R;

/**
 * Created by MyPC on 20/09/2017.
 */

public class RestaurantOnMapAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Restaurant> arrayRestaurant=new ArrayList<>();
    private LatLng myLocation;

    public RestaurantOnMapAdapter(Context context, ArrayList<Restaurant> arrayRestaurant, LatLng myLocation) {
        this.context = context;
        this.arrayRestaurant = arrayRestaurant;
        this.myLocation=myLocation;
    }

    @Override
    public int getCount() {
        return arrayRestaurant.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayRestaurant.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Restaurant restaurant=arrayRestaurant.get(position);
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.custom_layout_restaurant,parent,false);
        }

        TextView tvName=(TextView)convertView.findViewById(R.id.tvName);
        TextView tvAddress=(TextView)convertView.findViewById(R.id.tvAddress);
        TextView tvVote=(TextView)convertView.findViewById(R.id.tvVote);
        TextView tvDistance=(TextView)convertView.findViewById(R.id.tvDistance);

        tvName.setText(restaurant.getName());
        tvAddress.setText(restaurant.getAddress());
        tvVote.setText("9.6");
        float[] distances=new float[1];
        Location.distanceBetween(myLocation.latitude,myLocation.longitude,restaurant.getPosition().latitude,restaurant.getPosition().longitude,distances);
        if(distances[0]<1000)
            tvDistance.setText((int)distances[0]+" m");
        else
            tvDistance.setText(String.format("%.1f",(distances[0]/1000))+" km");


        if(restaurant.getImage()!=null){
            ImageView ivIcon=(ImageView)convertView.findViewById(R.id.ivIcon);
            ivIcon.setImageBitmap(restaurant.getImage());
        }
        else {
            RestaurantAndView container=new RestaurantAndView();
            container.restaurant=restaurant;
            container.view=convertView;

            ImageLoader imageLoader=new ImageLoader();
            imageLoader.execute(container);
        }



        return convertView;
    }

    private class RestaurantAndView{
        public Restaurant restaurant;
        public View view;
        public Bitmap bitmap;
    }
    private class ImageLoader extends AsyncTask<RestaurantAndView,Void,RestaurantAndView> {
        @Override
        protected RestaurantAndView doInBackground(RestaurantAndView... params) {
            RestaurantAndView container=params[0];
            Restaurant restaurant=container.restaurant;

            Bitmap image=null;
            try {
                URL url = new URL(restaurant.getLinkImage());
                image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch(IOException e) {
                Log.e("ErrorBitMap","Couldn't get bitmap of "+restaurant.getName()+" cause: "+e.getMessage());
            }

            restaurant.setImage(image);
            container.bitmap=image;

            return container;
        }

        @Override
        protected void onPostExecute(RestaurantAndView restaurantAndViewAndView) {
            super.onPostExecute(restaurantAndViewAndView);

            ImageView ivIcon=(ImageView)restaurantAndViewAndView.view.findViewById(R.id.ivIcon);
            ivIcon.setImageBitmap(restaurantAndViewAndView.bitmap);
            restaurantAndViewAndView.restaurant.setImage(restaurantAndViewAndView.bitmap);
        }
    }
}
