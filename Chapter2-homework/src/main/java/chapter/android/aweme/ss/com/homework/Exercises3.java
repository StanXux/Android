package chapter.android.aweme.ss.com.homework;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.util.Log;

import java.io.InputStream;
import java.util.List;

import chapter.android.aweme.ss.com.homework.model.*;

/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */
public class Exercises3 extends AppCompatActivity implements GreenAdapter.ListItemClickListener {

    private static final String TAG = "xuxiang";

    private static List<Message> messages;

    private GreenAdapter xAdapter;
    private RecyclerView xNumbersListView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        try {
            InputStream assetInput = getAssets().open("data.xml");
            messages = PullParser.pull2xml(assetInput);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

        xNumbersListView = findViewById(R.id.rv_list);
        LinearLayoutManager llayoutManager = new LinearLayoutManager(this);
        llayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xNumbersListView.setLayoutManager(llayoutManager);
        xNumbersListView.setHasFixedSize(true);
        xAdapter = new GreenAdapter(messages.size(), this, messages);

        xNumbersListView.setAdapter(xAdapter);
        xNumbersListView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            //最后一个完全可见项的位置
            private int lastCompletelyVisibleItemPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager rlayoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = rlayoutManager.getChildCount();
                int totalItemCount = rlayoutManager.getItemCount();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (visibleItemCount > 0 && lastCompletelyVisibleItemPosition >= totalItemCount - 1) {
                        Toast.makeText(Exercises3.this, "已经到底了!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager rlayoutManager = recyclerView.getLayoutManager();
                if (rlayoutManager instanceof LinearLayoutManager) {
                    lastCompletelyVisibleItemPosition = ((LinearLayoutManager) rlayoutManager).findLastCompletelyVisibleItemPosition();
                }
                Log.d(TAG, "onScrolled: lastVisiblePosition=" + lastCompletelyVisibleItemPosition);

            }
        });

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.d(TAG, "onListItemClick: " + clickedItemIndex);

        Intent intent = new Intent(this, ChatroomActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", clickedItemIndex);
        bundle.putString("name", messages.get(clickedItemIndex).getTitle());
        bundle.putString("said", messages.get(clickedItemIndex).getDescription());
        intent.putExtra("friend", bundle);
        startActivity(intent);

    }

}
