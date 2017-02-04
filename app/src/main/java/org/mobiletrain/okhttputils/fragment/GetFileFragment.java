package org.mobiletrain.okhttputils.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

public class GetFileFragment extends Fragment {
    public  String file_url = "https://www.baidu.com/img/bd_logo1.png";

    private String fileName;

    private MyFileCallback fileCallback;

    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fileName = file_url.substring(file_url.lastIndexOf("/") + 1);
        fileCallback = new MyFileCallback(Environment.getExternalStorageDirectory().getPath(), fileName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         textView = new TextView(getContext());
        textView.setBackgroundColor(Color.GRAY);

        OkHttpUtils
                .get()
                .url(file_url)
                .build()
                .execute(fileCallback);


        return textView;
    }

    private class MyFileCallback extends FileCallBack {

        public MyFileCallback(String destFileDir, String destFileName) {
            super(destFileDir, destFileName);
        }

        @Override
        public void inProgress(float progress) {

        }

        @Override
        public void onError(Request request, Exception e) {

        }

        // 自动存储文件，成功后返回文件对象
        @Override
        public void onResponse(File response) {
            String filePath = response.getAbsolutePath();
            textView.setText(filePath);
        }
    }

}
