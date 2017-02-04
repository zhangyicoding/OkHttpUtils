package org.mobiletrain.okhttputils.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

public class PostFileFragment extends Fragment {

    public static final String FILE_URL = "http://10.0.184.27:8080/PostServletDemo/PostFileServlet";

    private TextView textView;

    private File uploadFile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uploadFile = new File(Environment.getExternalStorageDirectory(), "bd_logo1.png");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        textView = new TextView(getContext());


        OkHttpUtils
                .postFile()
                .file(uploadFile)
                .url(FILE_URL)
                .build()
                .execute(stringCallback);

        return textView;
    }

    private StringCallback stringCallback = new StringCallback() {
        @Override
        public void onError(Request request, Exception e) {

        }

        @Override
        public void onResponse(String response) {
            textView.setText(response);
        }
    };

}
