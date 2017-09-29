package happyfood.vn.kaak.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import happyfood.vn.kaak.myapplication.R;

/**
 * Library:
 * compile 'com.google.android.gms:play-services-maps:11.0.4'
 * compile 'com.google.android.gms:play-services-location:11.0.4'
 */
public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}
