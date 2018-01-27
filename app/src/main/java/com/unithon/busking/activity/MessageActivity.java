package com.unithon.busking.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.unithon.busking.R;
import com.unithon.busking.adapter.MessageAdapter;

public class MessageActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String[] myDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataSet = new String[]{"친절한 인사 감사합니다.", "안전운전 좋아요!", "친절한 인사 감사합니다.",
        "난폭운전하지 않아서 좋아요!"};

        // specify an adapter (see also next example)
        mAdapter = new MessageAdapter(myDataSet);
        mRecyclerView.setAdapter(mAdapter);
    }
}
