package org.mobiletrain.okhttputils.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class PostParamsFragment extends Fragment {
//public static final String JSON_URL = "http://192.168.1.127:8080/PostServletDemo/PostParamsServlet";cateid
public static final String JSON_URL = "http://app.vmoiver.com/apiv3/post/getPostInCate";

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        textView = new TextView(getContext());

        OkHttpUtils
                .post()
                .url(JSON_URL)
                .addParams("cateid", "0")
                .addParams("p", "1")
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
            Log.d("tag", response);
            textView.setText(response);

        }

    };

}
