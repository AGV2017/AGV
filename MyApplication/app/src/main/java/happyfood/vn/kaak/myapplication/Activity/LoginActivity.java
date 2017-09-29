package happyfood.vn.kaak.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import happyfood.vn.kaak.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;
    private EditText etUsername, etPassword;
    private ImageButton ibtnShowpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        getFormWidgets();
        addEvent();
    }

    /**
     * mapped control
     */
    private void getFormWidgets(){
        etUsername=(EditText)findViewById(R.id.etUserName);
        etPassword=(EditText)findViewById(R.id.etPassWord);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        ibtnShowpassword=(ImageButton)findViewById(R.id.ibtnShowPassword);
    }

    /**
     * Add event
     */
    private void addEvent(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Just click login",Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        //Event when client hold button, password will be show
        ibtnShowpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        v.setPressed(true);
                        //show content
                        etPassword.setTransformationMethod(null);
                        //move cusor to end
                        etPassword.setSelection(etPassword.length());
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                        v.setPressed(false);
                        //hide content
                        etPassword.setTransformationMethod(new PasswordTransformationMethod());
                        //move cusor to end
                        etPassword.setSelection(etPassword.length());
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                }
                return true;
            }
        });
    }
}
