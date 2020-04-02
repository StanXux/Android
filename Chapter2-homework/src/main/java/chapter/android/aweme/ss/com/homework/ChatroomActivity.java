package chapter.android.aweme.ss.com.homework;

import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;

public class ChatroomActivity extends AppCompatActivity {

    private EditText send;
    private TextView say;
    ImageView iv_send;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        TextView title = findViewById(R.id.tv_with_name);
        iv_send = findViewById(R.id.iv_send);
        say = findViewById(R.id.tv_content_info);
        send = findViewById(R.id.ed_say);

        send.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (0 == send.getText().length())
                    iv_send.setEnabled(false);
                else
                    iv_send.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle friend = extras.getBundle("friend");
            if (friend != null) {
                int id = friend.getInt("id");
                String name = friend.getString("name");
                String said = friend.getString("said");

                String str_title = "item." + id + "--" + name;
                title.setText(str_title);
                String str_said = said + "\n";
                say.setText(str_said);
            }
        }
    }

    public void send_onClick(View view){
        TextView tv_chat = findViewById(R.id.tv_content_info);
        EditText rd_chat = findViewById(R.id.ed_say);
        String str = rd_chat.getText().toString() + "\n";
        tv_chat.append(str);
        rd_chat.setText("");
        new Handler().postDelayed(new Runnable(){
            public void run() {
                auto_reply(str);
            }
        }, 1000);
    }

    public void auto_reply(String str){
        TextView tv_chat = findViewById(R.id.tv_content_info);
        String reply = str.replaceAll("你", "我");
        SystemClock.sleep(500);
        tv_chat.append(reply);
    }
}
