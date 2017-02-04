package org.mobiletrain.okhttputils.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.mobiletrain.okhttputils.R;
import org.mobiletrain.okhttputils.fragment.GetBitmapFragment;
import org.mobiletrain.okhttputils.fragment.GetFileFragment;
import org.mobiletrain.okhttputils.fragment.GetStringFragment;
import org.mobiletrain.okhttputils.fragment.PostFileFragment;
import org.mobiletrain.okhttputils.fragment.PostParamsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;

    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        fragmentList = new ArrayList<>();


        fragmentList.add(new GetStringFragment());
        fragmentList.add(new GetBitmapFragment());
        fragmentList.add(new GetFileFragment());
        fragmentList.add(new PostParamsFragment());
        fragmentList.add(new PostFileFragment());

        viewPager.setAdapter(adapter);
    }

    private FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    };


    // 权限请求码
    private final int REQUEST_CODE = 1;



    // Android6.0系统申请读写外部存储的运行时权限（该方法在需要操作外部存储时调用，或在相关初始化操作中调用）
    private void requestPermissions() {
        // 如果未获得外部存储读写权限，则申请
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }



    // 请求运行时权限的回调方法
    // requestCode：用于识别申请权限的请求码
    // permissions：请求的权限
    // grantResults：对应权限的请求结果
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 允许权限
                    Toast.makeText(MainActivity.this, "允许权限", Toast.LENGTH_SHORT).show();
                } else {
                    // 拒绝权限
                    Toast.makeText(MainActivity.this, "拒绝权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
