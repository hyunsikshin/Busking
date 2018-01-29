package com.unithon.busking.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.unithon.busking.R;

public class DriverActivity extends AppCompatActivity implements View.OnClickListener {

    Context mContext;
    LinearLayout message_btn;
    LinearLayout drive_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        mContext = this;

        message_btn = (LinearLayout) findViewById(R.id.btn_message);
        drive_btn = (LinearLayout) findViewById(R.id.btn_start);
        message_btn.setOnClickListener(this);
        drive_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_message:
                startActivity(new Intent(mContext, MessageActivity.class));
                break;

            case R.id.btn_start:
                startActivity(new Intent(mContext, DrivingActivity.class));
                break;
        }
    }
}
