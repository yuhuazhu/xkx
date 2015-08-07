package com.xiamen.xkx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/7.
 */ //AppCompat
public class JingquListActivity extends AppCompatActivity {

    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;

    private ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jingqu_list);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // improve performance if you know that changes in content
        // do not change the size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JingquListActivity.this, "back click", Toast.LENGTH_SHORT).show();
            }
        });

        //mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
        //    @Override
        //    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        //        return false;
        //    }
        //
        //    @Override
        //    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        //        Log.e("test", "onTouchEvent()-------------");
        //        Toast.makeText(JingquListActivity.this, "onTouchEvent", Toast.LENGTH_SHORT).show();
        //    }
        //
        //    @Override
        //    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        //
        //    }
        //});
        mRecyclerView.setClickable(true);
        mRecyclerView.setFocusable(true);
        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JingquListActivity.this, "onClick", Toast.LENGTH_SHORT).show();
            }
        });

        // specify an adapter (see also next example)
        MyAdapter mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        list.add("(厦门)鼓浪屿景区");
        list.add("(厦门)观音山景区");
        list.add("(厦门)胡里山景区");
        list.add("(厦门)南普陀寺");
        list.add("(厦门)厦门大学");
        list.add("(厦门)中山路");
        list.add("(厦门)环岛路");
        list.add("(厦门)文曾路");
        list.add("(厦门)植物园");
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_jingqu_name);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_head);
        }
    }

    private final int TYPE_ITEM = 1;
    private final int TYPE_HEAD = 2;

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        OnItemClickLitener onItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
            this.onItemClickLitener = onItemClickLitener;
        }

        @Override

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(JingquListActivity.this);
            if (viewType == TYPE_HEAD) {
                return new HeaderViewHolder(inflater.inflate(R.layout.item_head, null));
            } else if (viewType == TYPE_ITEM) {
                return new ItemViewHolder(inflater.inflate(R.layout.item_jingqu_list, null));
            }
            throw new RuntimeException("there is unknown viewtype in the recycler view ,check it");
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            if (viewHolder instanceof ItemViewHolder) {
                final ItemViewHolder holder = (ItemViewHolder) viewHolder;
                holder.tv.setText(list.get(position - 1));
                if (true) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = holder.getLayoutPosition();
                            //onItemClickLitener.onItemClick(holder.itemView, pos);
                            Toast.makeText(JingquListActivity.this, "onClick", Toast.LENGTH_SHORT).show();
                            Intent it = new Intent(JingquListActivity.this, JingquActivity.class);
                            startActivity(it);
                        }
                    });
                    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            int pos = holder.getLayoutPosition();
                            //onItemClickLitener.onItemLongClick(holder.itemView, pos);
                            Toast.makeText(JingquListActivity.this, "onlongclick", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    });
                }
            } else if (viewHolder instanceof HeaderViewHolder) {
                //HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
            }

        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_HEAD;
            }
            return TYPE_ITEM;
        }

        @Override
        public int getItemCount() {
            return list.size() + 1;
        }
    }

}

interface OnItemClickLitener {
    void onItemClick(View view, int position);

    void onItemLongClick(View view, int position);
}