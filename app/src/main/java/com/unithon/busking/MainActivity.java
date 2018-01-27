package com.unithon.busking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;
import com.unithon.busking.activity.DriverActivity;
import com.unithon.busking.activity.SplashActivity;

import org.json.JSONException;
import org.json.JSONObject;

import static com.nhn.android.naverlogin.OAuthLogin.mOAuthLoginHandler;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    private static OAuthLogin mOAuthLoginModule;
    OAuthLoginButton naverLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        startActivity(new Intent(mContext, SplashActivity.class));

        setNaver();
    }

    private void setNaver() {
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                mContext,
                getString(R.string.OAUTH_CLIENT_ID),
                getString(R.string.OAUTH_CLIENT_SECRET),
                getString(R.string.OAUTH_CLIENT_NAME)
        );

        naverLoginButton = (OAuthLoginButton) findViewById(R.id.naver_login);
        naverLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
        naverLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOAuthLoginModule.startOauthLoginActivity(MainActivity.this, mOAuthLoginHandler);
            }
        });
    }

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                String accessToken = mOAuthLoginModule.getAccessToken(mContext);
                String refreshToken = mOAuthLoginModule.getRefreshToken(mContext);
                long expiresAt = mOAuthLoginModule.getExpiresAt(mContext);
                String tokenType = mOAuthLoginModule.getTokenType(mContext);

                finish();
                startActivity(new Intent(mContext, DriverActivity.class));
            } else {
                String errorCode = mOAuthLoginModule.getLastErrorCode(mContext).getCode();
                String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
                Toast.makeText(mContext, "errorCode:" + errorCode
                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }
    };
}
