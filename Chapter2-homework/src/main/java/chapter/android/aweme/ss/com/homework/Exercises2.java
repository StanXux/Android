package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;


/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    private View view_layout;
    private TextView tv_count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view_layout = getLayoutInflater().inflate(R.layout.activity_relativelayout, null);
        setContentView(view_layout);
        tv_count = findViewById(R.id.tv_center);
        tv_count.setText(String.valueOf(getAllChildViewCount(view_layout)-1));
    }

    public int getAllChildViewCount(View root) {
        if (null == root){return 0;}
        int view_count = 0;
        if(root instanceof ViewGroup){
            view_count++;
            for (int i=0; i<((ViewGroup)root).getChildCount(); i++){
                View view = ((ViewGroup) root).getChildAt(i);
                if(view instanceof ViewGroup){
                    view_count += getAllChildViewCount(view);
                }
                else{
                    view_count++;
                }

            }
        }
        return view_count;
    }

    public void click2Del(View view){
        ((ViewGroup)view_layout).removeView(view);
        tv_count.setText(String.valueOf(getAllChildViewCount(view_layout) - 1));

    }

}
