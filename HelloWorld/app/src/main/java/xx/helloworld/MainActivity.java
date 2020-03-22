package xx.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.CheckBox;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "MainActivity");

        TextView tv = findViewById(R.id.textView1);
        tv.setTextSize(15);
        tv.setGravity(Gravity.CENTER);

    }

    int size = 15;

    public void show_pic(View v)
    {
        ImageView iv1 = findViewById(R.id.imageView1);
        Switch sw1 = findViewById(R.id.switch1);
        if (sw1.isChecked())
        {
            iv1.setVisibility(View.VISIBLE);
        }
        else
        {
            iv1.setVisibility(View.INVISIBLE);
        }
    }

    public void bigger(View v)
    {
        TextView tv1 = findViewById(R.id.textView1);
        tv1.setTextSize(++size);
    }

    public void smaller(View v)
    {
        TextView tv2 = findViewById(R.id.textView1);
        tv2.setTextSize(--size);
    }

    public void mod(View v)
    {
        EditText editText1 = findViewById(R.id.editText1);
        TextView tv3 = findViewById(R.id.textView1);
        tv3.setText(editText1.getText().toString());
    }

    public void show_word(View v)
    {
        TextView tv4 = findViewById(R.id.textView1);
        CheckBox cb1 = findViewById(R.id.checkBox1);
        if(cb1.isChecked())
        {
            tv4.setVisibility(View.VISIBLE);
        }
        else
        {
            tv4.setVisibility(View.INVISIBLE);
        }
    }

}
