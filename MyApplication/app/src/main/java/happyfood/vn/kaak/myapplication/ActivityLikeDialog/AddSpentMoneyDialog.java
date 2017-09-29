package happyfood.vn.kaak.myapplication.ActivityLikeDialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import happyfood.vn.kaak.myapplication.Activity.HomeActivity;
import happyfood.vn.kaak.myapplication.R;

public class AddSpentMoneyDialog extends AppCompatActivity {

    public static final int RESULT_CODE=100;
    public static final int GET_MONEY=0;
    public static final int SPENT_MONEY=1;
    public static final int RESULT_CANCEL=0;
    public static final int RESULT_OK=1;
    EditText etContent, etCost;
    Button btnGetMoney,btnSpentMoney, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_spent_money_dialog);

        this.setFinishOnTouchOutside(false);
        getFormWidgets();
        addEvent();
    }

    /**
     * Mapped controls
     */
    private void getFormWidgets(){
        etContent=(EditText)findViewById(R.id.etContent);
        etCost=(EditText)findViewById(R.id.etCost);
        btnGetMoney=(Button)findViewById(R.id.btnGetMoney);
        btnSpentMoney=(Button)findViewById(R.id.btnSpentMoney);
        btnCancel=(Button)findViewById(R.id.btnCancel);
    }
    /**
     * Add event
     */
    private void addEvent(){
        btnGetMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("CONTENT",etContent.getText().toString());
                bundle.putInt("COST",Integer.parseInt(etCost.getText().toString()));
                bundle.putInt("TYPE",GET_MONEY);
                bundle.putInt("RESULT",RESULT_OK);
                intent.putExtra("DATA",bundle);
                setResult(RESULT_CODE,intent);
                finish();
            }
        });

        btnSpentMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("CONTENT",etContent.getText().toString());
                bundle.putInt("COST",Integer.parseInt(etCost.getText().toString()));
                bundle.putInt("TYPE",SPENT_MONEY);
                bundle.putInt("RESULT",RESULT_OK);
                intent.putExtra("DATA",bundle);
                setResult(RESULT_CODE,intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putInt("RESULT",RESULT_CANCEL);
                intent.putExtra("DATA",bundle);
                setResult(RESULT_CODE,intent);
                finish();
            }
        });
    }

}
