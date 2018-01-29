package com.unithon.busking.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unithon.busking.R;
import com.unithon.busking.util.TTSHelper;


public class DrivingActivity extends AppCompatActivity {

    private NaverTTSTask mNaverTTSTask;
    private TTSHelper ttsHelper;
    TextView client_message;
    LinearLayout btn_end;
    String[] mTextMessage;
    Context mContext;
    ImageView image_face;
    TextView message_txt;
    TextView view_color;
    TextView instance_txt;

    boolean isClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving);
        mContext = this;

        isClicked = false;
        ttsHelper = new TTSHelper();

        instance_txt = (TextView) findViewById(R.id.instance_txt);
        view_color = (TextView) findViewById(R.id.view_color);
        message_txt = (TextView) findViewById(R.id.message_txt);
        image_face = (ImageView) findViewById(R.id.image_face);
        btn_end = (LinearLayout) findViewById(R.id.btn_end);

        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        image_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    String mText; //GCM 연동 시 여기에 string 받아와서 넣으면됨
                    mText = "기사님 추워여우어으"; //말하게 될 string
                    mTextMessage = new String[]{mText};
                    mNaverTTSTask = new NaverTTSTask();
                    mNaverTTSTask.execute(mTextMessage);
                    isClicked = false;

                    message_txt.setText(mText);
                    view_color.setBackground(getResources().getDrawable(R.color.cblue));
                    image_face.setImageResource(R.mipmap.ic_bad);
                } else {
                    String mText;
                    mText = "기사님 더워여우어어";
                    mTextMessage = new String[]{mText};
                    mNaverTTSTask = new NaverTTSTask();
                    mNaverTTSTask.execute(mTextMessage);
                    isClicked = true;

                    message_txt.setText(mText);
                    view_color.setBackground(getResources().getDrawable(R.color.cred));
                    image_face.setImageResource(R.mipmap.ic_bad);
                }


                //기본 상태로 바꿔주기
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        message_txt.setText(getString(R.string.nothing_txt));
                        view_color.setBackground(getResources().getDrawable(R.color.cgray));
                        image_face.setImageResource(R.mipmap.ic_good);
                    }
                }, 2970);
            }
        });

        instance_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mText;
                mText = "기사님 제가 정말정말 급해서 화장실을 가야만 할 것 같은 느낌적인 느낌이 드는데 제발";
                mTextMessage = new String[]{mText};
                mNaverTTSTask = new NaverTTSTask();
                mNaverTTSTask.execute(mTextMessage);
                isClicked = false;

                message_txt.setText(mText);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        message_txt.setText(getString(R.string.nothing_txt));
                    }
                }, 5000);
            }
        });
    }

    private class NaverTTSTask extends AsyncTask<String[], Void, String> {

        @Override
        protected String doInBackground(String[]... strings) {
            //여기서 서버에 요청
            TTSHelper.main(mTextMessage, mContext);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

}
