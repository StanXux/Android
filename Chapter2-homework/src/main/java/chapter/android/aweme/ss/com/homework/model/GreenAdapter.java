package chapter.android.aweme.ss.com.homework.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

import chapter.android.aweme.ss.com.homework.R;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;


//适配器
public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {
    private static final String TAG = "GreenAdapter";
    private int mNumberItems;
    private final ListItemClickListener mOnClickListener;
    private static int viewHolderCount;
    private List<Message> mMessages;

    public GreenAdapter(int numListItems, ListItemClickListener listener, List<Message> messages) {
        mNumberItems = numListItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
        mMessages = messages;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        viewHolder.itemView.setBackgroundColor(0x000000);

        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);
        numberViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView offical;//标识符，是否官方
        private TextView title;//昵称
        private TextView description;//描述
        private TextView time;//时间
        private CircleImageView portrait;//头像

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);

            offical = (ImageView) itemView.findViewById(R.id.robot_notice);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            time = (TextView) itemView.findViewById(R.id.tv_time);
            portrait = (CircleImageView) itemView.findViewById(R.id.iv_avatar);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            if(mMessages.get(position).isOfficial())
                offical.setVisibility(View.VISIBLE);
            else
                offical.setVisibility(View.INVISIBLE);
            title.setText(mMessages.get(position).getTitle());
            description.setText(mMessages.get(position).getDescription());
            time.setText(mMessages.get(position).getTime());
            setHead(portrait, mMessages.get(position).getIcon());

//            viewHolderIndex.setText(String.format("ViewHolder index: %s", getAdapterPosition()));
//            int backgroundColorForViewHolder = ColorUtils.
//                    getViewHolderBackgroundColorFromInstance(itemView.getContext(), getAdapterPosition() % 10);
//            itemView.setBackgroundColor(backgroundColorForViewHolder);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }

        public void setHead(ImageView imageview, String str) {
            if(str.equals("TYPE_GAME"))
                imageview.setImageResource(R.drawable.icon_micro_game_comment);
            else if(str.equals("TYPE_ROBOT"))
                imageview.setImageResource(R.drawable.session_robot);
            else if(str.equals("TYPE_SYSTEM"))
                imageview.setImageResource(R.drawable.session_system_notice);
            else if(str.equals("TYPE_STRANGER"))
                imageview.setImageResource(R.drawable.session_stranger);
            else if(str.equals("TYPE_USER"))
                imageview.setImageResource(R.drawable.icon_girl);
        }

    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
