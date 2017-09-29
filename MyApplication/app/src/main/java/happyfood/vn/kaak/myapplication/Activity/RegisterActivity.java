package happyfood.vn.kaak.myapplication.Activity;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import happyfood.vn.kaak.myapplication.R;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister, btnCancel;
    private EditText etUsername, etPassword, etConfirmPassword;
    private ImageButton ibtnShowPassword, ibtnShowConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        getFormWidgets();
        addEvent();
    }

    /**
     * Ánh xạ control
     */
    private void getFormWidgets() {
        etUsername = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassWord);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        ibtnShowPassword=(ImageButton)findViewById(R.id.ibtnShowPassword);
        ibtnShowConfirmPassword=(ImageButton)findViewById(R.id.ibtnShowConfirmPassword);
    }

    /**
     * Add event
     */
    private void addEvent(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                String confirmPassword=etConfirmPassword.getText().toString();

                if(checkValid(userName,password,confirmPassword)){
                    doRegister(userName,password);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        //Event when client hold button, password will be show
        ibtnShowPassword.setOnTouchListener(new View.OnTouchListener() {
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

        //Event when client hold button, confirm password will be show
        ibtnShowConfirmPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        v.setPressed(true);
                        //show content
                        etConfirmPassword.setTransformationMethod(null);
                        //move cusor to end
                        etConfirmPassword.setSelection(etConfirmPassword.length());
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                        v.setPressed(false);
                        //hide content
                        etConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());
                        //move cusor to end
                        etConfirmPassword.setSelection(etConfirmPassword.length());
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

    //Sau này 2 hàm này sẽ được chuyển vào class user
    /**
     * Check validation before register
     */
    private boolean checkValid(String userName, String password, String confirmPassword){
        return true;
    }

    /**
     * Do register
     */
    private boolean doRegister(String userName, String password){
        return true;
    }
}

