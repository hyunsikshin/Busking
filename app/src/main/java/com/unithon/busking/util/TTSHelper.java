package com.unithon.busking.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by hyunsik on 2018. 1. 27..
 */

public class TTSHelper {

    private static String TAG = "TTSHelper";

    public static void main(String[] args, Context mContext) {
        String clientId = "4W1mScFleCGfFqwG9DYu";//애플리케이션 클라이언트 아이디값"
        String clientSecret = "677LsQMkka";//애플리케이션 클라이언트 시크릿값"
        try {
            String text = URLEncoder.encode(args[0], "UTF-8"); // 13자
            String apiURL = "https://openapi.naver.com/v1/voice/tts.bin";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "speaker=mijin&speed=0&text=" + text;
            con.setDoOutput(true);
            con.setDoInput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];

                // 랜덤한 이름으로 mp3 파일 생성
                //String tempname = Long.valueOf(new Date().getTime()).toString();
                String tempname = "naverttstemp"; //하나의 파일명으로 덮어쓰기 하자.
                File f = new File(mContext.getFilesDir(), tempname + ".mp3");
                String fileChk = mContext.getFilesDir() + File.separator + tempname + ".mp3";
                File file = new File(fileChk);
                FileOutputStream outputStream = new FileOutputStream(f);
                while ((read = is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                is.close();
                //여기서 바로 재생
                String Path_to_file = mContext.getFilesDir() + File.separator + tempname + ".mp3";
                MediaPlayer audioPlay = new MediaPlayer();
                audioPlay.setAudioStreamType(AudioManager.STREAM_MUSIC);
                audioPlay.setDataSource(Path_to_file);
                audioPlay.prepare();//이걸 해줘야 하는군. 없으면 에러난다.
                audioPlay.start();


            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
