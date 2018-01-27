package com.unithon.busking.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.unithon.busking.R;
import com.unithon.busking.util.TTSHelper;


public class DrivingActivity extends AppCompatActivity {

    private NaverTTSTask mNaverTTSTask;
    private TTSHelper ttsHelper;
    TextView client_message;
    String[] mTextMessage;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving);
        mContext = this;

        ttsHelper = new TTSHelper();
        //client_message = (TextView) findViewById(R.id.message_txt);

        String mText;
        mText = "";
        mTextMessage = new String[]{mText};

        Button btn_sample = (Button) findViewById(R.id.btn_sample);
        btn_sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNaverTTSTask = new NaverTTSTask();
                mNaverTTSTask.execute(mTextMessage);
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
            //방금 받은 파일명의 mp3가 있으면 플레이 시키자. 맞나 여기서 하는거?
            //아닌가 파일을 만들고 바로 실행되게 해야 하나? AsyncTask 백그라운드 작업중에...?
        }
    }

}
