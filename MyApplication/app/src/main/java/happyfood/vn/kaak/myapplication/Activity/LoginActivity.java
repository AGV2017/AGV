package happyfood.vn.kaak.myapplication.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import happyfood.vn.kaak.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }
}
