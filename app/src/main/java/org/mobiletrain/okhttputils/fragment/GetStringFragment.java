package org.mobiletrain.okhttputils.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class GetStringFragment extends Fragment {
//    public static final String JSON_URL = "http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1";
    public static final String JSON_URL = "http://www.qubaobei.com/ios/cf/dish_list.php";

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        textView = new TextView(getContext());
        textView.setBackgroundColor(Color.GREEN);

        OkHttpUtils
                .get()
                .url(JSON_URL)
                .addParams("stage_id", "1")
                .addParams("limit", "10")
                .addParams("page", "1")
                .build()
                .execute(stringCallback);// 不是接口，不能通过实现的方式


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
