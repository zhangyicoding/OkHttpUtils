package org.mobiletrain.okhttputils.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import org.mobiletrain.okhttputils.R;

public class GetBitmapFragment extends Fragment {
    public static final String IMG_URL = "https://www.baidu.com/img/bd_logo1.png";

    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         imageView = new ImageView(getContext());
        imageView.setBackgroundColor(Color.YELLOW);
        imageView.setImageResource(R.mipmap.ic_launcher);

        OkHttpUtils
                .get()
                .url(IMG_URL)
                .build()
                .execute(bitmapCallback);

        return imageView;
    }

    private BitmapCallback bitmapCallback = new BitmapCallback() {
        @Override
        public void onError(Request request, Exception e) {

        }

        @Override
        public void onResponse(Bitmap response) {
            imageView.setImageBitmap(response);
        }
    };

}
