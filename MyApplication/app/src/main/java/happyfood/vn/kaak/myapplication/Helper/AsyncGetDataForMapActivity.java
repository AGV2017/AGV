package happyfood.vn.kaak.myapplication.Helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import happyfood.vn.kaak.myapplication.Adapter.RestaurantOnMapAdapter;
import happyfood.vn.kaak.myapplication.Model.Restaurant;

/**
 * Created by MyPC on 16/09/2017.
 */

public class AsyncGetDataForMapActivity extends AsyncTask<String,Void,Void> {
    private Context context;
    private ArrayList<Restaurant> arrayRestaurant=new ArrayList<>();
    private LatLng myLocation;
    private ProgressDialog progressDialog;
    private ListView lstRestaurant;

    public AsyncGetDataForMapActivity(Context context, ListView lstRestaurant, LatLng myLocation) {
        this.context = context;
        this.lstRestaurant=lstRestaurant;
        this.myLocation=myLocation;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Getting data...");
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        HttpHandler httpHandler=new HttpHandler();
        for(int j=0;j<params.length;j++){
            String jsonStr=httpHandler.makeServiceCall(params[j]);
            if(jsonStr!=null){
                try{
                    JSONObject result=new JSONObject(jsonStr);
                    JSONArray arrayPosts=result.getJSONArray("posts");
                    for(int i=0;i<arrayPosts.length();i++){
                        String title=arrayPosts.getJSONObject(i).getString("title");
                        String thumbnail=arrayPosts.getJSONObject(i).getString("thumbnail");
                        String address=arrayPosts.getJSONObject(i).getString("excerpt");
                        Float vote=Float.parseFloat(arrayPosts.getJSONObject(i).getInt("id")+"");
                        Bitmap image=null;
                        /*try {
                            URL url = new URL(thumbnail);
                            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        } catch(IOException e) {
                            Log.e("ErrorBitMap","Couldn't get bitmap of "+title+" cause: "+e.getMessage());
                        }*/
                        Restaurant oneRestaurant=new Restaurant();
                        oneRestaurant.setVote(vote);
                        oneRestaurant.setImage(image);
                        oneRestaurant.setPosition(new LatLng(10.8125,106.707));
                        oneRestaurant.setAddress(address);
                        oneRestaurant.setLinkImage(thumbnail);
                        oneRestaurant.setName(title);

                        arrayRestaurant.add(oneRestaurant);
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
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        RestaurantOnMapAdapter adapter=new RestaurantOnMapAdapter(context,arrayRestaurant,myLocation);
        lstRestaurant.setAdapter(adapter);
    }


}
